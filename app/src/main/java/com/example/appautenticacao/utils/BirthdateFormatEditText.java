package com.example.appautenticacao.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class BirthdateFormatEditText extends AppCompatEditText {

    public BirthdateFormatEditText(Context context) {
        super(context);
        init();
    }

    public BirthdateFormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BirthdateFormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new BirthdateFormatTextWatcher());
    }

    private class BirthdateFormatTextWatcher implements TextWatcher {

        private static final int BIRTHDATE_LENGTH = 8;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            removeTextChangedListener(this);

            String birthdate = editable.toString().replaceAll("\\D", "");

            if (birthdate.length() == BIRTHDATE_LENGTH) {
                birthdate = birthdate.substring(0, 2) + "/" + birthdate.substring(2, 4) + "/" + birthdate.substring(4);
            } else {
                Toast.makeText(getContext(), "Data de nascimento inválida.", Toast.LENGTH_SHORT).show();
            }

            setText(birthdate);
            setSelection(Objects.requireNonNull(getText()).length());

            addTextChangedListener(this);
        }
    }
}