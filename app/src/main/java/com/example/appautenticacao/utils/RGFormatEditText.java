package com.example.appautenticacao.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import java.util.Objects;

public class RGFormatEditText extends AppCompatEditText {

    public RGFormatEditText(Context context) {
        super(context);
        init();
    }

    public RGFormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RGFormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        addTextChangedListener(new RGFormatTextWatcher());
    }

    private class RGFormatTextWatcher implements TextWatcher {

        private static final int RG_LENGTH = 9;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            removeTextChangedListener(this);

            String rg = editable.toString().replaceAll("\\D", "");

            if (rg.length() == RG_LENGTH) {
                rg = rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8);
            } else {
                Toast.makeText(getContext(), "RG inválido.", Toast.LENGTH_SHORT).show();
            }

            setText(rg);
            setSelection(Objects.requireNonNull(getText()).length());

            addTextChangedListener(this);
        }
    }
}