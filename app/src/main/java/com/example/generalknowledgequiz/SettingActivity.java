package com.example.generalknowledgequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private Switch switchMusic;
    private Switch switchMode;

    protected GeneralClass app;

    RatingBar ratingBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app = (GeneralClass) getApplication();
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        switchMode = findViewById(R.id.switch_mode);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switchMode.setChecked(true);
        }
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(getApplicationContext(), "Night mode", Toast.LENGTH_SHORT).show();
                    restartApp();

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(getApplicationContext(), "Day mode", Toast.LENGTH_SHORT).show();
                    restartApp();

                }
            }
        });


        //muzika
        switchMusic = findViewById(R.id.switch_music);

        if (app.getMedia_eletronicloop_state()) {
            switchMusic.setChecked(true);
        } else {
            switchMusic.setChecked(false);
        }
        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Music on", Toast.LENGTH_SHORT).show();
                    app.setMedia_eletronicloop_state(true);
                    app.musicPlay();

                } else {
                    Toast.makeText(getApplicationContext(), "Music off", Toast.LENGTH_SHORT).show();
                    app.setMedia_eletronicloop_state(false);
                    app.musicStop();
                }
            }
        }
        );
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        button = (Button) findViewById(R.id.button);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(SettingActivity.this, "Stars: " + rating, Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "Stars: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }

        });


    }

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(i);
        finish();
    }


}


