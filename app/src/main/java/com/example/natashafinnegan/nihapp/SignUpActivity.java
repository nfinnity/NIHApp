package com.example.natashafinnegan.nihapp;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button signUp;
    private TextView appName;
    private EditText name;
    private EditText email;
    private EditText password;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = (Button) findViewById(R.id.btn_login);
        appName = (TextView) findViewById(R.id.appName);
        name = (EditText) findViewById(R.id.usernameBox);
        email = (EditText) findViewById(R.id.emailBox);
        password = (EditText) findViewById(R.id.passwordBox);
        loginLink = (TextView) findViewById(R.id.loginLink);

        Typeface customTypeface = Typeface.createFromAsset(getAssets(), "fonts/Confetti Stream.ttf");
        appName.setTypeface(customTypeface);

        signUp.getBackground().setColorFilter(0xFF098083, PorterDuff.Mode.MULTIPLY);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.creatingAccount));
        progressDialog.show();

        String nameStr = name.getText().toString();
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signUp.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.signUpFailed), Toast.LENGTH_LONG).show();

        signUp.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String nameStr = name.getText().toString();
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if (nameStr.isEmpty() || name.length() < 3) {
            Drawable dr = getResources().getDrawable(android.R.drawable.stat_notify_error);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            name.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        if (emailStr.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            Drawable dr = getResources().getDrawable(android.R.drawable.stat_notify_error);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            email.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        if (passwordStr.isEmpty() || password.length() < 4 || password.length() > 10) {
            Drawable dr = getResources().getDrawable(android.R.drawable.stat_notify_error);
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            password.setCompoundDrawables(null, null, dr, null);
            valid = false;
        } else {
            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        return valid;
    }
}