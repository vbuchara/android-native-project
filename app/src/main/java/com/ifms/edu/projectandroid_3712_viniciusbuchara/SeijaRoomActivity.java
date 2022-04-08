package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class SeijaRoomActivity extends BaseAppCompactActivity {

    private MaterialTextView seijaText;
    private TextView labelSeijaRoom;
    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;

    private String labelText;

    private final Handler inputHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        labelSeijaRoom = findViewById(R.id.labelSeijaRoom);
        labelText = labelSeijaRoom.getText().toString();

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputEditText = findViewById(R.id.textInput);
        seijaText = findViewById(R.id.seijaText);

        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int before) {
                String text = String.valueOf(charSequence);

                StringBuilder reverseSeijaText = new StringBuilder();
                StringBuilder reverseLabelText = new StringBuilder();

                reverseLabelText.append(labelText);
                reverseLabelText.reverse();

                reverseSeijaText.append(text);
                reverseSeijaText.reverse();

                labelSeijaRoom.setText(reverseLabelText);
                seijaText.setText(reverseSeijaText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputHandler.removeCallbacksAndMessages(null);
                inputHandler.postDelayed(afterUserStoppedTyping, 200);
            }

            Runnable afterUserStoppedTyping = new Runnable() {
                @Override
                public void run() {
                    labelSeijaRoom.setText(labelText);
                }
            };
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if ( view instanceof EditText) {
                Rect outRect = new Rect();
                view.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_seija_room;
    }
}