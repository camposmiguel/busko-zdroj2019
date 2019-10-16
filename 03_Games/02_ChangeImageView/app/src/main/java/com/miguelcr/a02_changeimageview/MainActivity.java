package com.miguelcr.a02_changeimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewCloud;
    TextView textViewPlayer;

    boolean download = false;
    boolean isPlayingPlayer1 = true;
    String player1Name = "Konrad";
    String player2Name = "Mikolaj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the references
        imageViewCloud = findViewById(R.id.image_view_cloud);
        textViewPlayer = findViewById(R.id.text_view_player);

        // Set default player
        textViewPlayer.setText(player1Name);

        // Click listener
        imageViewCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlayingPlayer1) {
                    imageViewCloud.setImageResource(R.drawable.ic_android);
                    textViewPlayer.setText("Playing: " + player2Name);
                } else {
                    imageViewCloud.setImageResource(R.drawable.ic_apple_logo);
                    textViewPlayer.setText("Playing: " + player1Name);
                }

                // Change the boolean value for the next time
                isPlayingPlayer1 = !isPlayingPlayer1;
            }
        });
    }
}
