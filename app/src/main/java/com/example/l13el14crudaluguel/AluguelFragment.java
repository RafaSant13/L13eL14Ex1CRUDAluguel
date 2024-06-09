package com.example.l13el14crudaluguel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l13el14crudaluguel.controller.AluguelController;
import com.example.l13el14crudaluguel.controller.AlunoController;
import com.example.l13el14crudaluguel.controller.LivroController;
import com.example.l13el14crudaluguel.controller.RevistaController;
import com.example.l13el14crudaluguel.model.Aluguel;
import com.example.l13el14crudaluguel.model.Aluno;
import com.example.l13el14crudaluguel.model.Exemplar;
import com.example.l13el14crudaluguel.model.Livro;
import com.example.l13el14crudaluguel.model.Revista;
import com.example.l13el14crudaluguel.persistance.AluguelDao;
import com.example.l13el14crudaluguel.persistance.AlunoDao;
import com.example.l13el14crudaluguel.persistance.LivroDao;
import com.example.l13el14crudaluguel.persistance.RevistaDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private AluguelController aluguelC;
    private AlunoController alunoC;
    private LivroController lc;
    private RevistaController rc;
    private List<Exemplar> exemplares;
    private List<Aluno> alunos;


    public AluguelFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);
        spAlunoAluguel = view.findViewById(R.id.spAlunoAluguel);
        spExemplarAluguel = view.findViewById(R.id.spExemplarAluguel);
        etRetirada = view.findViewById(R.id.etRetirada);
        etDevolucao = view.findViewById(R.id.etDevolucao);
        btnInserirAluguel = view.findViewById(R.id.btnInserirAluguel);
        btnBuscarAluguel = view.findViewById(R.id.btnBuscarAluguel);
        btnAlterarAluguel = view.findViewById(R.id.btnAlterarAluguel);
        btnExcluirAluguel = view.findViewById(R.id.btnExcluirAluguel);
        btnListarAluguel = view.findViewById(R.id.btnListarAluguel);
        tvAluguel = view.findViewById(R.id.tvAluguel);
        tvAluguel.setMovementMethod(new ScrollingMovementMethod());
        aluguelC = new AluguelController(new AluguelDao(view.getContext()));
        alunoC = new AlunoController(new AlunoDao(view.getContext()));
        lc = new LivroController(new LivroDao(view.getContext()));
        rc = new RevistaController(new RevistaDao(view.getContext()));
        preencheSpinners();
        btnInserirAluguel.setOnClickListener(op -> {
            try {
                inserir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnBuscarAluguel.setOnClickListener(op -> {
            try {
                buscar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnAlterarAluguel.setOnClickListener(op -> {
            try {
                alterar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnExcluirAluguel.setOnClickListener(op -> {
            try {
                excluir();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btnListarAluguel.setOnClickListener(op -> {
            try {
                listar();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void inserir() throws SQLException {
        Aluguel a = montaAluguel();
        aluguelC.insert(a);
        Toast.makeText(view.getContext(), "Aluguel inserido com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void buscar() throws SQLException {
        Aluguel a = montaAluguel();
        a = aluguelC.findOne(a);
        preencheCampos(a);
    }

    private void alterar() throws SQLException {
        Aluguel a = montaAluguel();
        aluguelC.update(a);
        Toast.makeText(view.getContext(), "Aluguel alterado com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void excluir() throws SQLException {
        Aluguel a = montaAluguel();
        aluguelC.delete(a);
        Toast.makeText(view.getContext(), "Aluguel excluído com sucesso!", Toast.LENGTH_LONG).show();
        limpaCampos();
    }

    private void listar() throws SQLException {
        List<Aluguel> alugueis = aluguelC.findAll();
        StringBuffer buffer = new StringBuffer();
        for (Aluguel a : alugueis){
            buffer.append(a.toString()+"\n");
        }
        tvAluguel.setText(buffer.toString());
    }

    private void preencheSpinners() {
        Aluno a = new Aluno();
        a.setRa(0);
        a.setNome("Selecione um aluno");
        a.setEmail("");
        exemplares = new ArrayList<>();
        Revista r0 = new Revista();
        r0.setCodigo(0);
        r0.setNome("Selecione um exemplar");
        r0.setQtdPaginas(0);
        r0.setISSN("");

        try {
            alunos = alunoC.findAll();
            List<Livro> livros = lc.findAll();
            List<Revista> revistas = rc.findAll();
            for (Livro l : livros){
                exemplares.add(l);
            }
            for (Revista r : revistas){
                exemplares.add(r);
            }
            alunos.add(0, a);
            exemplares.add(0, r0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, alunos);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAlunoAluguel.setAdapter(ad);

            ArrayAdapter ad1 = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, exemplares);
            ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spExemplarAluguel.setAdapter(ad1);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Aluguel montaAluguel(){
        Aluguel a = new Aluguel();
        if (etRetirada.getText().toString().equals("")){
            a.setDataRetirada(null);
        } else {
            a.setDataRetirada(LocalDate.parse(etRetirada.getText().toString()));
        }
        if (etDevolucao.getText().toString().equals("")){
            a.setDataDevolucao(null);
        } else {
            a.setDataDevolucao(LocalDate.parse(etDevolucao.getText().toString()));
        }
        a.setAluno((Aluno) spAlunoAluguel.getSelectedItem());
        a.setExemplar((Exemplar) spExemplarAluguel.getSelectedItem());
        return a;
    }

    private void preencheCampos(Aluguel a){
        if (a.getDataRetirada()==null){
            etRetirada.setText("");
        } else {
            etRetirada.setText(a.getDataRetirada().toString());
        }
        if (a.getDataDevolucao()==null){
            etDevolucao.setText("");
        } else{
            etDevolucao.setText(a.getDataDevolucao().toString());
        }
        int contAlunos = 0;
        for (Aluno al: alunos){
            if (al.getRa() == a.getAluno().getRa()){
                spAlunoAluguel.setSelection(contAlunos);
            }
            else {
                contAlunos++;
            }
        }
        if (contAlunos > alunos.size()){
            spAlunoAluguel.setSelection(0);
        }

        int contExemplares = 0;
        for (Exemplar e: exemplares){
            if (e.getCodigo() == a.getExemplar().getCodigo()){
                spExemplarAluguel.setSelection(contExemplares);
            }
            else {
                contExemplares++;
            }
        }
        if (contExemplares > exemplares.size()){
            spExemplarAluguel.setSelection(0);
        }

    }

    private void limpaCampos(){
        etRetirada.setText("");
        etDevolucao.setText("");
        spAlunoAluguel.setSelection(0);
        spExemplarAluguel.setSelection(0);
        tvAluguel.setText("");
    }

}