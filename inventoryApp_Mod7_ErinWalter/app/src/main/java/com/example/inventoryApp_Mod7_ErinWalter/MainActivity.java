package com.example.inventoryApp_Mod7_ErinWalter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_screen.R;


public class MainActivity extends AppCompatActivity {
    private TextView userNameText;
    private TextView passwordText;
    private Button loginButton;
    private Button createAccountButton;
    private InventoryAppDatabase database;

    private TextWatcher enableButtonsTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String userNameText_text = userNameText.getText().toString();
            String passwordText_text = passwordText.getText().toString();

            //both buttons are set to only be clickable if both password and username fields
            //are not empty
            createAccountButton.setEnabled(!userNameText_text.isEmpty() && !passwordText_text.isEmpty());
            loginButton.setEnabled(!userNameText_text.isEmpty() && !passwordText_text.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        //associate all variables above with their associated UI elements

        userNameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);
        database = new InventoryAppDatabase(getApplicationContext());

        //add listener to enable/disable login and create account buttons
        userNameText.addTextChangedListener(enableButtonsTextWatcher);
        passwordText.addTextChangedListener(enableButtonsTextWatcher);
    }

    public void OnLoginButtonClick(View view){
        //get value of username and password so logic can be
        //applied to check vs database
        String userNameText_text = userNameText.getText().toString();
        String passwordText_text = passwordText.getText().toString();

        //send to database method to validate vs db for correctness
        long userId = database.loginUser(userNameText_text, passwordText_text);
        if(userId != -1)
        {
            //if exists and correctly matches password, take to home page
            Intent dashboard = new Intent(this, MainDashboard.class);
            dashboard.putExtra("userId", userId);
            startActivity(dashboard);
        }
        else {
            //otherwise, display toast that username was invalid
            Toast.makeText(getApplicationContext(), "Error: username or password incorrect. Try Again.",Toast.LENGTH_SHORT).show();
        }
    }

    public void OnCreateNewUserButtonClick(View view){
        //get values of username and password so can be used to check
        //vs db for any existing users with same username/pw
        String userNameText_text = userNameText.getText().toString();
        String passwordText_text = passwordText.getText().toString();

        //send to db to add user method
        long userId = database.addUser(userNameText_text, passwordText_text);
        //successfully created new username and log user in
        if(userId != -1)
        {
            Toast.makeText(getApplicationContext(), "Successfully created new username",Toast.LENGTH_SHORT).show();
        }
        else {
            //if exists, toast to screen that error, username already exists
            Toast.makeText(getApplicationContext(), "Error: username already exists",Toast.LENGTH_SHORT).show();

        }

    }

}
