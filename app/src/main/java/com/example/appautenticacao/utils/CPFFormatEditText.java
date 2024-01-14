package com.example.appautenticacao.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class CPFFormatEditText extends AppCompatEditText {

    public CPFFormatEditText(Context context) {
        super(context);
        init();
    }

    public CPFFormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CPFFormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new CPFFormatTextWatcher());
    }

    private class CPFFormatTextWatcher implements TextWatcher {

        private static final int CPF_LENGTH = 11;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            removeTextChangedListener(this);

            String cpf = editable.toString().replaceAll("\\D", "");

            if (cpf.length() == CPF_LENGTH) {
                cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
            } else {
                Toast.makeText(getContext(), "CPF inválido.", Toast.LENGTH_SHORT).show();
            }

            setText(cpf);
            setSelection(Objects.requireNonNull(getText()).length());

            addTextChangedListener(this);
        }
    }
}