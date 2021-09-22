package com.example.randomstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class ListOfStudents extends AppCompatActivity {

    RecyclerView studentView;
    StudentsAdapter studentsAdapter;
    String [] arrayOfStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_students);
        Intent intent = getIntent();
        arrayOfStudents = intent.getStringArrayExtra("studentsArray");
        for (String student: arrayOfStudents
             ) {
            Log.d("testtttt", student);
        }
        studentView = findViewById(R.id.rvStudents);

        studentsAdapter = new StudentsAdapter(Arrays.asList(arrayOfStudents.clone()));
        studentView.setAdapter(studentsAdapter);
        studentView.setLayoutManager(new LinearLayoutManager(this));
    }
}