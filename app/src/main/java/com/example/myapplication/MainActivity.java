package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegisteration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvinfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegisteration = (TextView)findViewById(R.id.tvRegister);
        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

    }
    private void validate(String userName, String userPassword){

        if((userName.equals("Admin")) && (userPassword.equals( "1234"))){

        startActivity(new Intent(MainActivity.this , SecondActivity.class));
    }
        else{
            counter--;

            Info.setText("No of attempts remaining: "+ String.valueOf(counter));
            if(counter == 0){
                Login.setEnabled(false);
            }

    }
    userRegisteration.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, RegisterationActivity.class));
        }
    });
}
}
