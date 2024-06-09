package com.example.l13el14crudaluguel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AluguelFragment extends Fragment {

    private View view;
    private Spinner spAlunoAluguel;
    private Spinner spExemplarAluguel;
    private EditText etRetirada;
    private EditText etDevolucao;
    private Button btnInserirAluguel;
    private Button btnBuscarAluguel;
    private Button btnAlterarAluguel;
    private Button btnExcluirAluguel;
    private Button btnListarAluguel;
    private TextView tvAluguel;


    public AluguelFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);
        spAlunoAluguel = view.findViewById(R.id.spAlunoAluguel);
        spExemplarAluguel = view.findViewById(R.id.spExemplarAluguel);
        preencheSpinners();
        etRetirada = view.findViewById(R.id.etRetirada);
        etDevolucao = view.findViewById(R.id.etDevolucao);
        btnInserirAluguel = view.findViewById(R.id.btnInserirAluguel);
        btnBuscarAluguel = view.findViewById(R.id.btnBuscarAluguel);
        btnAlterarAluguel = view.findViewById(R.id.btnAlterarAluguel);
        btnExcluirAluguel = view.findViewById(R.id.btnExcluirAluguel);
        btnListarAluguel = view.findViewById(R.id.btnListarAluguel);
        tvAluguel = view.findViewById(R.id.tvAluguel);
        tvAluguel.setMovementMethod(new ScrollingMovementMethod());
        btnInserirAluguel.setOnClickListener(op -> inserir());
        btnBuscarAluguel.setOnClickListener(op -> buscar());
        btnAlterarAluguel.setOnClickListener(op -> alterar());
        btnExcluirAluguel.setOnClickListener(op -> excluir());
        btnListarAluguel.setOnClickListener(op -> listar());
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

    private void preencheSpinners() {

    }

}