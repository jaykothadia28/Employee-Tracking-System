package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddClient extends AppCompatActivity {

    TextView textView;
    EditText editText1, editText2, editText3, editText4, editText5;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        textView = (TextView) findViewById(R.id.add_client);
        editText1 = (EditText) findViewById(R.id.client_name);
        editText2 = (EditText) findViewById(R.id.client_username);
        editText3 = (EditText) findViewById(R.id.client_password);
        editText4 = (EditText) findViewById(R.id.client_contact);
        editText5 = (EditText) findViewById(R.id.client_address);
        addBtn = (Button) findViewById(R.id.addClientBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClient();
            }
        });
    }

    public void addClient() {
        BackgroundAddClient bgClient = new BackgroundAddClient(this);
        bgClient.execute(editText1.getText().toString(), editText2.getText().toString(),
                editText3.getText().toString(), editText4.getText().toString(),editText5.getText().toString());
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        editText4.getText().clear();
        editText5.getText().clear();
    }
}