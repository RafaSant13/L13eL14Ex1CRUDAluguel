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
        btnInserirLivro.setOnClickListener(op -> inserir());
        btnBuscarLivro.setOnClickListener(op -> buscar());
        btnAlterarLivro.setOnClickListener(op -> alterar());
        btnExcluirLivro.setOnClickListener(op -> excluir());
        btnListarLivro.setOnClickListener(op -> listar());
        return view;
    }

    private void inserir() {

    }

    private void buscar() {

    }

    private void alterar() {

    }

    private void excluir() {

    }

    private void listar() {

    }
}