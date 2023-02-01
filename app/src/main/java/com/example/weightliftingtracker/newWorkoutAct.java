package com.example.weightliftingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class newWorkoutAct extends AppCompatActivity {

    private TextView date;
    private HashMap<String, ArrayList<String>> workOuts = new HashMap<>();
    private ArrayList<String> exerciseList = new ArrayList<>();
    private Button addToList;
    private TextView exerciseName;
    private TextView weightUsed;
    private TextView reps;
    private TextView sets;
    private Button finishWorkout;
    private Button showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);
        if (exerciseList.size() != 0) {
            exerciseList = (ArrayList<String>) getIntent().getSerializableExtra("updatedList");
        }

        date = (TextView) findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = sdf.format(new Date());
        date.setText(currentDate);

        //Date of the workout is the key of the values in hashmap.
        workOuts.put(currentDate, exerciseList);

        exerciseName = findViewById(R.id.exerciseName);
        weightUsed = findViewById(R.id.weightUsed);
        reps = findViewById(R.id.repetitionsDone);
        sets = findViewById(R.id.setsDone);

        addToList = (Button) findViewById(R.id.addToList);
        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Adds the given strings to the list.
                String execName = exerciseName.getText().toString();
                String weight = weightUsed.getText().toString();
                String rep = reps.getText().toString();
                String set = sets.getText().toString();
                String listEntry = execName + " " + weight + " kg " + set + "x" + rep;

                exerciseList.add(listEntry);
                //Clears the text fields.
                clearForm();
            }

        });

        finishWorkout = (Button) findViewById(R.id.finishWorkout);
        finishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToMap(currentDate, exerciseList);
                //Save it to DB in here. Not before.
            }
        });

        showList = (Button) findViewById(R.id.showList);
        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowListAct();
            }
        });
    }

    public void addToMap(String key, ArrayList<String> list) {
        workOuts.put(key, list);
    }

    public void openShowListAct() {
        String passToAnotherAct = "passingList";
        Intent intent = new Intent(this, showListActivity.class);
        intent.putExtra(passToAnotherAct, exerciseList);
        startActivity(intent);
    }

    private void clearForm() {
        exerciseName.setText("");
        weightUsed.setText("");;
        reps.setText("");
        sets.setText("");
    }
}