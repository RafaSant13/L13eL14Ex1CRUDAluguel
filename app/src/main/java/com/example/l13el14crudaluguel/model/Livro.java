package com.example.l13el14crudaluguel.model;

public class Livro extends Exemplar {

    private String ISBN;
    private int edicao;

    public Livro() {
        super();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "#"+getCodigo()+" "+getNome()+" "+edicao+"ª Edição";
    }
}
