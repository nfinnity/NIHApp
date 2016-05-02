package com.example.natashafinnegan.nihapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class mainActivity extends AppCompatActivity {

    private TextView appName;
    private Button tutorialButton;
    private Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = (TextView) findViewById(R.id.appName);
        tutorialButton = (Button) findViewById(R.id.tutorialButton);
        emailButton = (Button) findViewById(R.id.emailButton);

        Typeface customTypeface = Typeface.createFromAsset(getAssets(), "fonts/Confetti Stream.ttf");
        appName.setTypeface(customTypeface);
    }

    public void onClick(View view)
    {
        if (view == tutorialButton)
        {

        }
        else if (view == emailButton)
        {
            Intent intent = new Intent(this, emailActivity.class);
            startActivity(intent);
        }

    }
}
