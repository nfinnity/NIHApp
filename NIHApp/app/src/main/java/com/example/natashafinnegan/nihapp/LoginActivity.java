package com.example.natashafinnegan.nihapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private ImageView background;
    private TextView centerBox;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        background = (ImageView) findViewById(R.id.background);
        centerBox = (TextView) findViewById(R.id.centerBox);
        appName = (TextView) findViewById(R.id.appName);

        centerBox.getBackground().setAlpha(87);

        Typeface customTypeface = Typeface.createFromAsset(getAssets(), "fonts/Confetti Stream.ttf");
        appName.setTypeface(customTypeface);
    }
}
