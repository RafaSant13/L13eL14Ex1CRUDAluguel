package com.example.l13el14crudaluguel.controller;

import com.example.l13el14crudaluguel.model.Aluguel;
import com.example.l13el14crudaluguel.persistance.AluguelDao;
import com.example.l13el14crudaluguel.persistance.AlunoDao;

import java.sql.SQLException;
import java.util.List;

public class AluguelController implements IController<Aluguel> {

    private AluguelDao alDao;

    public AluguelController(AluguelDao alDao) {
        this.alDao = alDao;
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.insert(aluguel);
        alDao.close();
    }

    @Override
    public void update(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.update(aluguel);
        alDao.close();
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.delete(aluguel);
        alDao.close();
    }

    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findOne(aluguel);
    }

    @Override
    public List<Aluguel> findAll() throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findAll();
    }
}
