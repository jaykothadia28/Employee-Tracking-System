package com.project.employeetrackingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AssignTask extends AppCompatActivity {

    String sentence, toDo, finalDate, emp, client;
    Button btn;
    TextView tx1, tx2;
    EditText et1, et2;
    int mday, mmonth, myear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_task);

        et1 = (EditText) findViewById(R.id.toDo);
        et2 = (EditText) findViewById(R.id.selectDate);
        tx2 = (TextView) findViewById(R.id.assign_task);
        tx1 = (TextView) findViewById(R.id.title);
        btn = (Button) findViewById(R.id.assignBtn);
        Bundle b = getIntent().getExtras();
        sentence = "Client: " + b.getString("client") + "\nEmployee: " + b.getString("emp");
        emp = b.getString("emp");
        client = b.getString("client");
        tx1.setText(sentence.toString());
        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AssignTaskBg(AssignTask.this).execute(et1.getText().toString(), finalDate, emp, client);
            }
        });

    }

    private void selectDate() {
        final Calendar mCalender = Calendar.getInstance();
        myear = mCalender.get(Calendar.YEAR);
        mmonth = mCalender.get(Calendar.MONTH);
        mday = mCalender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AssignTask.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mday = dayOfMonth;
                mmonth = month+1;
                myear = year;
                String date = String.valueOf(year) + "-" + String.valueOf(month+1) + "-" + String.valueOf(dayOfMonth);
                finalDate = date;
                et2.setText(date);
            }
        }, myear, mmonth, mday);
        datePickerDialog.show();
    }
}