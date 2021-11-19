package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends AppCompatActivity implements OnGetList {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        new GetEmployeeList(EmployeeList.this).execute();
    }

    @Override
    public void onTaskCompleted(List<String> lst) {

        listView = (ListView) findViewById(R.id.EmployeeList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, lst);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = String.valueOf(parent.getItemAtPosition(position));
                Log.d("emp", val);
                //send the selected Item to the next activity where we will need to select a client
                Intent i = new Intent(getApplicationContext(), ClientList.class);
                i.putExtra("employee", val);
                startActivity(i);
            }
        });
    }
}