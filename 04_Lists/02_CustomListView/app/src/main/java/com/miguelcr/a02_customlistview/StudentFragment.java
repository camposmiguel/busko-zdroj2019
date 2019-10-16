package com.miguelcr.a02_customlistview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment {

    private int mColumnCount = 2;
    MyStudentRecyclerViewAdapter adapter;
    List<Student> students;

    public StudentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // List of students
            students = new ArrayList<>();
            students.add(new Student("Konrad",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Filip",19, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Michal",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Piotr",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Grzegorz",19, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Sebastian",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Mikolaj",20, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Adrian",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Mateusz",30, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Jakub I",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Alan",19, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Bartosz I",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Gabriel",19, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Bartosz II",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Jakub II",18, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));
            students.add(new Student("Damian",19, "https://s3.amazonaws.com/uifaces/faces/twitter/sauro/128.jpg"));

            adapter = new MyStudentRecyclerViewAdapter(
                    getActivity(),
                    R.layout.fragment_student,
                    students
            );

            recyclerView.setAdapter(adapter);
        }
        return view;
    }
}
