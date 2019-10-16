package com.miguelcr.a01_simplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<String> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView UI component
        listView = findViewById(R.id.list_view);

        // Init the student list
        students = new ArrayList<>();
        students.add("Konrad");
        students.add("Filip");
        students.add("Michal");
        students.add("Piotr");
        students.add("Grzegorz");
        students.add("Sebastian");
        students.add("Mikolaj");
        students.add("Adrian");
        students.add("Mateusz");
        students.add("Jakub I");
        students.add("Alan");
        students.add("Bartosz I");
        students.add("Gabriel");
        students.add("Bartosz II");
        students.add("Jakub II");
        students.add("Damian");

        // Adapter
        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                students
        );

        // Connect the adapter with the ListView
        listView.setAdapter(adapter);

        // Click event
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String studentSelected = students.get(position);
                Toast.makeText(MainActivity.this, studentSelected, Toast.LENGTH_SHORT).show();

                // Animation
                if(view.getAlpha() == 0) {
                    view.animate().alpha(100).setDuration(2000);
                } else {
                    view.animate().alpha(0).setDuration(2000);
                }
            }
        });

    }
}
