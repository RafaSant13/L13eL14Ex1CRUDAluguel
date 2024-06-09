package com.example.l13el14crudaluguel.persistance;

import java.sql.SQLException;

public interface ILivroDao {
    public LivroDao open() throws SQLException;
    public void close();
}
