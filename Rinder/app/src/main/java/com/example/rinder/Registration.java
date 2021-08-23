package com.example.rinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {





        Button registerRegister;
        EditText emailRegister,passwordRegister,nameRegister;
        FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);
            registerRegister = findViewById(R.id.registerRegister);
            nameRegister = findViewById(R.id.nameRegister);
            emailRegister = findViewById(R.id.emailRegister);
            passwordRegister = findViewById(R.id.passwordRegister);

            auth = FirebaseAuth.getInstance();


            registerRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = nameRegister.getText().toString();
                    String email = emailRegister.getText().toString();
                    String password = passwordRegister.getText().toString();



                    if(name.isEmpty()){
                        nameRegister.setError("Field must required!");
                        return;
                    }
                    if(email.isEmpty()){
                        emailRegister.setError("Field must required!");
                        return;
                    }
                    if(password.isEmpty()){
                        passwordRegister.setError("Field must required!");
                        return;
                    }
                    if(password.length()<6){
                        passwordRegister.setError("6 digits password required!");
                        return;
                    }

                    auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"Registered Successfull !",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Log_in.class));
                            finish();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });







////                startActivity(new Intent(getApplicationContext(),loginActivity.class));
//                startActivity(new Intent(getApplicationContext(),afterRegisterActivity.class));
//                finish();
                }
            });

        }
    }