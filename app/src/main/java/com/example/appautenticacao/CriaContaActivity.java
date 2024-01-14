package com.example.appautenticacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appautenticacao.databinding.ActivityCriaContaBinding;

import java.util.Objects;

public class CriaContaActivity extends AppCompatActivity {

    private ActivityCriaContaBinding binding;

    String[] documentos = {"CPF", "RG", "CNPJ"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterDocumentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        autoCompleteTextView = binding.autoCompleteTxt;
        adapterDocumentos = new ArrayAdapter<>(this, R.layout.lista_documentos, documentos);
        autoCompleteTextView.setAdapter(adapterDocumentos);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            if (Objects.equals(adapterDocumentos.getItem(position), "CPF")) {
                binding.editDocumentoCpf.setVisibility(View.VISIBLE);
                binding.editDocumentoCpf.setHint("XXX.XXX.XXX-XX");
            } else if (Objects.equals(adapterDocumentos.getItem(position), "RG")) {
                binding.editDocumentoRg.setVisibility(View.VISIBLE);
                binding.editDocumentoRg.setHint("XX.XXX.XXX-X");
            } else {
                binding.editDocumentoCnpj.setVisibility(View.VISIBLE);
                binding.editDocumentoCnpj.setHint("XX.XXX.XXX/XXXX-XX");
            }
        });
    }

}