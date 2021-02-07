package com.example.androidbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.androidbasics.App.CHANNEL_1_ID;
import static com.example.androidbasics.App.CHANNEL_2_ID;

public class NotificationActivity extends AppCompatActivity {
    private NotificationManagerCompat compat;


    EditText titleET, messageET;
    Button channelOneBtn,channelTwoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        compat = NotificationManagerCompat.from(this);
        setupUI();

    }

    void setupUI(){
        titleET = findViewById(R.id.titleET);
        messageET = findViewById(R.id.messageET);
        channelOneBtn = findViewById(R.id.channelOneBtn);
        channelTwoBtn = findViewById(R.id.channelTwoBtn);


        channelOneBtn.setOnClickListener(view -> {

            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    0, intent,0);

            Intent broadCastIntent = new Intent(this, NotificationReceiver.class);
            broadCastIntent.putExtra("toastMessage", messageET.getText().toString());

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle(titleET.getText().toString())
                    .setContentText(messageET.getText().toString())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(contentIntent);
            Notification notification = builder.build();

            //id=1 will over write the same notification.
            compat.notify(1, notification);
        });

        channelTwoBtn.setOnClickListener(view -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_two)
                    .setContentTitle(titleET.getText().toString())
                    .setContentText(messageET.getText().toString())
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE);
            Notification notification = builder.build();

            //id=1 will over write the same notification.
            compat.notify(2, notification);
        });
    }
}