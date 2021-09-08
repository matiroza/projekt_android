package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity{

    DBHelper db;

    //hooks
    EditText username, password, repassword;
    Button signin, signup;
    AutoCompleteTextView autoCompleteTextView;
    Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);

        signin = findViewById(R.id.btnSignIn);
        signup = findViewById(R.id.btnSignUp);
        autoCompleteTextView = findViewById(R.id.autoCompleteText);

        //spiner
        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        db = new DBHelper(this);

        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, countries);
        autoCompleteTextView.setAdapter(arrayAdapter);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String country = autoCompleteTextView.getText().toString();
                String age = spinner1.getSelectedItem().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(SignupActivity.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = db.checkUsername(user);
                        if (checkuser == false) {
                            Boolean insert = db.insertData(user, pass, country, age);
                            if (insert == true) {
                                Toast.makeText(SignupActivity.this, "Registered successfully, now you can Sign in", Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(getApplicationContext(), HomeLogged.class);
                                startActivity(intent);*/
                            } else {
                                Toast.makeText(SignupActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}