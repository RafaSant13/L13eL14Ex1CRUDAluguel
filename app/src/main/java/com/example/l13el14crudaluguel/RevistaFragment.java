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

import com.example.l13el14crudaluguel.controller.RevistaController;
import com.example.l13el14crudaluguel.model.Livro;
import com.example.l13el14crudaluguel.model.Revista;
import com.example.l13el14crudaluguel.persistance.RevistaDao;

import java.sql.SQLException;
import java.util.List;

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
    private RevistaController rc;

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
        rc = new RevistaController(new RevistaDao(view.getContext()));
        btnInserirRevista.setOnClickListener(op -> {
            try {
                inserir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnBuscarRevista.setOnClickListener(op -> {
            try {
                buscar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnAlterarRevista.setOnClickListener(op -> {
            try {
                alterar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnExcluirRevista.setOnClickListener(op -> {
            try {
                excluir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnListarRevista.setOnClickListener(op -> {
            try {
                listar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void inserir() throws SQLException {
        Revista r = montaRevista();
        rc.insert(r);
        Toast.makeText(view.getContext(), "Revista inserida com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();

    }

    private void buscar() throws SQLException {
        Revista r = montaRevista();
        r = rc.findOne(r);
        preencheCampos(r);
    }

    private void alterar() throws SQLException {
        Revista r = montaRevista();
        rc.update(r);
        Toast.makeText(view.getContext(), "Revista alterada com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void excluir() throws SQLException {
        Revista r = montaRevista();
        rc.delete(r);
        Toast.makeText(view.getContext(), "Revista excluída com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void listar() throws SQLException {
        List<Revista> revistas = rc.findAll();
        StringBuffer buffer = new StringBuffer();
        for (Revista r : revistas){
            buffer.append(r.toString()+"\n");
        }
        tvRevista.setText(buffer.toString());
    }

    private Revista montaRevista(){
        Revista r = new Revista();
        r.setCodigo(Integer.parseInt(etCodigoRevista.getText().toString()));
        r.setNome(etNomeRevista.getText().toString());
        if (etQtdPaginasRevista.getText().toString().equals("")){
            r.setQtdPaginas(0);
        } else {
            r.setQtdPaginas(Integer.parseInt(etQtdPaginasRevista.getText().toString()));
        }
        r.setISSN(etIssnRevista.getText().toString());
        return r;
    }

    private void preencheCampos(Revista r){
        etCodigoRevista.setText(String.valueOf(r.getCodigo()));
        etNomeRevista.setText(r.getNome());
        etQtdPaginasRevista.setText(String.valueOf(r.getQtdPaginas()));
        etIssnRevista.setText(r.getISSN());
    }

    private void limpaCampos(){
        etCodigoRevista.setText("");
        etNomeRevista.setText("");
        etQtdPaginasRevista.setText("");
        etIssnRevista.setText("");
        tvRevista.setText("");
    }
}