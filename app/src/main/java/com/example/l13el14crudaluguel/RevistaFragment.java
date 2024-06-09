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

public class RevistaFragment extends Fragment {

    private View view;
    private EditText etCodigoRevista;
    private EditText etNomeRevista;
    private EditText etQtdPaginasRevista;
    private EditText etIssnRevista;
    private Button btnInserirRevista;
    private Button btnBuscarRevista;
    private Button btnAlterarRevista;
    private Button btnExcluirRevista;
    private Button btnListarRevista;
    private TextView tvRevista;

    public RevistaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_revista, container, false);
        etCodigoRevista = view.findViewById(R.id.etCodigoRevista);
        etNomeRevista = view.findViewById(R.id.etNomeRevista);
        etQtdPaginasRevista = view.findViewById(R.id.etQtdPaginasRevista);
        etIssnRevista = view.findViewById(R.id.etIssnRevista);
        btnInserirRevista = view.findViewById(R.id.btnInserirRevista);
        btnBuscarRevista = view.findViewById(R.id.btnBuscarRevista);
        btnAlterarRevista = view.findViewById(R.id.btnAlterarRevista);
        btnExcluirRevista = view.findViewById(R.id.btnExcluirRevista);
        btnListarRevista = view.findViewById(R.id.btnListarRevista);
        tvRevista = view.findViewById(R.id.tvRevista);
        tvRevista.setMovementMethod(new ScrollingMovementMethod());
        btnInserirRevista.setOnClickListener(op -> inserir());
        btnBuscarRevista.setOnClickListener(op -> buscar());
        btnAlterarRevista.setOnClickListener(op -> alterar());
        btnExcluirRevista.setOnClickListener(op -> excluir());
        btnListarRevista.setOnClickListener(op -> listar());
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