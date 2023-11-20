package com.example.inmobiliariacesde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity {

    EditText singupName, singupUsername, singupEmail, singupPassword;
    TextView loginRedirect;
    Button singupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        singupName = findViewById(R.id.signup_name);
        singupUsername = findViewById(R.id.signup_username);
        singupEmail = findViewById(R.id.signup_email);
        singupPassword = findViewById(R.id.signup_password);
        singupButton = findViewById(R.id.signup_btn);
        loginRedirect= findViewById(R.id.login_redirect);

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String name = singupName.getText().toString();
                String username = singupUsername.getText().toString();
                String email = singupEmail.getText().toString();
                String password = singupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, username, email, password);
                reference.child(name).setValue(helperClass);

                Toast.makeText(SingupActivity.this, "Registrado con Exito!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SingupActivity.this, Log.class);
                startActivity(intent);
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}