package com.ifms.edu.projectandroid_3712_viniciusbuchara.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ifms.edu.projectandroid_3712_viniciusbuchara.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FumoCarTextWatcher implements TextWatcher {

    private EditText firstInput;
    private EditText secondInput;

    private Double value;

    public FumoCarTextWatcher(EditText firstInput, EditText secondInput) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Editable firstInputValue = firstInput.getText();
        Editable secondInputValue = secondInput.getText();

        if(checkNotNullOrNotEmpty(firstInputValue) && firstInput.hasFocus()){
            value = Double.valueOf(firstInputValue.toString());

            BigDecimal valueFormatted = new BigDecimal(value * 3.6)
                    .setScale(2, RoundingMode.HALF_UP);

            setSafetyText(secondInput, String.valueOf(valueFormatted));
            return;
        }

        if(checkNotNullOrNotEmpty(secondInputValue) && secondInput.hasFocus()){
            value = Double.valueOf(secondInputValue.toString());

            BigDecimal valueFormatted = new BigDecimal(value / 3.6)
                    .setScale(2, BigDecimal.ROUND_CEILING);

            setSafetyText(firstInput, String.valueOf(valueFormatted));
            return;
        }

        if(!checkNotNullOrNotEmpty(firstInputValue) || !checkNotNullOrNotEmpty(secondInputValue)){
            setSafetyText(firstInput, "");
            setSafetyText(secondInput, "");
        }
    }

    private void setSafetyText(EditText input, String text) {
        input.removeTextChangedListener(this);
        input.setText(text);
        input.addTextChangedListener(this);
    }

    private boolean checkNotNullOrNotEmpty(Editable inputValue) {
        return inputValue != null && !inputValue.toString().isEmpty();
    }
}
