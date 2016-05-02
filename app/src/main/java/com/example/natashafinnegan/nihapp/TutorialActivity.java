package com.example.natashafinnegan.nihapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialActivity extends AppCompatActivity {

    Button videoTutorial, exitTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        videoTutorial = (Button) findViewById(R.id.viewVideoTutorial);
        exitTutorial = (Button) findViewById(R.id.exitTutorial);
    }

    public void tutorialOnClick(View view)
    {
        if (view == videoTutorial)
        {
            Uri uri = Uri.parse("https://www.youtube.com/watch?v=cfd9ViHBlR4");
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(i);
        }
        else if (view == exitTutorial)
        {
            finish();
        }
    }
}
