package com.example.natashafinnegan.nihapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private Button login;
    private TextView appName;
    private EditText username;
    private EditText password;
    private TextView signUpLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        appName = (TextView) findViewById(R.id.appName);
        username = (EditText) findViewById(R.id.usernameBox);
        password = (EditText) findViewById(R.id.passwordBox);
        signUpLink = (TextView) findViewById(R.id.linkSignUp);


        Typeface customTypeface = Typeface.createFromAsset(getAssets(), "fonts/Confetti Stream.ttf");
        appName.setTypeface(customTypeface);

        login.getBackground().setColorFilter(0xFF098083, PorterDuff.Mode.MULTIPLY);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.authenticating));
        progressDialog.show();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        login.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.loginFailed), Toast.LENGTH_LONG).show();
        login.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String passwordStr = password.getText().toString();
        String usernameStr = username.getText().toString();

        if (usernameStr.isEmpty()) {
            Drawable dr = getResources().getDrawable(android.R.drawable.stat_notify_error);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            username.setCompoundDrawables(null, null, dr, null);
            valid = false;
        }
        else
        {
            username.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        if (passwordStr.isEmpty())
        {
            Drawable dr = getResources().getDrawable(android.R.drawable.stat_notify_error);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            password.setCompoundDrawables(null, null, dr, null);
            valid = false;
        }
        else
        {
            password.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
        return valid;
    }
}