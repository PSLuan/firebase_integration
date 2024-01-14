package com.example.appautenticacao.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class CNPJFormatEditText extends AppCompatEditText {

    public CNPJFormatEditText(Context context) {
        super(context);
        init();
    }

    public CNPJFormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CNPJFormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new CNPJFormatTextWatcher());
    }

    private class CNPJFormatTextWatcher implements TextWatcher {

        private static final int CNPJ_LENGTH = 14;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            removeTextChangedListener(this);

            String cnpj = editable.toString().replaceAll("\\D", "");

            if (cnpj.length() == CNPJ_LENGTH) {
                cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." +
                        cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
            } else {
                Toast.makeText(getContext(), "CNPJ inválido", Toast.LENGTH_SHORT).show();
            }

            setText(cnpj);
            setSelection(Objects.requireNonNull(getText()).length());

            addTextChangedListener(this);
        }
    }
}