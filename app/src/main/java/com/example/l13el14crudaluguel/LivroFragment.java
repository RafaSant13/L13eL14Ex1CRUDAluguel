package com.example.l13el14crudaluguel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l13el14crudaluguel.controller.LivroController;
import com.example.l13el14crudaluguel.model.Aluno;
import com.example.l13el14crudaluguel.model.Livro;
import com.example.l13el14crudaluguel.persistance.LivroDao;

import java.sql.SQLException;
import java.util.List;

public class LivroFragment extends Fragment {

    private View view;
    private EditText etCodigoLivro;
    private EditText etNomeLivro;
    private EditText etQtdPaginasLivro;
    private EditText etIsbnLivro;
    private EditText etEdicaoLivro;
    private Button btnInserirLivro;
    private Button btnBuscarLivro;
    private Button btnAlterarLivro;
    private Button btnExcluirLivro;
    private Button btnListarLivro;
    private TextView tvLivro;
    private LivroController lc;

    public LivroFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_livro, container, false);
        etCodigoLivro = view.findViewById(R.id.etCodigoLivro);
        etNomeLivro = view.findViewById(R.id.etNomeLivro);
        etQtdPaginasLivro = view.findViewById(R.id.etQtdPaginasLivro);
        etIsbnLivro = view.findViewById(R.id.etIsbnLivro);
        etEdicaoLivro = view.findViewById(R.id.etEdicaoLivro);
        btnInserirLivro = view.findViewById(R.id.btnInserirLivro);
        btnBuscarLivro = view.findViewById(R.id.btnBuscarLivro);
        btnAlterarLivro = view.findViewById(R.id.btnAlterarLivro);
        btnExcluirLivro = view.findViewById(R.id.btnExcluirLivro);
        btnListarLivro = view.findViewById(R.id.btnListarLivro);
        tvLivro = view.findViewById(R.id.tvLivro);
        tvLivro.setMovementMethod(new ScrollingMovementMethod());
        lc = new LivroController(new LivroDao(view.getContext()));
        btnInserirLivro.setOnClickListener(op -> {
            try {
                inserir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnBuscarLivro.setOnClickListener(op -> {
            try {
                buscar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnAlterarLivro.setOnClickListener(op -> {
            try {
                alterar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnExcluirLivro.setOnClickListener(op -> {
            try {
                excluir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnListarLivro.setOnClickListener(op -> {
            try {
                listar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void inserir() throws SQLException {
        Livro l = montaLivro();
        lc.insert(l);
        Toast.makeText(view.getContext(), "Livro inserido com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void buscar() throws SQLException {
        Livro l = montaLivro();
        l =lc.findOne(l);
        preencheCampos(l);
    }

    private void alterar() throws SQLException {
        Livro l = montaLivro();
        lc.update(l);
        Toast.makeText(view.getContext(), "Livro alterado com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void excluir() throws SQLException {
        Livro l = montaLivro();
        lc.delete(l);
        Toast.makeText(view.getContext(), "Livro excluído com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void listar() throws SQLException {
        List<Livro> livros = lc.findAll();
        StringBuffer buffer = new StringBuffer();
        for (Livro l : livros){
            buffer.append(l.toString()+"\n");
        }
        tvLivro.setText(buffer.toString());
    }

    private Livro montaLivro(){
        Livro l = new Livro();
        l.setCodigo(Integer.parseInt(etCodigoLivro.getText().toString()));
        l.setNome(etNomeLivro.getText().toString());
        if (etQtdPaginasLivro.getText().toString().equals("")){
            l.setQtdPaginas(0);
        } else {
            l.setQtdPaginas(Integer.parseInt(etQtdPaginasLivro.getText().toString()));
        }
        l.setISBN(etIsbnLivro.getText().toString());
        if (etEdicaoLivro.getText().toString().equals("")){
            l.setEdicao(0);
        } else {
            l.setEdicao(Integer.parseInt(etEdicaoLivro.getText().toString()));
        }
        return l;
    }

    private void preencheCampos(Livro l){
        etCodigoLivro.setText(String.valueOf(l.getCodigo()));
        etNomeLivro.setText(l.getNome());
        etQtdPaginasLivro.setText(String.valueOf(l.getQtdPaginas()));
        etIsbnLivro.setText(l.getISBN());
        etEdicaoLivro.setText(String.valueOf(l.getEdicao()));
    }

    private void limpaCampos(){
        etCodigoLivro.setText("");
        etNomeLivro.setText("");
        etQtdPaginasLivro.setText("");
        etIsbnLivro.setText("");
        etEdicaoLivro.setText("");
        tvLivro.setText("");
    }
}