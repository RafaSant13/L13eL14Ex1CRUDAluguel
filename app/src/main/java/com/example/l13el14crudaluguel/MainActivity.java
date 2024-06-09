package com.example.l13el14crudaluguel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            carregaFragment(bundle);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, new HomeFragment());
            ft.commit();
        }
    }

    private void carregaFragment(Bundle bundle) {
        String tipo = bundle.getString("tipo");
        if (tipo.equals("home")){
            fragment = new HomeFragment();
        }
        else if (tipo.equals("aluguel")){
            fragment = new AluguelFragment();
        }
        else if (tipo.equals("aluno")){
            fragment = new AlunoFragment();
        }
        else if (tipo.equals("livro")){
            fragment = new LivroFragment();
        }
        else {
            fragment = new RevistaFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent i = new Intent(this, MainActivity.class);

        if (id == R.id.itemHome){
            bundle.putString("tipo", "home");
            i.putExtras(bundle);
            this.startActivity(i);
            this.finish();
            return true;
        } else if (id == R.id.itemAluguel){
            bundle.putString("tipo", "aluguel");
            i.putExtras(bundle);
            this.startActivity(i);
            this.finish();
            return true;
        } else if (id == R.id.itemAluno){
            bundle.putString("tipo", "aluno");
            i.putExtras(bundle);
            this.startActivity(i);
            this.finish();
            return true;
        } else if (id == R.id.itemLivro){
            bundle.putString("tipo", "livro");
            i.putExtras(bundle);
            this.startActivity(i);
            this.finish();
            return true;
        } else {
            bundle.putString("tipo", "revista");
            i.putExtras(bundle);
            this.startActivity(i);
            this.finish();
            return true;
        }

    }
}