package com.example.fariha.editprofile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Calendar;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    //private static int RESULT_LOAD_IMAGE = 1;
    ImageView myImage,thumb1, thumb2, thumb3, thumb4, thumb5;
    Button addPicture, dateChangeButton;
    EditText name, profile, about;
    RadioButton male, female, none;
    LoginButton fbButton;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private static final int fb_id=64206;
    private static final int upload_id=0;
    Switch switch1, switch2, switch3, switch4, switch5;
    UploadPicture uploadPicture;
    private CallbackManager callbackManager;

    //dateChanger datePick;
    //Gender gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        myImage = (ImageView) findViewById(R.id.myImage);
        addPicture = (Button) findViewById(R.id.addButton);
        name = (EditText) findViewById(R.id.name);
        profile = (EditText) findViewById(R.id.profile);
        about = (EditText) findViewById(R.id.about);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        none = (RadioButton) findViewById(R.id.none);
        dateChangeButton = (Button) findViewById(R.id.dateChangeButton);

        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = 1996;
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        thumb1 = (ImageView) findViewById(R.id.Thumb1);
        thumb2 = (ImageView) findViewById(R.id.Thumb2);
        thumb3 = (ImageView) findViewById(R.id.Thumb3);
        thumb4 = (ImageView) findViewById(R.id.Thumb4);
        thumb5 = (ImageView) findViewById(R.id.Thumb5);

        switch5 = (Switch) findViewById(R.id.switch5);
        switch4 = (Switch) findViewById(R.id.switch4);
        switch3 = (Switch) findViewById(R.id.switch3);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch1 = (Switch) findViewById(R.id.switch1);

        uploadPicture = new UploadPicture(this, R.id.myImage);
        fbButton=(LoginButton) findViewById(R.id.facebookButton);

        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                Log.v("onSuccess","Hurray");
                String userID=loginResult.getAccessToken().getUserId();
                String tokenID=loginResult.getAccessToken().getToken();
            }

            @Override
            public void onCancel()
            {
                Log.v("onCancel","Boooo");
            }

            @Override
            public void onError(FacebookException exception)
            {
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });

        //uploading picture object
        //datePick=new dateChanger(this,R.id.textView3);
        //The switches at the end
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thumb5.setImageResource(R.drawable.thumbsup);
                else
                    thumb5.setImageDrawable(null);
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thumb4.setImageResource(R.drawable.thumbsup);
                else
                    thumb4.setImageDrawable(null);
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thumb3.setImageResource(R.drawable.thumbsup);
                else
                    thumb3.setImageDrawable(null);
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thumb2.setImageResource(R.drawable.thumbsup);
                else
                    thumb2.setImageDrawable(null);
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thumb1.setImageResource(R.drawable.thumbsup);
                else
                    thumb1.setImageDrawable(null);
            }
        });

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPicture.askForPermissions();
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i,upload_id );
            }
        });


    }

    //On choosing an image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case upload_id:
                String picturePath = uploadPicture.ActivityResult(requestCode, resultCode, data);
                if (!picturePath.equals(""))
                    myImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            case fb_id:
                Log.v("requestcode",""+requestCode);
                callbackManager.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }


    //For choosing date
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    //For choosing gender
    public void genderButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    // set gender as male
                    break;
            case R.id.female:
                if (checked)
                    // set gender as female
                    break;
            case R.id.none:
                if (checked)
                    // set gender as unspecified
                    break;

        }
    }
}