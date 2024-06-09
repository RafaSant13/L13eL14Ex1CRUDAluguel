package com.example.l13el14crudaluguel.persistance;

import java.sql.SQLException;

public interface IRevistaDao {
    public RevistaDao open() throws SQLException;
    public void close();
}
