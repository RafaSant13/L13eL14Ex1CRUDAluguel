package com.example.l13el14crudaluguel.model;

import java.time.LocalDate;

public class Aluguel {

    private Aluno aluno;
    private Exemplar exemplar;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;

    public Aluguel() {
        super();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevoluacao) {
        this.dataDevolucao = dataDevoluacao;
    }

    @Override
    public String toString() {
        return "ALUNO - Nome: " + aluno.getNome() + " RA: " + aluno.getRa() + " E-mail: "
                + aluno.getEmail() + "\n EXEMPLAR - Nome: " + exemplar.getNome() + " Código: " + exemplar.getCodigo() +
                "\n DATA DE RETIRADA: " + dataRetirada.toString() + "\n DATA DE DEVOLUÇÃO: " + dataDevolucao.toString();
    }
}

