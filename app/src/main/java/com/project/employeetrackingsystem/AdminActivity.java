package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AdminActivity extends AppCompatActivity {

    ListView listView;
    String []options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        options = getResources().getStringArray(R.array.options_list);
        listView = (ListView) findViewById(R.id.listView_1);
        final ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, options);
        listView.setAdapter(adapter);

        onItemSelected();
    }

    private void onItemSelected() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = String.valueOf(parent.getItemAtPosition(position));

                switch (val){
                    case "(1) Add Employee":
                        startActivity(new Intent(AdminActivity.this, AddEmployee.class));
                        break;
                    case "(2) Add Client":
                        startActivity(new Intent(AdminActivity.this, AddClient.class));
                        break;
                    case "(3) Assign task to employee":
                        startActivity(new Intent(AdminActivity.this, EmployeeList.class));
                        break;
                    case "(4) Track Employee":
                        startActivity(new Intent(AdminActivity.this, TrackEmployee.class));
                }
            }
        });
    }
}
