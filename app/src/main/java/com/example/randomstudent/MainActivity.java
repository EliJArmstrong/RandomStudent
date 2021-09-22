package com.example.randomstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] studentsStringArray = new String[]{""};

    List<String> students = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();

    Intent intent;

    Button btn;
    Button resetBtn;
    TextView student1;
    TextView student2;
    TextView studentCountLbl;

    Random random = new Random();

    int count = 0;


    String[] studentsPairs = new String[studentsStringArray.length/2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, ListOfStudents.class);

        for (int i = 0; i < studentsStringArray.length; i++) {
            int number = random.nextInt(studentsStringArray.length);
            while(numbers.contains(number)){
                number = random.nextInt(studentsStringArray.length);
            }
            students.add(studentsStringArray[number]);
            numbers.add(number);
        }

        Log.d("listOfStudents", students.toString());

        btn = findViewById(R.id.button);
        resetBtn = findViewById(R.id.resetBtn);
        student1 = findViewById(R.id.student1);
        student2 = findViewById(R.id.student2);
        studentCountLbl = findViewById(R.id.studentCountLbl);
        studentCountLbl.setText("Number of Students Left: " + students.size());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Display a message when there are no more students.
                if(students.size() < 2){
                    intent.putExtra("studentsArray", studentsPairs);
                    startActivity(intent);
                } else {

                    // Holds the random indexes
                    int index1;
                    int index2;

                    // Get to random indexes
                    index1 = random.nextInt(students.size());
                    index2 = random.nextInt(students.size());

                    // If the indexes equal pick a new number until they no longer equal
                    while(index2 == index1){
                        index2 = random.nextInt(students.size());
                    }

                    // Display the student on the phone
                    student1.setText(students.get(index1));
                    student2.setText(students.get(index2));

                    // log the student for the record
                    Log.d("listOfStudents", students.get(index1) + " and " + students.get(index2));

                    studentsPairs[count++] = students.get(index1) + " and " + students.get(index2);

                    // Remove the student
                    students.remove((index1 > index2 ? index1 : index2));
                    students.remove((index1 < index2 ? index1 : index2));
                    studentCountLbl.setText("Number of Students Left: " + students.size());
                    if(students.size() == 0){
                        btn.setText("List Results");
                    }

                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                students.clear();
                numbers.clear();
                count = 0;
                student1.setText("Student 1");
                student2.setText("Student 2");
                btn.setText("CHOOSE PAIRS");
                for (int i = 0; i < studentsStringArray.length; i++) {
                    int number = random.nextInt(studentsStringArray.length);
                    while(numbers.contains(number)){
                        number = random.nextInt(studentsStringArray.length);
                    }
                    students.add(studentsStringArray[number]);
                    numbers.add(number);
                }
                studentCountLbl.setText("Number of Students Left: " + students.size());
            }
        });
    }
}