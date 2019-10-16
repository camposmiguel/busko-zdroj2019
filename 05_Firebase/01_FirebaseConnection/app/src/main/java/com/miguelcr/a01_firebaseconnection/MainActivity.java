package com.miguelcr.a01_firebaseconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    ListView listView;
    List<String> students;
    ArrayAdapter adapter;
    FloatingActionButton fabAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        fabAddStudent = findViewById(R.id.floatingActionButton);

        // Click
        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewStudentActivity.class);
                startActivity(i);
            }
        });

        db = FirebaseFirestore.getInstance();

        students = new ArrayList<>();
        adapter = new ArrayAdapter(
          this,
          android.R.layout.simple_list_item_1,
          students
        );

        listView.setAdapter(adapter);

        loadStudents();

    }

    private void loadStudents() {

        db.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                students.add(document.getString("name"));
                            }

                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadStudents();
    }
}
