package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {
    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(validate()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(RegisterationActivity.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterationActivity.this,MainActivity.class));
                           }
                           else{

                               Toast.makeText(RegisterationActivity.this, "Registeration Failed", Toast.LENGTH_SHORT).show();

                           }
                        }
                    });
                }

            }

        });
        userLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(RegisterationActivity.this,MainActivity.class));

            }

        });
    }
    private void setupUIViews(){
            userName= (EditText)findViewById(R.id.etUserName);
            userPassword = (EditText)findViewById(R.id.etUserPassword);
            userEmail = (EditText)findViewById(R.id.etUserEmail);
            regButton = (Button)findViewById(R.id.btnRegister);
            userLogin = (TextView)findViewById(R.id.tvUserLogin);

    }
    private Boolean validate(){
        Boolean result = false;

        String name = userName.getText().toString();
        String password  = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }
}
