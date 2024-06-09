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

public class AlunoFragment extends Fragment {

    private View view;
    private EditText etRaAluno;
    private EditText etNomeAluno;
    private EditText etEmailAluno;
    private Button btnInserirAluno;
    private Button btnBuscarAluno;
    private Button btnAlterarAluno;
    private Button btnExcluirAluno;
    private Button btnListarAluno;
    private TextView tvAluno;

    public AlunoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluno, container, false);
        etRaAluno = view.findViewById(R.id.etRaAluno);
        etNomeAluno = view.findViewById(R.id.etNomeAluno);
        etEmailAluno = view.findViewById(R.id.etEmailAluno);
        btnInserirAluno = view.findViewById(R.id.btnInserirAluno);
        btnBuscarAluno = view.findViewById(R.id.btnBuscarAluno);
        btnAlterarAluno = view.findViewById(R.id.btnAlterarAluno);
        btnExcluirAluno = view.findViewById(R.id.btnExcluirAluno);
        btnListarAluno = view.findViewById(R.id.btnListarAluno);
        tvAluno = view.findViewById(R.id.tvAluno);
        tvAluno.setMovementMethod(new ScrollingMovementMethod());
        btnInserirAluno.setOnClickListener(op -> inserir());
        btnBuscarAluno.setOnClickListener(op -> buscar());
        btnAlterarAluno.setOnClickListener(op -> alterar());
        btnExcluirAluno.setOnClickListener(op -> excluir());
        btnListarAluno.setOnClickListener(op -> listar());
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