package com.example.l13el14crudaluguel.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l13el14crudaluguel.controller.ICRUDDao;
import com.example.l13el14crudaluguel.model.Livro;
import com.example.l13el14crudaluguel.model.Revista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevistaDao implements ICRUDDao<Revista>, IRevistaDao {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public RevistaDao(Context context) {
        this.context = context;
    }

    @Override
    public RevistaDao open() throws SQLException {
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
    public void insert(Revista revista) throws SQLException {
        db.insert("exemplar", null, getExemplarContentValues(revista));
        db.insert("revista", null, getRevistaContentValues(revista));
    }

    @Override
    public int update(Revista revista) throws SQLException {
        db.update("exemplar", getExemplarContentValues(revista),
                "codigo = " + revista.getCodigo(), null);
        db.update("revista", getRevistaContentValues(revista),
                "exemplarCodigo = " + revista.getCodigo(), null);
        return 0;
    }

    @Override
    public void delete(Revista revista) throws SQLException {
        db.delete("revista", "exemplarCodigo = " + revista.getCodigo(), null);
        db.delete("exemplar", "codigo = " + revista.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Revista findOne(Revista revista) throws SQLException {
        String SQL = "SELECT " +
                "exemplar.codigo , exemplar.nome, exemplar.paginas, revista.issn " +
                "FROM exemplar, revista " +
                "WHERE exemplar.codigo = revista.exemplarCodigo " +
                "AND exemplar.codigo = " + revista.getCodigo();

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
            revista.setISSN(cursor.getString(cursor.getColumnIndex("issn")));
        }

        cursor.close();

        return revista;
    }

    @SuppressLint("Range")
    @Override
    public List<Revista> findAll() throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        String SQL = "SELECT " +
                "exemplar.codigo , exemplar.nome, exemplar.paginas, revista.issn " +
                "FROM exemplar, revista " +
                "WHERE exemplar.codigo = revista.exemplarCodigo";

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Revista revista = new Revista();
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("paginas")));
            revista.setISSN(cursor.getString(cursor.getColumnIndex("issn")));
            revistas.add(revista);
            cursor.moveToNext();
        }

        cursor.close();
        return revistas;
    }

    private static ContentValues getExemplarContentValues(Revista revista){
        ContentValues cv = new ContentValues();
        cv.put("codigo", revista.getCodigo());
        cv.put("nome", revista.getNome());
        cv.put("paginas", revista.getQtdPaginas());
        return cv;
    }

    private static ContentValues getRevistaContentValues(Revista revista){
        ContentValues cv = new ContentValues();
        cv.put("exemplarCodigo", revista.getCodigo());
        cv.put("issn", revista.getISSN());
        return cv;
    }
}
