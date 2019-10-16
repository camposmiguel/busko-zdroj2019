package com.miguelcr.a01_duckhunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView textViewNick, textViewCounter, textViewTimer;
    ImageView imageViewDuck;

    int counter = 0;
    Random rand;
    String nick;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Init Random object
        rand = new Random();


        // Get the information from the Intent
        Bundle extras = getIntent().getExtras();
        nick = extras.getString("nickname");
        time = extras.getLong("time");

        // Get the references
        textViewNick = findViewById(R.id.text_view_nick);
        textViewCounter = findViewById(R.id.text_view_counter);
        textViewTimer = findViewById(R.id.text_view_timer);
        imageViewDuck = findViewById(R.id.image_view_duck);

        // Set nickname
        textViewNick.setText(nick);

        // Start countdown timer
        startCountdown();

        // Duck click listener
        imageViewDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increase the counter
                counter++;
                textViewCounter.setText(String.valueOf(counter));

                // Change the duck to another different random position
                randomDuckPosition();

            }
        });
    }

    private void startCountdown() {
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                textViewTimer.setText("0s");
                openGameOverDialog();
            }
        }.start();

    }

    private void openGameOverDialog() {
        // 1. Create de dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

        // 2. Builder: title + message
        builder.setTitle("Game is over")
                .setMessage("You've hunted " + counter + " ducks. Do you want to play again?");
        builder.setCancelable(false);

        builder.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                restartGame();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                finish();
            }
        });

        // 3. Create the Dialog with the Builder config
        AlertDialog dialog = builder.create();
        // 4. Show the dialog
        dialog.show();
    }

    private void restartGame() {
        randomDuckPosition();
        startCountdown();
        counter = 0;
        textViewCounter.setText("0");
    }

    private void randomDuckPosition() {
        //  Get screen size dynamically
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Get duck size
        int duckX = imageViewDuck.getWidth();
        int duckY = imageViewDuck.getHeight();

        int maxX = size.x - duckX;
        int maxY = size.y - duckY;
        int min = 0;

        int randomX = rand.nextInt((maxX - min) + 1) + min;
        int randomY = rand.nextInt((maxY - min) + 1) + min;

        // Move the duck image to the new random position
        imageViewDuck.setX(randomX);
        imageViewDuck.setY(randomY);

    }
}
