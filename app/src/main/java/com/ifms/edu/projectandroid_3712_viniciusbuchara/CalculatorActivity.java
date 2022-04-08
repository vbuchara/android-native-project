package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ifms.edu.projectandroid_3712_viniciusbuchara.erros.DivisionByZeroException;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class CalculatorActivity extends BaseAppCompactActivity {

    private static final DecimalFormat round = new DecimalFormat("0.00");

    private String welcomeText;
    private MaterialTextView result;
    private TextView clear, welcome;
    private TextInputLayout number1_layout, number2_layout;
    private TextInputEditText number1, number2;
    private Button btnSum, btnSub, btnTimes, btnDiv;

    ColorStateList originalNumberInputColorState, originalNumberHintColorState;
    int originalNumberHelperColor;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        setWelcomeMsg(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcome = findViewById(R.id.welcome);
        welcomeText = welcome.getText().toString();

        setWelcomeMsg(getIntent());

        number1 = findViewById(R.id.number1);
        number1_layout = findViewById(R.id.number1_layout);
        number2 = findViewById(R.id.number2);
        number2_layout = findViewById(R.id.number2_layout);
        result = findViewById(R.id.result);
        clear = findViewById(R.id.clear);

        getInputOriginalColor();

        btnSum = findViewById(R.id.sum);
        btnSub = findViewById(R.id.sub);
        btnTimes = findViewById(R.id.times);
        btnDiv = findViewById(R.id.division);

        btnSum.setOnClickListener(doMath);
        btnSub.setOnClickListener(doMath);
        btnTimes.setOnClickListener(doMath);
        btnDiv.setOnClickListener(doMath);
        clear.setOnClickListener((View text) -> {
            number1.setText("");
            number2.setText("");
            result.setText("");
        });
    }

    Button.OnClickListener doMath = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                Double number1Value = Double.parseDouble(number1.getText().toString());
                Double number2Value = Double.parseDouble(number2.getText().toString());
                Button btn = (Button) view;

                switch(btn.getText().toString()){
                    case "+":
                        String resultSum = round.format(number1Value + number2Value)
                                .replace(',', '.');
                        result.setText(resultSum);
                        handleResetField(number1_layout, number1);
                        handleResetField(number2_layout, number2);
                    break;

                    case "-":
                        String resultSub = round.format(number1Value - number2Value)
                                .replace(',', '.');
                        result.setText(resultSub);
                        handleResetField(number1_layout, number1);
                        handleResetField(number2_layout, number2);
                    break;

                    case "x":
                        String resultTimes = round.format(number1Value * number2Value)
                                .replace(',', '.');
                        result.setText(resultTimes);
                        handleResetField(number1_layout, number1);
                        handleResetField(number2_layout, number2);
                    break;

                    case "÷":
                        if(number2Value == 0) {
                            throw new DivisionByZeroException();
                        }

                        String resultDiv = round.format(number1Value / number2Value)
                                .replace(',', '.');
                        result.setText(resultDiv);
                        handleResetField(number1_layout, number1);
                        handleResetField(number2_layout, number2);
                    break;
                }

            } catch(NullPointerException | NumberFormatException error){
               checkEmptyField(number1_layout, number1);
               checkEmptyField(number2_layout, number2);
            } catch(DivisionByZeroException error) {
                checkEmptyField(number1_layout, number1);
                handleTextInputLayoutError(number2_layout, "Sem Divisão por 0!");
            }
        }
    };

    private void setWelcomeMsg(Intent calculator){
        try {
            welcome.setVisibility(View.VISIBLE);

            String charName = (String) calculator.getSerializableExtra("charName");
            if(charName == null){
                throw new Exception("Null!");
            }

            welcome.setText(MessageFormat.format(welcomeText, charName));
        } catch(Exception error) {
            welcome.setVisibility(View.INVISIBLE);
        }
    }

    private void checkEmptyField(
        TextInputLayout textInputLayout,
        TextInputEditText textInput
    ){
        if(textInput.getText() == null || textInput.getText().toString().isEmpty()){
            handleTextInputLayoutError(textInputLayout, "Campo Vazio!");
        } else {
            handleResetField(textInputLayout, textInput, false);
        }
    }

    private void handleTextInputLayoutError(TextInputLayout errorField, String message) {
        int errorColorInt = getColor(R.color.red_700);
        int[][] colorStates = new int[][] {
                new int [] { android.R.attr.state_focused },
                new int [] { -android.R.attr.state_focused }
        };
        int[] colors = new int[] {
                errorColorInt,
                errorColorInt
        };

        ColorStateList errorColorStateList = new ColorStateList(colorStates, colors);

        errorField.setBoxStrokeColorStateList(errorColorStateList);
        errorField.setHintTextColor(getColorStateList(R.color.red_700));

        errorField.setHelperText(message);
        errorField.setHelperTextEnabled(true);
    }

    private void handleResetField(
        TextInputLayout textInputLayout,
        TextInputEditText textInput
    ){
        handleResetField(textInputLayout, textInput, true);
    }

    private void handleResetField(
        TextInputLayout textInputLayout,
        TextInputEditText textInput,
        Boolean resetText
    ){
        if(resetText){
            textInput.setText("");
        }

        textInputLayout.setBoxStrokeColorStateList(originalNumberInputColorState);
        textInputLayout.setHintTextColor(originalNumberHintColorState);

        textInputLayout.setHelperTextEnabled(false);
    }

    private void getInputOriginalColor() {
        originalNumberInputColorState = new ColorStateList(new int[][] {
                new int [] { android.R.attr.state_focused },
                new int [] { -android.R.attr.state_focused }
        }, new int[] {
                number1_layout.getBoxStrokeColor(),
                com.google.android.material.R.color.mtrl_outlined_stroke_color
        });

        originalNumberHelperColor = number1_layout.getHelperTextCurrentTextColor();
        originalNumberHintColorState = number1_layout.getHintTextColor();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_calculator;
    }
}