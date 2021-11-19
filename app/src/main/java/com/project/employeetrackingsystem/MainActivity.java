package com.project.employeetrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView whoAreYouTxt;
    private TextView orTxt1;
    private TextView orTxt2;
    private Button adminBtn;
    private Button employeeBtn;
    private Button clientBtn;

    @Override
    protected void onStart() {
        super.onStart();
        String username, userType;
        Shared shared = new Shared(getApplicationContext());

        if(shared.isLoggedIn()){
            username = shared.sharedPreferences.getString("userName", null);
            userType = shared.sharedPreferences.getString("userType", null);

            assert userType != null;
            switch (userType){
                case "Admin":
                    Intent iAdmin = new Intent(this, AdminActivity.class);
                    iAdmin.putExtra("userName", username);
                    iAdmin.putExtra("userType", userType);
                    startActivity(iAdmin);
                    finish();
                    break;
                case "Employee":
                    Intent iEmp = new Intent(this, EmployeeActivity.class);
                    iEmp.putExtra("userName", username);
                    iEmp.putExtra("userType", userType);
                    startActivity(iEmp);
                    finish();
                    break;
                case "Client":
                    Intent iClient = new Intent(this, ClientActivity.class);
                    iClient.putExtra("userName", username);
                    iClient.putExtra("userType", userType);
                    startActivity(iClient);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whoAreYouTxt = findViewById(R.id.who_are_you);
        orTxt1 = findViewById(R.id.or1);
        orTxt2 = findViewById(R.id.or2);
        adminBtn = findViewById(R.id.admin_button);
        employeeBtn = findViewById(R.id.employee_button);
        clientBtn = findViewById(R.id.client_button);

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserLogin.class);
               // i.putExtra("userType", "Admin");
                startActivity(i);
            }
        });

        employeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserLogin.class);
                //i.putExtra("userType", "Employee");
                startActivity(i);
            }
        });

        clientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UserLogin.class);
                //i.putExtra("userType", "Client");
                startActivity(i);
            }
        });

    }
}