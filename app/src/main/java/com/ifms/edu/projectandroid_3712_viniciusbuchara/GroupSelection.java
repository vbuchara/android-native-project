package com.ifms.edu.projectandroid_3712_viniciusbuchara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GroupSelection extends BaseAppCompactActivity {

    RadioGroup groupSelector;
    ImageView groupImage;
    TextView groupLabel;
    Button choose;

    private static Matrix matrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groupSelector = (RadioGroup) findViewById(R.id.groupSelector);
        groupImage = findViewById(R.id.kokoImage);
        groupLabel = findViewById(R.id.label);
        choose = findViewById(R.id.choose);

        groupImage.setScaleType(ImageView.ScaleType.MATRIX);

        choose.setOnClickListener((View view) -> {
            int groupSelected = groupSelector.getCheckedRadioButtonId();

            groupLabel.setVisibility(View.VISIBLE);

            switch(groupSelected){
                case R.id.radioBuddhism:
                    Drawable byak = getDrawable(R.drawable.byak_aocf);

                    setMatrixImage(0.4f, 0.05f, byak);
                    groupImage.setImageResource(R.drawable.byak_aocf);
                    groupLabel.setText("Good! :)");
                break;

                case R.id.radioTaoism:
                    Drawable miko = getDrawable(R.drawable.miko_aocf);

                    setMatrixImage(0.425f, 0.1f, miko);
                    groupImage.setImageResource(R.drawable.miko_aocf);
                    groupLabel.setText("Taoist nooooo >:(");
                break;

                case R.id.radioShinto:
                    Drawable reimu = getDrawable(R.drawable.reimu_aocf);

                    setMatrixImage(0.5f, 0.15f, reimu);
                    groupImage.setImageResource(R.drawable.reimu_aocf);
                    groupLabel.setText("No money for you!");
                break;
            }
        });
    }

    private void setMatrixImage(float xpos, float ypos, Drawable image){
        Matrix matrix = new Matrix();

        Integer dWidth = image.getIntrinsicWidth();
        Integer dHeight = image.getIntrinsicHeight();

        Integer vWidth = groupImage.getMeasuredWidth();
        Integer vHeight = groupImage.getMeasuredHeight();

        matrix.setTranslate(
            Math.round((vWidth - dWidth) * xpos),
            Math.round((vHeight - dHeight) * ypos)
        );

        groupImage.setImageMatrix(matrix);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_group_selection;
    }

}