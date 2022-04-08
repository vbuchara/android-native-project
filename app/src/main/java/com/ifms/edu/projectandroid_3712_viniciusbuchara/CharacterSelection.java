package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.HashSet;
import java.util.Set;

public class CharacterSelection extends BaseAppCompactActivity {

    RadioGroup charSelector;
    ImageView charImage;
    RadioButton checkedChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        charSelector = (RadioGroup) findViewById(R.id.charSelector);
        charImage = findViewById(R.id.kokoImage);
        checkedChar = findViewById(R.id.radioNazrin);

        charSelector.setOnCheckedChangeListener((RadioGroup charSelector, int checkedId) -> {
            checkedChar = charSelector.findViewById(checkedId);

                switch(checkedChar.getText().toString()){
                    case "Nazrin":
                        charImage.setImageResource(R.drawable.rat);
                    break;

                    case "Kogasa":
                        charImage.setImageResource(R.drawable.scary);
                    break;

                    case "Ichirin and Uzan":
                        charImage.setImageResource(R.drawable.fist);
                    break;

                    case "Murasa":
                        charImage.setImageResource(R.drawable.ghost_ship);
                    break;

                    case "Shou":
                        charImage.setImageResource(R.drawable.confused_shou);
                    break;

                    case "Byakuren":
                        charImage.setImageResource(R.drawable.byak);
                    break;
                }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_character_selection;
    }

    @Override
    protected HashSet<Integer> getOverrideMenuOptions() {
        HashSet<Integer> overrideMenuOptions = new HashSet<Integer>();
        overrideMenuOptions.add(R.id.calculator);

        return overrideMenuOptions;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calculator:
                Intent calculator = new Intent(getApplicationContext(), CalculatorActivity.class);
                calculator.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                calculator.putExtra("charName", checkedChar.getText().toString());
                startActivity(calculator);
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}