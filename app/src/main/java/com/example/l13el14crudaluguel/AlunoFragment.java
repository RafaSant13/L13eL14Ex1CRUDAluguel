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

import com.example.l13el14crudaluguel.controller.AlunoController;
import com.example.l13el14crudaluguel.model.Aluno;
import com.example.l13el14crudaluguel.persistance.AlunoDao;

import java.sql.SQLException;
import java.util.List;

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
    private AlunoController ac;

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
        ac = new AlunoController(new AlunoDao(view.getContext()));
        tvAluno.setMovementMethod(new ScrollingMovementMethod());
        btnInserirAluno.setOnClickListener(op -> {
            try {
                inserir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnBuscarAluno.setOnClickListener(op -> {
            try {
                buscar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnAlterarAluno.setOnClickListener(op -> {
            try {
                alterar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnExcluirAluno.setOnClickListener(op -> {
            try {
                excluir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnListarAluno.setOnClickListener(op -> {
            try {
                listar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void inserir() throws SQLException {
        Aluno a = montaAluno();
        ac.insert(a);
        Toast.makeText(view.getContext(), "Aluno inserido com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void buscar() throws SQLException {
        Aluno a = montaAluno();
        a = ac.findOne(a);
        preencheCampos(a);
    }

    private void alterar() throws SQLException {
        Aluno a = montaAluno();
        ac.update(a);
        Toast.makeText(view.getContext(), "Aluno alterado com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void excluir() throws SQLException {
        Aluno a = montaAluno();
        ac.delete(a);
        Toast.makeText(view.getContext(), "Aluno excluído com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void listar() throws SQLException {
        List<Aluno> alunos = ac.findAll();
        StringBuffer buffer = new StringBuffer();
        for (Aluno a : alunos){
            buffer.append(a.toString()+"\n");
        }
        tvAluno.setText(buffer.toString());
    }

    private Aluno montaAluno(){
        Aluno a = new Aluno();
        a.setRa(Integer.parseInt(etRaAluno.getText().toString()));
        a.setNome(etNomeAluno.getText().toString());
        a.setEmail(etEmailAluno.getText().toString());
        return a;
    }

    private void preencheCampos(Aluno a){
        etRaAluno.setText(String.valueOf(a.getRa()));
        etNomeAluno.setText(a.getNome());
        etEmailAluno.setText(a.getEmail());
    }

    private void limpaCampos(){
        etRaAluno.setText("");
        etNomeAluno.setText("");
        etEmailAluno.setText("");
        tvAluno.setText("");
    }
}