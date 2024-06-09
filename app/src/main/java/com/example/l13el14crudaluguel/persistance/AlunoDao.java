package com.example.l13el14crudaluguel.persistance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.l13el14crudaluguel.controller.ICRUDDao;
import com.example.l13el14crudaluguel.model.Aluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements ICRUDDao<Aluno>, IAlunoDao {

    private Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public AlunoDao(Context context) {
        this.context = context;
    }

    @Override
    public AlunoDao open() throws SQLException {
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
    public void insert(Aluno aluno) throws SQLException {
        db.insert("aluno", null, getContentValues(aluno));
    }

    @Override
    public int update(Aluno aluno) throws SQLException {
        return db.update("aluno", getContentValues(aluno),
                "ra = " + aluno.getRa(), null);
    }

    @Override
    public void delete(Aluno aluno) throws SQLException {
        db.delete("aluno", "ra = " + aluno.getRa(), null);
    }

    @SuppressLint("Range")
    @Override
    public Aluno findOne(Aluno aluno) throws SQLException {
        String SQL = "SELECT ra, nome, email " +
                "FROM aluno " +
                "WHERE ra = " + aluno.getRa();

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        }

        cursor.close();

        return aluno;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluno> findAll() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String SQL = "SELECT ra, nome, email " +
                "FROM aluno";

        Cursor cursor = db.rawQuery(SQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunos.add(aluno);
            cursor.moveToNext();
        }

        cursor.close();

        return alunos;
    }

    private static ContentValues getContentValues(Aluno aluno) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ra", aluno.getRa());
        contentValues.put("nome", aluno.getNome());
        contentValues.put("email", aluno.getEmail());

        return contentValues;
    }
}
