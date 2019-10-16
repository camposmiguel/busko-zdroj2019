package com.miguelcr.a01_firebaseconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewStudentActivity extends AppCompatActivity {
    EditText editTextName, editTextAge;
    Button buttonSave;
    
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        
        editTextName = findViewById(R.id.edit_text_name);
        editTextAge = findViewById(R.id.edit_text_age);
        buttonSave = findViewById(R.id.button_save);
        
        // Firebase Database connection
        db = FirebaseFirestore.getInstance();
        
        // Click event
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String ageText = editTextAge.getText().toString();
                int age = Integer.parseInt(ageText);
                
                if(!name.isEmpty() && !ageText.isEmpty()) {
                    // We can save
                    saveStudent(name, age);
                } else {
                    Toast.makeText(NewStudentActivity.this, "Complete the form!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveStudent(String name, int age) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("age", age);

        // Add a new document with a generated ID
        db.collection("students")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());

                        // Close the Activity to return to the student list
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }
}
