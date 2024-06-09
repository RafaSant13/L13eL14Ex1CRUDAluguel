package com.example.l13el14crudaluguel.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l13el14crudaluguel.controller.ICRUDDao;
import com.example.l13el14crudaluguel.model.Livro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements ICRUDDao<Livro>, ILivroDao {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public LivroDao(Context context) {
        this.context = context;
    }

    @Override
    public LivroDao open() throws SQLException {
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
    public void insert(Livro livro) throws SQLException {
        db.insert("exemplar", null, getExemplarContentValues(livro));
        db.insert("livro", null, getLivroContentValues(livro));
    }

    @Override
    public int update(Livro livro) throws SQLException {
        db.update("exemplar", getExemplarContentValues(livro),
                "codigo = " + livro.getCodigo(), null);
        db.update("livro", getLivroContentValues(livro),
                "exemplarCodigo = " + livro.getCodigo(), null);
        return 0;
    }

    @Override
    public void delete(Livro livro) throws SQLException {
        db.delete("livro", "exemplarCodigo = " + livro.getCodigo(), null);
        db.delete("exemplar", "codigo = " + livro.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Livro findOne(Livro livro) throws SQLException {
        String SQL = "SELECT " +
                "exemplar.codigo , exemplar.nome, exemplar.paginas, livro.isbn, livro.edicao " +
                "FROM exemplar, livro " +
                "WHERE exemplar.codigo = livro.exemplarCodigo " +
                "AND exemplar.codigo = " + livro.getCodigo();

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
            livro.setISBN(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
        }

        cursor.close();

        return livro;
    }

    @SuppressLint("Range")
    @Override
    public List<Livro> findAll() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String SQL = "SELECT " +
                "exemplar.codigo , exemplar.nome, exemplar.paginas, livro.isbn, livro.edicao " +
                "FROM exemplar, livro " +
                "WHERE exemplar.codigo = livro.exemplarCodigo";

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            Livro livro = new Livro();
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
            livro.setISBN(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
            livros.add(livro);
            cursor.moveToNext();
        }
        cursor.close();
        return livros;
    }

    private static ContentValues getExemplarContentValues(Livro livro){
        ContentValues cv = new ContentValues();
        cv.put("codigo", livro.getCodigo());
        cv.put("nome", livro.getNome());
        cv.put("paginas", livro.getQtdPaginas());
        return cv;
    }

    private static ContentValues getLivroContentValues(Livro livro){
        ContentValues cv = new ContentValues();
        cv.put("exemplarCodigo", livro.getCodigo());
        cv.put("isbn", livro.getISBN());
        cv.put("edicao", livro.getEdicao());
        return cv;
    }

}
