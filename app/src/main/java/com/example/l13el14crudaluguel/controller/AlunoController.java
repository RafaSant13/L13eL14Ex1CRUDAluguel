package com.example.l13el14crudaluguel.controller;

import com.example.l13el14crudaluguel.model.Aluno;
import com.example.l13el14crudaluguel.persistance.AlunoDao;

import java.sql.SQLException;
import java.util.List;

public class AlunoController implements IController<Aluno>{

    private AlunoDao alDao;

    public AlunoController (AlunoDao alDao){
        this.alDao = alDao;
    }

    @Override
    public void insert(Aluno aluno) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.insert(aluno);
        alDao.close();
    }

    @Override
    public void update(Aluno aluno) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.update(aluno);
        alDao.close();
    }

    @Override
    public void delete(Aluno aluno) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        alDao.delete(aluno);
        alDao.close();
    }

    @Override
    public Aluno findOne(Aluno aluno) throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findOne(aluno);
    }

    @Override
    public List<Aluno> findAll() throws SQLException {
        if (alDao.open()==null){
            alDao.open();
        }
        return alDao.findAll();
    }
}
