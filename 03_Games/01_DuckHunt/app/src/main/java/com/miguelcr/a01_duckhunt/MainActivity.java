package com.miguelcr.a01_duckhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText editTextNick;
    Button buttonStart;
    RadioGroup radioGroupTime;
    RadioButton radioButton60, radioButton30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the toolbar
        getSupportActionBar().hide();

        // Get the reference for the view components
        editTextNick = findViewById(R.id.edit_text_nick);
        buttonStart = findViewById(R.id.button_start_game);
        radioGroupTime = findViewById(R.id.radio_group_time);
        radioButton30 = findViewById(R.id.radio_button_30);
        radioButton60 = findViewById(R.id.radio_button_60);

        radioButton60.setChecked(true);

        // Define click event
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1. Get the nickname
                String nick = editTextNick.getText().toString();

                // 2. Check if nick is not empty
                if(nick.isEmpty()) {
                    editTextNick.setError("Write a nickname please");
                } else {
                    int idSelected = radioGroupTime.getCheckedRadioButtonId();

                    // continue to the GameActivity
                    Intent i = new Intent(MainActivity.this, GameActivity.class);

                    i.putExtra("nickname", nick);
                    if(idSelected == R.id.radio_button_30) {
                        i.putExtra("time", 30000l);
                    } else {
                        i.putExtra("time", 60000l);
                    }

                    startActivity(i);
                }

            }
        });
    }
}
