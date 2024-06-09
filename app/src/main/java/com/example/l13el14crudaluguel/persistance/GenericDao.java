package com.example.l13el14crudaluguel.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {
    private static final String DATABASE = "Biblioteca";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_ALUNO =
            "CREATE TABLE aluno( " +
                    "ra INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL);";
    private static final String CREATE_TABLE_EXEMPLAR =
            "CREATE TABLE exemplar( " +
                    "codigo INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "paginas INTEGER(10) NOT NULL);";
    private static final String CREATE_TABLE_LIVRO =
            "CREATE TABLE livro( " +
                    "isbn CHAR(13) NOT NULL, " +
                    "edicao INTEGER(10) NOT NULL, " +
                    "exemplarCodigo INTEGER(10), " +
                    "FOREIGN KEY (exemplarCodigo) REFERENCES exemplar (codigo) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_REVISTA =
            "CREATE TABLE revista( " +
                    "issn CHAR(8) NOT NULL, " +
                    "exemplarCodigo INTEGER(10), " +
                    "FOREIGN KEY (exemplarCodigo) REFERENCES exemplar (codigo) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_ALUGUEL =
            "CREATE TABLE aluguel( " +
                    "dataRetirada VARCHAR(10) NOT NULL, " +
                    "dataDevolucao VARCHAR(10) NOT NULL, " +
                    "alunoRa INTEGER(10),  " +
                    "exemplarCodigo INTEGER(10), " +
                    "FOREIGN KEY (alunoRa) REFERENCES aluno (ra) ON DELETE CASCADE, " +
                    "FOREIGN KEY (exemplarCodigo) REFERENCES exemplar (codigo) ON DELETE CASCADE, " +
                    "PRIMARY KEY (dataRetirada, alunoRa, exemplarCodigo));";

    public GenericDao(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUNO);
        db.execSQL(CREATE_TABLE_EXEMPLAR);
        db.execSQL(CREATE_TABLE_LIVRO);
        db.execSQL(CREATE_TABLE_REVISTA);
        db.execSQL(CREATE_TABLE_ALUGUEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS aluguel");
            db.execSQL("DROP TABLE IF EXISTS revista");
            db.execSQL("DROP TABLE IF EXISTS livro");
            db.execSQL("DROP TABLE IF EXISTS exemplar");
            db.execSQL("DROP TABLE IF EXISTS aluno");

            onCreate(db);
        }
    }
}
