package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ifms.edu.projectandroid_3712_viniciusbuchara.util.MapAdapter;

public class KokoMoodsActivity extends BaseAppCompactActivity {
    private boolean showToast = true;

    private ImageView kokoImage;
    private CheckBox checkHappy, checkSad, checkAngry, checkFlushed;
    private Spinner emotionSpin;
    private Switch toggleToast;
    private LinkedHashSet<String> checkedText = new LinkedHashSet<String>();
    private Map<String, Integer> emotionIcons = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kokoImage = findViewById(R.id.kokoImage);
        setMatrixImage(0.325f, 0.15f, getDrawable(R.drawable.koko_aocf));

        checkHappy = findViewById(R.id.checkHappy);
        checkSad = findViewById(R.id.checkSad);
        checkAngry = findViewById(R.id.checkAngry);
        checkFlushed = findViewById(R.id.checkFlushed);

        emotionSpin = findViewById(R.id.emotionSpin);
        toggleToast = findViewById(R.id.toggleToast);

        emotionIcons.put("Feliz", R.drawable.emoji_happy);
        emotionIcons.put("Triste", R.drawable.sad_emoji);
        emotionIcons.put("Brava", R.drawable.angry_emoji);
        emotionIcons.put("Envergonhada", R.drawable.flushed_emoji);

        setCheckListeners();

        toggleToast.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            this.showToast = isChecked;
        });

        emotionSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textViewSelected = view.findViewById(R.id.textView);
                String textSelected = textViewSelected.getText().toString();

                switch (textSelected){
                    case "Feliz":
                        adapterView.setBackgroundColor(getColor(R.color.yellow));

                        if(!showToast){
                            break;
                        }

                        String textHappy = "Koko está feliz!";

                        Toast.makeText(getBaseContext(), textHappy,
                                Toast.LENGTH_SHORT).show();
                    break;

                    case "Triste":
                        adapterView.setBackgroundColor(getColor(R.color.light_blue));

                        if(!showToast){
                            break;
                        }

                        String textSad = "Koko está triste!";

                        Toast.makeText(getBaseContext(), textSad,
                                Toast.LENGTH_SHORT).show();
                    break;

                    case "Brava":
                        adapterView.setBackgroundColor(getColor(R.color.light_red_500));

                        if(!showToast){
                            break;
                        }

                        String textAngry = "Koko está brava!";

                        Toast.makeText(getBaseContext(), textAngry,
                                Toast.LENGTH_SHORT).show();
                    break;

                    case "Envergonhada":
                        adapterView.setBackgroundColor(getColor(R.color.pink));

                        if(!showToast){
                            break;
                        }

                        String textFlushed = "Koko está envergonhada!";

                        Toast.makeText(getBaseContext(), textFlushed,
                                Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerList(){
        if(checkedText.size() == 0){
            emotionSpin.setVisibility(View.INVISIBLE);
            return;
        }

        Map<String, Integer> emotions = new LinkedHashMap<String, Integer>();

        checkedText.forEach((String emotion) -> {
            String emotionText = capitalizeString(emotion);
            emotions.put(emotionText, emotionIcons.get(emotionText));
        });

        MapAdapter emotionsList = new MapAdapter(getApplicationContext(), emotions);
        emotionSpin.setAdapter(emotionsList);
        emotionSpin.setVisibility(View.VISIBLE);
    }

    private void setCheckListeners() {
        checkHappy.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                handleShowMoods("feliz", true);
                setSpinnerList();
                return;
            }

            handleShowMoods("feliz", false);
            setSpinnerList();
        });

        checkSad.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                handleShowMoods("triste", true);
                setSpinnerList();
                return;
            }

            handleShowMoods("triste", false);
            setSpinnerList();
        });

        checkAngry.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                handleShowMoods("brava", true);
                setSpinnerList();
                return;
            }

            handleShowMoods("brava", false);
            setSpinnerList();
        });

        checkFlushed.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                handleShowMoods("envergonhada", true);
                setSpinnerList();
                return;
            }

            handleShowMoods("envergonhada", false);
            setSpinnerList();
        });
    }

    private void handleShowMoods(String mood, Boolean add){
        if(add){
            checkedText.add(mood);
        } else {
            checkedText.remove(mood);
        }

        if(checkedText.isEmpty()){
            return;
        }
    }

    private void setMatrixImage(float xpos, float ypos, Drawable image){
        Matrix matrix = new Matrix();

        Integer dWidth = image.getIntrinsicWidth();
        Integer dHeight = image.getIntrinsicHeight();

        Integer vWidth = kokoImage.getMeasuredWidth();
        Integer vHeight = kokoImage.getMeasuredHeight();

        matrix.setTranslate(
                Math.round((vWidth - dWidth) * xpos),
                Math.round((vHeight - dHeight) * ypos)
        );

        kokoImage.setImageMatrix(matrix);
    }

    private String capitalizeString(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_koko_moods;
    }
}