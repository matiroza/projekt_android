package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //db
    DBHelper db;

    //hooks
    private EditText username, password;
    private Button btnLogin, btnSignup;
    private CheckBox checkBoxLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignUp1);
        checkBoxLogin = findViewById(R.id.checkboxLogin);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        db = new DBHelper(this);

        checkSharedPreferences();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(checkBoxLogin.isChecked()){
                    editor.putString(getString(R.string.checkbox),"True");
                    editor.commit();

                    editor.putString(getString(R.string.username),user);
                    editor.commit();

                    editor.putString(getString(R.string.password),pass);
                    editor.commit();
                }else{
                    editor.putString(getString(R.string.checkbox),"False");
                    editor.commit();

                    editor.putString(getString(R.string.username),"");
                    editor.commit();

                    editor.putString(getString(R.string.password),"");
                    editor.commit();
                }

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = db.checkUsernamePassword(user, pass);

                    if(checkUserPass){
                        Toast.makeText(LoginActivity.this, "Sign in successfull!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeLogged.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid password or login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void checkSharedPreferences(){
        String checkbox = sharedPreferences.getString(getString(R.string.checkbox),"False");
        String username1 = sharedPreferences.getString(getString(R.string.username),"");
        String password1 = sharedPreferences.getString(getString(R.string.password),"");

        username.setText(username1);
        password.setText(password1);

        if(checkbox.equals("True")){
            checkBoxLogin.setChecked(true);
        }else {
            checkBoxLogin.setChecked(false);
        }
    }
}