package com.example.l13el14crudaluguel.persistance;

import java.sql.SQLException;

public interface IAlunoDao {
    public AlunoDao open() throws SQLException;
    public void close();
}
