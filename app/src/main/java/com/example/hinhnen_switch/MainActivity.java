package com.example.hinhnen_switch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Switch switch_background;
    ArrayList<Integer> arrayImage;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        switch_background = findViewById(R.id.switch_background);
        linearLayout = findViewById(R.id.myLinearlayout);
        preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        arrayImage = new ArrayList<>();
        arrayImage.add(R.drawable.img);
        arrayImage.add(R.drawable.img_1);
        arrayImage.add(R.drawable.img_2);
        arrayImage.add(R.drawable.img_3);

        boolean isRandomEnabled = preferences.getBoolean("isRandomEnabled", false);
        switch_background.setChecked(isRandomEnabled);

        if(isRandomEnabled){
            setRandomBackground();
        }

        switch_background.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("isRandomEnabled", isChecked).apply();
            if (isChecked) {
                setRandomBackground();
            } else {
                linearLayout.setBackgroundResource(R.drawable.img);
            }
        });
    }
    private void setRandomBackground(){
        Random random = new Random();
        int vt = random.nextInt(arrayImage.size());
        linearLayout.setBackgroundResource(arrayImage.get(vt));
    }
}
