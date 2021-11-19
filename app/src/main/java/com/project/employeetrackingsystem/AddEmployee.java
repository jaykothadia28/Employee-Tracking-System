package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEmployee extends AppCompatActivity {

    TextView textView;
    EditText editText1, editText2, editText3, editText4;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        textView = (TextView) findViewById(R.id.add_employee);
        editText1 = (EditText) findViewById(R.id.employee_name);
        editText2 = (EditText) findViewById(R.id.employee_username);
        editText3 = (EditText) findViewById(R.id.employee_pass);
        editText4 = (EditText) findViewById(R.id.employee_contact);
        addBtn = (Button) findViewById(R.id.addEmpBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });

    }

    public void addEmployee(){
        BackgroundAddEmployee bgAddEmp = new BackgroundAddEmployee(this);
        bgAddEmp.execute(editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString());
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        editText4.getText().clear();
    }
}