package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.appcompat.app.AlertDialog;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListThingsActivity extends ListActivity {

    String[] thingsList = new String[] {
            "Login", "Characters", "Calculator", "Kokoro", "Religiões", "Seija"
    };
    ArrayAdapter<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thingsList);

        setListAdapter(list);
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        switch(position){
            case 0:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Indo para Login");
                dialog.setMessage("Tem certeza que deseja ir para login?");
                dialog.setPositiveButton("Sim", (DialogInterface dialogInterface, int i) -> {
                    Intent login = new Intent(ListThingsActivity.this, MainActivity.class);
                    startActivity(login);
                    finishAffinity();
                });
                dialog.setNegativeButton("Não", null);
                dialog.show();
            break;

            case 1:
                Intent characters = new Intent(getApplicationContext(), CharacterSelection.class);
                characters.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(characters);
            break;

            case 2:
                Intent calculator = new Intent(getApplicationContext(), CalculatorActivity.class);
                calculator.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(calculator);
            break;

            case 3:
                Intent koko = new Intent(getApplicationContext(), KokoMoodsActivity.class);
                koko.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(koko);
            break;

            case 4:
                Intent groups = new Intent(getApplicationContext(), GroupSelection.class);
                groups.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(groups);
            break;

            case 5:
                Intent seija = new Intent(getApplicationContext(), SeijaRoomActivity.class);
                seija.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(seija);
            break;
        }
    }
}