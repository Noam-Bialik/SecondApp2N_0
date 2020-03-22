package com.example.secondapp2n_0.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secondapp2n_0.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button registerBtn,loginBtn;
    FirebaseAuth firebaseAuth;
    public static final String mypreference = "";
    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedpreferences.getString("UserName", "")!=""&&sharedpreferences.getString("UserPassword", "")!="") {
            switchToMain();
        }

        emailEditText = (EditText)findViewById(R.id.edit_text_email);
        passwordEditText = (EditText)findViewById(R.id.edit_text_password);
        registerBtn = (Button)findViewById(R.id.btn_new_user);
        loginBtn = (Button)findViewById(R.id.btn_log_in);

        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
                }

                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    saveUser(email,password);
                                    switchToMain();
                                }
                                else{

                                    Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });




    }
    void logIn(){
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //Toast.makeText(getApplicationContext(),"good",Toast.LENGTH_SHORT).show();
                    saveUser(email,password);
                    switchToMain();

                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("UserName","");
                    editor.putString("UserPassword", "");
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();

                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"password or email is empty", Toast.LENGTH_SHORT).show();
        }

    }


    //not outh:
    public void clickOnSwitchToMain(View view){
        switchToMain();
    }

    private void saveUser1(String userEmail){
        SharedPreferences.Editor editor = getSharedPreferences("my_pref", MODE_PRIVATE).edit();
        editor.putString("email", userEmail);
        editor.apply();
    }

    private void switchToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //@Override
    protected void onStart()
    {
        super.onStart();
        if (sharedpreferences.getString("UserName", "")!=""&&sharedpreferences.getString("UserPassword", "")!="") {
            emailEditText.setText(sharedpreferences.getString("UserName", ""));
            passwordEditText.setText(sharedpreferences.getString("UserPassword", ""));
        }
    }

    public void saveUser(String userName, String userPassword) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("UserName",userName);
        editor.putString("UserPassword", userPassword);
        editor.commit();
        switchToMain();


    }
}


