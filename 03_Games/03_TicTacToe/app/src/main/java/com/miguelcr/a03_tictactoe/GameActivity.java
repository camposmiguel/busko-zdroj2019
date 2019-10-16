package com.miguelcr.a03_tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    boolean isPlayingPlayer1 = true;
    ImageView iv0, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;
    String p1Name, p2Name;
    TextView tvPlayer;
    boolean gameOver = false;

    //                     0 1 2 3 4 5 6 7 8
    int[] selectedCells = {0,0,0,0,0,0,0,0,0};

    // 0 > cell is free
    // 1 > cell was clicked by player 1
    // 2 > cell was clicked by player 2

    //     CELLS positions
    /*
         0 | 1 | 2
         ----------
         3 | 4 | 5
         ----------
         6 | 7 | 8
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        iv0 = findViewById(R.id.imageView0);
        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        tvPlayer = findViewById(R.id.textViewPlayer);

        // get the players name
        Bundle extras = getIntent().getExtras();
        p1Name = extras.getString("P1");
        p2Name = extras.getString("P2");

        // Set the default player (player 1)
        tvPlayer.setText(p1Name);

        iv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellClicked(v);
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cellClicked(v);
            }
        });
    }

    public void cellClicked(View view) {
        if(gameOver) {
            Toast.makeText(this, "Game is Over", Toast.LENGTH_SHORT).show();
        } else {

            int id = view.getId();
            ImageView ivSelected = null;
            int positionSelected = 0;


            switch (id) {
                case R.id.imageView0:
                    ivSelected = iv0;
                    positionSelected = 0;
                    break;
                case R.id.imageView1:
                    ivSelected = iv1;
                    positionSelected = 1;
                    break;
                case R.id.imageView2:
                    ivSelected = iv2;
                    positionSelected = 2;
                    break;
                case R.id.imageView3:
                    ivSelected = iv3;
                    positionSelected = 3;
                    break;
                case R.id.imageView4:
                    ivSelected = iv4;
                    positionSelected = 4;
                    break;
                case R.id.imageView5:
                    ivSelected = iv5;
                    positionSelected = 5;
                    break;
                case R.id.imageView6:
                    ivSelected = iv6;
                    positionSelected = 6;
                    break;
                case R.id.imageView7:
                    ivSelected = iv7;
                    positionSelected = 7;
                    break;
                case R.id.imageView8:
                    ivSelected = iv8;
                    positionSelected = 8;
                    break;
            } // switch end

            if (selectedCells[positionSelected] == 0) {

                if (isPlayingPlayer1) {
                    ivSelected.setImageResource(R.drawable.ic_player_one);
                    selectedCells[positionSelected] = 1;
                } else {
                    ivSelected.setImageResource(R.drawable.ic_player_two);
                    selectedCells[positionSelected] = 2;
                }

                if (existSolution()) { // Winner!
                    String nameWinner = tvPlayer.getText().toString();
                    Toast.makeText(this, nameWinner + " wins",
                            Toast.LENGTH_SHORT).show();
                    gameOver = true;
                    showGamerOverDialog(false);

                } else if(existDraw()) { // Draw
                    showGamerOverDialog(true);
                } else { // Continue playing...
                    if (isPlayingPlayer1) {
                        tvPlayer.setText(p2Name);
                        tvPlayer.setTextColor(getResources().getColor(R.color.colorPrimary));
                    } else {
                        tvPlayer.setText(p1Name);
                        tvPlayer.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    // Change the player
                    isPlayingPlayer1 = !isPlayingPlayer1;
                }


            } else {
                Toast.makeText(this,
                        "Select another cell",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean existDraw() {
        boolean isDraw = true;

        for(int i=0; i<selectedCells.length; i++) {
            if(selectedCells[i] == 0) isDraw = false;
        }

        return  isDraw;
    }

    private void showGamerOverDialog(boolean isDraw) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        String winner = tvPlayer.getText().toString();
        builder.setCancelable(false);
        if(isDraw) {
            builder.setMessage("Both players are winners!");
        } else {
            builder.setMessage("The winner is " + winner);
        }
        builder.setTitle("Game Over");

        // Add the buttons
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                restartGame();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
                finish();
            }
        });


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    private void restartGame() {
        gameOver = false;
        tvPlayer.setText(p1Name);
        iv0.setImageResource(R.drawable.ic_blank_check_box);
        iv1.setImageResource(R.drawable.ic_blank_check_box);
        iv2.setImageResource(R.drawable.ic_blank_check_box);
        iv3.setImageResource(R.drawable.ic_blank_check_box);
        iv4.setImageResource(R.drawable.ic_blank_check_box);
        iv5.setImageResource(R.drawable.ic_blank_check_box);
        iv6.setImageResource(R.drawable.ic_blank_check_box);
        iv7.setImageResource(R.drawable.ic_blank_check_box);
        iv8.setImageResource(R.drawable.ic_blank_check_box);

        for(int i=0; i<selectedCells.length; i++) {
            selectedCells[i] = 0;
        }
    }

    private boolean existSolution() {
        boolean exist = false;

        //     CELLS positions
    /*
         0 | 1 | 2
         ----------
         3 | 4 | 5
         ----------
         6 | 7 | 8

         Possible solutions
         0,1,2
         3,4,5
         6,7,8
         0,3,6
         1,4,7
         2,5,8
         0,4,8
         2,4,6

     */

        if(selectedCells[0] == selectedCells[1]
                && selectedCells[1] == selectedCells[2]
                && selectedCells[2] != 0) { // 0,1,2
            exist = true;
        } else if(selectedCells[3] == selectedCells[4]
                && selectedCells[4] == selectedCells[5]
                && selectedCells[5] != 0) { // 3,4,5
            exist = true;
        } else if(selectedCells[6] == selectedCells[7]
                && selectedCells[7] == selectedCells[8]
                && selectedCells[8] != 0) { // 6,7,8
            exist = true;
        } else if(selectedCells[0] == selectedCells[3]
                && selectedCells[3] == selectedCells[6]
                && selectedCells[6] != 0) { // 0,3,6
            exist = true;
        } else if(selectedCells[1] == selectedCells[4]
                && selectedCells[4] == selectedCells[7]
                && selectedCells[7] != 0) { // 1,4,7
            exist = true;
        } else if(selectedCells[2] == selectedCells[5]
                && selectedCells[5] == selectedCells[8]
                && selectedCells[8] != 0) { // 2,5,8
            exist = true;
        } else if(selectedCells[0] == selectedCells[4]
                && selectedCells[4] == selectedCells[8]
                && selectedCells[8] != 0) { // 0,4,8
            exist = true;
        } else if(selectedCells[2] == selectedCells[4]
                && selectedCells[4] == selectedCells[6]
                && selectedCells[6] != 0) { // 2,4,6
            exist = true;
        }
        return exist;
    }
}
