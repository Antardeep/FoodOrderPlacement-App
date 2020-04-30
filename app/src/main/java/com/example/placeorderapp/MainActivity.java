package com.example.placeorderapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Users u = new Users(this);                                                              //object of Users class
    EditText email;
    EditText password;

    TextInputLayout textInputLayoutEmail;                                                           //Declaration TextInputLayout
    TextInputLayout textInputLayoutPassword;


    /*********************call register activity**************************/
    private View.OnClickListener registerListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent reg = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(reg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));

        /**************Asign IDs**************************/
        email = findViewById(R.id.user);
        password = findViewById(R.id.password);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        final Button login = findViewById(R.id.login);
        final Button register = findViewById(R.id.register);

        /************************login functionality**********************/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /************Check user input is correct or not***************/
                if (validate()) {
                                                                                                    //Get values from EditText fields
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();

                    Customer currentUser = u.Verify(new Customer(null, null, Email, Password));         //Verify user

                    if (currentUser != null) {                                                         //Check Verification is successful or not

                        Cursor data = u.getItemID(Email);                                           //gets the id associated with the name
                        int itemID = -1;
                        while(data.moveToNext()){
                            itemID = data.getInt(0);
                        }

                        //User Logged in Successfully Launch You Start activity
                        Intent intent=new Intent(MainActivity.this,StartActivity.class);
                        intent.putExtra("id",itemID);
                        startActivity(intent);
                    } else {

                        Snackbar.make(login, "User doesn't Exists , please try again", Snackbar.LENGTH_LONG).show();        //User Logged in Failed

                    }
                }

            }
        });

        register.setOnClickListener(registerListerner);

    }

    /*********************This method is used to validate input given by user**************************/
    public boolean validate() {
        boolean valid = false;

        String Email = email.getText().toString();
        String Password = password.getText().toString();

        /************Handling validation for Email field*****************/
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        /**********Handling validation for Password field**************/
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Field is Empty!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is too short!");
            }
        }

        return valid;
    }

//    hide soft keyboard
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
