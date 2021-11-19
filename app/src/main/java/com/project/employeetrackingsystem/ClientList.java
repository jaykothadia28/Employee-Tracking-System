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

public class ClientList extends AppCompatActivity implements OnGetList{

    ListView listView;
    String empName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        Bundle b = getIntent().getExtras();
        empName = b.getString("employee");
        new GetClientList(ClientList.this).execute();
    }

    @Override
    public void onTaskCompleted(List<String> lst) {

        listView = (ListView) findViewById(R.id.ClientList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, lst);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = String.valueOf(parent.getItemAtPosition(position));

                //send the selected Item to the next activity where we will need to select a client
                Intent i = new Intent(ClientList.this, AssignTask.class);
                i.putExtra("client", val);
                i.putExtra("emp", empName);
                startActivity(i);
            }
        });
    }
}