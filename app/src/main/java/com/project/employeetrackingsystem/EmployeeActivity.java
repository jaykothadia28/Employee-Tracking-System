package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements OnGetList{

    TextView client_op, toDo, clientAddressOp, clientContactOp, performDateOp;
    String empName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Bundle b = getIntent().getExtras();
        empName = b.getString("userName");

        client_op = (TextView) findViewById(R.id.clientNameEmp);
        toDo = (TextView) findViewById(R.id.toDoOp);
        clientAddressOp = (TextView) findViewById(R.id.clientAddressOp);
        clientContactOp = (TextView) findViewById(R.id.clientContactOp);
        performDateOp = (TextView) findViewById(R.id.performDateOp);

        new EmployeeActivityBg(this).execute(empName);

    }

    @Override
    public void onTaskCompleted(List<String> lst) {

        client_op.setText(lst.get(0));
        clientContactOp.setText(lst.get(1));
        clientAddressOp.setText(lst.get(2));
        toDo.setText(lst.get(3));
        performDateOp.setText(lst.get(4));

    }
}
