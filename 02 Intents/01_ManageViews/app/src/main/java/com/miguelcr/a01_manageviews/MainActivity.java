package com.miguelcr.a01_manageviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Variable definitions
    EditText editTextOp1, editTextOp2;
    ImageButton imageButtonAdd;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the reference for the differents view components
        editTextOp1 = findViewById(R.id.edit_text_op1);
        editTextOp2 = findViewById(R.id.edit_text_op2);
        imageButtonAdd = findViewById(R.id.image_button_add);
        textViewResult = findViewById(R.id.text_view_result);

        // Detect the click event for the add button
        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. We get the 2 numbers to make the operation
                String op1Text = editTextOp1.getText().toString();
                String op2Text = editTextOp2.getText().toString();

                // 2. We calculate the result
                int op1 = Integer.parseInt(op1Text);
                int op2 = Integer.parseInt(op2Text);

                int result = op1 + op2;

                // 3. Print the result in the TextView
                textViewResult.setText(op1Text + " + " + op2Text + " = " + result);


            }
        });

    }
}
