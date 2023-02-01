package com.example.weightliftingtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button openNewWOButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openNewWOButton = (Button) findViewById(R.id.newWrkoutButton);
        openNewWOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWorkOutAct();
            }
        });
    }

    public void openNewWorkOutAct() {
        Intent intent = new Intent(this, newWorkoutAct.class);
        startActivity(intent);
    }
}