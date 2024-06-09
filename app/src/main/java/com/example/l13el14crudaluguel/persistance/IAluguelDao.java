package com.example.l13el14crudaluguel.persistance;

import java.sql.SQLException;

public interface IAluguelDao {
    public AluguelDao open() throws SQLException;
    public void close();
}
