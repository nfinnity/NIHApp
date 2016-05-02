package com.example.natashafinnegan.nihapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class emailActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText recipient;
    private EditText subject;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sendButton = (Button) findViewById(R.id.sendButton);
        recipient = (EditText) findViewById(R.id.recipient);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
    }

    public void onClick(View view)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(getResources().getString(R.string.emailCode));
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{recipient.getText().toString()});
        i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
        try {
            startActivity(Intent.createChooser(i, getResources().getString(R.string.sendTitle)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(emailActivity.this, getResources().getString(R.string.noClients), Toast.LENGTH_SHORT).show();
        }
    }
}