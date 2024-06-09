package com.example.l13el14crudaluguel.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l13el14crudaluguel.controller.ICRUDDao;
import com.example.l13el14crudaluguel.model.Aluguel;
import com.example.l13el14crudaluguel.model.Aluno;
import com.example.l13el14crudaluguel.model.Livro;
import com.example.l13el14crudaluguel.model.Revista;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AluguelDao implements ICRUDDao<Aluguel>, IAluguelDao {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public AluguelDao(Context context) {
        this.context = context;
    }

    @Override
    public AluguelDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        db.insert("aluguel", null, getContentValues(aluguel));
    }

    @Override
    public int update(Aluguel aluguel) throws SQLException {
        ContentValues cv = getContentValues(aluguel);
        int ret = db.update("aluguel", cv,
                "dataRetirada = '"+aluguel.getDataRetirada().toString()+"'" +
                        " AND alunoRa = "+aluguel.getAluno().getRa()+" " +
                        "AND exemplarCodigo = "+aluguel.getExemplar().getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        ContentValues cv = getContentValues(aluguel);
        db.delete("aluguel", "dataRetirada = '"+aluguel.getDataRetirada().toString()+"'" +
                        " AND alunoRa = "+aluguel.getAluno().getRa()+" " +
                        "AND exemplarCodigo = "+aluguel.getExemplar().getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        String SQL = "SELECT " +
                "aluguel.dataRetirada, aluguel.dataDevolucao, " +
                "aluno.ra, aluno.nome AS aNome, aluno.email, " +
                "exemplar.codigo, exemplar.nome AS eNome, exemplar.paginas, " +
                "livro.isbn, livro.edicao, " +
                "revista.issn  " +
                "FROM aluguel " +
                "INNER JOIN aluno ON aluguel.alunoRa = aluno.ra " +
                "INNER JOIN exemplar ON aluguel.exemplarCodigo = exemplar.codigo " +
                "LEFT JOIN revista ON exemplar.codigo = revista.exemplarCodigo " +
                "LEFT JOIN livro ON exemplar.codigo = livro.exemplarCodigo " +
                "WHERE aluguel.dataRetirada = '" + aluguel.getDataRetirada().toString() + "' " +
                "AND aluguel.alunoRa = " + aluguel.getAluno().getRa() + " " +
                "AND aluguel.exemplarCodigo = " + aluguel.getExemplar().getCodigo();

        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            Aluno al = new Aluno();
            al.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            al.setNome(cursor.getString(cursor.getColumnIndex("aNome")));
            al.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluguel.setAluno(al);

            if (!cursor.isNull(cursor.getColumnIndex("isbn"))) {
                Livro l = new Livro();
                l.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                l.setNome(cursor.getString(cursor.getColumnIndex("eNome")));
                l.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
                l.setISBN(cursor.getString(cursor.getColumnIndex("isbn")));
                l.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
                aluguel.setExemplar(l);
            } else {
                Revista r = new Revista();
                r.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                r.setNome(cursor.getString(cursor.getColumnIndex("eNome")));
                r.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
                r.setISSN(cursor.getString(cursor.getColumnIndex("issn")));
                aluguel.setExemplar(r);
            }
            aluguel.setDataRetirada(LocalDate.parse(cursor.getString(cursor.getColumnIndex("dataRetirada"))));
            aluguel.setDataDevolucao(LocalDate.parse(cursor.getString(cursor.getColumnIndex("dataDevolucao"))));
        }

        cursor.close();

        return aluguel;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();
        String SQL = "SELECT " +
                "aluguel.dataRetirada, aluguel.dataDevolucao, " +
                "aluno.ra, aluno.nome AS aNome, aluno.email, " +
                "exemplar.codigo, exemplar.nome as eNome, exemplar.paginas, " +
                "livro.isbn, livro.edicao, " +
                "revista.issn  " +
                "FROM aluguel " +
                "INNER JOIN aluno ON aluguel.alunoRa = aluno.ra " +
                "INNER JOIN exemplar ON aluguel.exemplarCodigo = exemplar.codigo " +
                "LEFT JOIN revista ON exemplar.codigo = revista.exemplarCodigo " +
                "LEFT JOIN livro ON exemplar.codigo = livro.exemplarCodigo";

        Cursor cursor = db.rawQuery(SQL, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Aluguel aluguel = new Aluguel();
            Aluno al = new Aluno();
            al.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            al.setNome(cursor.getString(cursor.getColumnIndex("aNome")));
            al.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluguel.setAluno(al);

            if (!cursor.isNull(cursor.getColumnIndex("isbn"))) {
                Livro l = new Livro();
                l.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                l.setNome(cursor.getString(cursor.getColumnIndex("eNome")));
                l.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
                l.setISBN(cursor.getString(cursor.getColumnIndex("isbn")));
                l.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
                aluguel.setExemplar(l);
            } else {
                Revista r = new Revista();
                r.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                r.setNome(cursor.getString(cursor.getColumnIndex("eNome")));
                r.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
                r.setISSN(cursor.getString(cursor.getColumnIndex("issn")));
                aluguel.setExemplar(r);
            }
            aluguel.setDataRetirada(LocalDate.parse(cursor.getString(cursor.getColumnIndex("dataRetirada"))));
            aluguel.setDataDevolucao(LocalDate.parse(cursor.getString(cursor.getColumnIndex("dataDevolucao"))));
            alugueis.add(aluguel);
            cursor.moveToNext();
        }

        cursor.close();

        return alugueis;
    }

    private static ContentValues getContentValues(Aluguel aluguel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dataRetirada", aluguel.getDataRetirada().toString());
        if (aluguel.getDataDevolucao()!=null){
            contentValues.put("dataDevolucao", aluguel.getDataDevolucao().toString());
        }
        contentValues.put("alunoRa", aluguel.getAluno().getRa());
        contentValues.put("exemplarCodigo", aluguel.getExemplar().getCodigo());

        return contentValues;
    }
}
