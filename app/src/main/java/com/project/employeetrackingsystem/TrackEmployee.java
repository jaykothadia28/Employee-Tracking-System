package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TrackEmployee extends AppCompatActivity implements OnGetList{

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_employee);

        new TrackEmployeeBg(TrackEmployee.this).execute();

    }

    @Override
    public void onTaskCompleted(List<String> lst) {
        listView = (ListView) findViewById(R.id.pendingEmployees);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, lst);
        listView.setAdapter(adapter);
    }
}