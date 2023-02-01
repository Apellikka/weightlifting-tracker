package com.example.weightliftingtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;

public class showListActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<String> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        listView = (ListView) findViewById(R.id.listView);

        exercises = (ArrayList<String>) getIntent().getSerializableExtra("passingList");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                exercises);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(showListActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete exercise?");
                builder.setMessage("Sure you want to delete?");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Updates the list and opens the newWorkoutActivity again.
                        exercises.remove(j);
                        updateList(exercises);
                        arrayAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),
                                "Item deleted!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
    }

    public void updateList(ArrayList<String> updList) {
        String updatedList = "updatedList";
        Intent intent = new Intent(this, newWorkoutAct.class);
        intent.putExtra(updatedList, exercises);
        startActivity(intent);
    }
}