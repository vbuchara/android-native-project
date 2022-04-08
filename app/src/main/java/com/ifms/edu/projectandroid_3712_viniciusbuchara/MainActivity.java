package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText loginInput;
    TextInputEditText senhaInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loginInput = findViewById(R.id.TextInputLogin);
        senhaInput = findViewById(R.id.TextInputSenha);
    }

    public void submit(View view){
        String login = loginInput.getText().toString();
        String senha = senhaInput.getText().toString();

        if(login.equals("Shou") && senha.equals("pagoda")){
            Toast.makeText(getBaseContext(), "You're Shou!", Toast.LENGTH_LONG).show();
            loginInput.setText("");
            senhaInput.setText("");

            Intent characterSelection = new Intent(getApplicationContext(), CharacterSelection.class);
            startActivity(characterSelection);

            finish();
        }
    }
}