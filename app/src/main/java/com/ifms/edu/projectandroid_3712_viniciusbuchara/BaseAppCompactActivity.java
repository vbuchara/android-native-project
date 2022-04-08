package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseAppCompactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        Toolbar toolbar = findViewById(R.id.appBar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_more_vert_24));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected abstract int getLayoutResourceId();

    protected HashSet<Integer> getOverrideMenuOptions() {
        return new HashSet<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Set<Integer> overrideMenuOptions = getOverrideMenuOptions();

        switch (item.getItemId()){
            case R.id.chars:
                if(overrideMenuOptions.contains(R.id.chars)) break;

                Intent charSelection = new Intent(getApplicationContext(), CharacterSelection.class);
                charSelection.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(charSelection);
            break;

            case R.id.list:
                if(overrideMenuOptions.contains(R.id.list)) break;

                Intent list = new Intent(getApplicationContext(), ListThingsActivity.class);
                list.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(list);
            break;

            case R.id.religion:
                if(overrideMenuOptions.contains(R.id.religion)) break;

                Intent religion = new Intent(getApplicationContext(), GroupSelection.class);
                religion.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(religion);
            break;

            case R.id.koko_moods:
                if(overrideMenuOptions.contains(R.id.koko_moods)) break;

                Intent koko = new Intent(getApplicationContext(), KokoMoodsActivity.class);
                koko.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(koko);
            break;

            case R.id.calculator:
                if(overrideMenuOptions.contains(R.id.calculator)) break;

                Intent calculator = new Intent(getApplicationContext(), CalculatorActivity.class);
                calculator.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(calculator);
            break;

            case R.id.seija_room:
                if(overrideMenuOptions.contains(R.id.seija_room)) break;

                Intent seija = new Intent(getApplicationContext(), SeijaRoomActivity.class);
                seija.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(seija);
            break;

            case R.id.converter:
                if(overrideMenuOptions.contains(R.id.converter)) break;

                Intent converter = new Intent(getApplicationContext(), FumoCarActivity.class);
                converter.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(converter);
            break;
        }

        return super.onOptionsItemSelected(item);
    }
}