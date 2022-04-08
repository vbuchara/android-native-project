package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.ifms.edu.projectandroid_3712_viniciusbuchara.util.FumoCarTextWatcher;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

public class FumoCarActivity extends BaseAppCompactActivity {

    private ImageView fumoCar;
    private TextView label;

    private TextInputLayout layoutMetrePerSecond, layoutKilometrePerHour;
    private TextInputEditText inputMetrePerSecond, inputKilometrePerHour;

    private Double metrePerSecond, kilometrePerSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        label = findViewById(R.id.label);
        fumoCar = findViewById(R.id.fumoCar);

        layoutMetrePerSecond = findViewById(R.id.layoutMetrePerSeconds);
        inputMetrePerSecond = findViewById(R.id.inputMetrePerSeconds);

        layoutKilometrePerHour = findViewById(R.id.layoutKilometrePerHour);
        inputKilometrePerHour = findViewById(R.id.inputKilometrePerHour);

        TextWatcher fumorCarTextWatcher = new FumoCarTextWatcher(
                inputMetrePerSecond,
                inputKilometrePerHour
        );

        inputMetrePerSecond.addTextChangedListener(fumorCarTextWatcher);
        inputKilometrePerHour.addTextChangedListener(fumorCarTextWatcher);

        KeyboardVisibilityEvent.setEventListener(
                FumoCarActivity.this,
                FumoCarActivity.this,
                (isOpen) -> {
                    if(isOpen){
                        label.setVisibility(View.GONE);
                        return;
                    }
                    label.setVisibility(View.VISIBLE);
                }
        );
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_fumo_car;
    }
}