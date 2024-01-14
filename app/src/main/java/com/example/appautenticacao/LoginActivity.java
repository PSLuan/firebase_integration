package com.example.appautenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appautenticacao.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance(); //comunicacação com firebase

        binding.textCadastro.setOnClickListener(v -> startActivity(new Intent(this, CadastroActivity.class)));

        binding.textRecuperaConta.setOnClickListener(v ->
                startActivity(new Intent(this, RecuperaContaActivity.class)));

        binding.btnLogin.setOnClickListener(v -> validaDados());
    }

    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editEmail.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty())
            Toast.makeText(this, "Informe seu e-mail e senha.", Toast.LENGTH_SHORT).show();

        binding.progressBar.setVisibility(View.VISIBLE);

        loginFirebase(email, senha);
    }

    private void loginFirebase(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, CriaContaActivity.class));
            } else {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
            Objects.requireNonNull(task.getResult().getUser()).getIdToken(true)
                    .addOnSuccessListener(getTokenResult ->
                            Log.i(null, Objects.requireNonNull(getTokenResult.getToken())));
        });
    }
}