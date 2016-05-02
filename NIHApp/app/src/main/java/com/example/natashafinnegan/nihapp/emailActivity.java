package com.example.natashafinnegan.nihapp;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.net.URI;

public class emailActivity extends AppCompatActivity {

    String attachmentFile;
    private static final int TAKE_PICTURE=1;
    private static final int PICK_FROM_GALLERY = 2;
    int columnIndex;
    private Button sendButton;
    private Button photoButton;
    private EditText recipient;
    private EditText subject;
    private EditText body;
    private ImageView viewImage;
    private Uri URI = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sendButton = (Button) findViewById(R.id.sendButton);
        photoButton = (Button) findViewById(R.id.photoButton);
        recipient = (EditText) findViewById(R.id.recipient);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        viewImage=(ImageView)findViewById(R.id.viewImage);
    }

    public void onClick(View view)
    {
        if (view == sendButton) {
            /*
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType(getResources().getString(R.string.emailCode));
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient.getText().toString()});
            i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
            try {
                startActivity(Intent.createChooser(i, getResources().getString(R.string.sendTitle)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(emailActivity.this, getResources().getString(R.string.noClients), Toast.LENGTH_SHORT).show();
            }
            */
            try {
                final Intent emailIntent = new Intent(
                        android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] {recipient.getText().toString()});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText().toString());
                if (URI != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                }
                emailIntent
                        .putExtra(android.content.Intent.EXTRA_TEXT, body.getText().toString());
                this.startActivity(Intent.createChooser(emailIntent,
                        "Sending email..."));

            } catch (Throwable t) {
                Toast.makeText(this,
                        "Request failed try again: " + t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
        else if (view == photoButton)
        {
            selectImage(view);
        }
    }

    public void selectImage(View v) {

        final CharSequence[] options = { getResources().getString(R.string.takePhoto), getResources().getString(R.string.chooseGallery),getResources().getString(R.string.cancel) };

        AlertDialog.Builder builder = new AlertDialog.Builder(emailActivity.this);
        builder.setTitle(getResources().getString(R.string.addPhoto));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(getResources().getString(R.string.takePhoto))) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, TAKE_PICTURE);
                } else if (options[item].equals(getResources().getString(R.string.chooseGallery))) {
                    openGallery();
                } else if (options[item].equals(getResources().getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {

        }
         else if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            /**
             * Get Path
             */
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
            Log.e("", attachmentFile);
            URI = Uri.parse(getResources().getString(R.string.file) + attachmentFile);
            cursor.close();
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType(getResources().getString(R.string.imageReturn));
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(getResources().getString(R.string.dataReturn), true);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.completeAction)),
                PICK_FROM_GALLERY);
    }
}
