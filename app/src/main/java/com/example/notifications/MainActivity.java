package com.example.notifications;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private String CHANNEL_ID =  "My_Channel_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but = findViewById(R.id.but);
        NotificationHelper.creatNotificationChannels(this);

        but.setOnClickListener(v->NotificationHelper.sendNotification(2,
                2,
                this,
                "Nowe Powidomienie 3TP",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et imperdiet nunc.",
                1,0));

        Button but_large = findViewById(R.id.but_large);

        but_large.setOnClickListener(v->NotificationHelper.sendNotification(1,
                2,
                this,
                "Nowe Powidomienie 3TP",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et imperdiet nunc. Aliquam eleifend, metus tincidunt ornare sollicitudin, tellus erat lobortis lorem, non viverra risus orci ut purus. Morbi ultricies.",
                2,0));

        Button but_img = findViewById(R.id.but_img);

        but_img.setOnClickListener(v->NotificationHelper.sendNotification(2,
                2,
                this,
                "Nowe Powidomienie 3TP",
                null,
                3,R.drawable.dice1));

    }
    private  void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Kanal Powiadomien";
            String discription = "Opis Kanalu Powiadomien";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(discription);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotificationLargeIcon(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},1);
                return;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dice1);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Nowe Powidomienie 3TP")
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1, builder.build());
        }
    }

    private void sendNotificationLong(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},1);
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Nowe Powidomienie 3TP")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et imperdiet nunc. Aliquam eleifend, metus tincidunt ornare sollicitudin, tellus erat lobortis lorem, non viverra risus orci ut purus. Morbi ultricies."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(2, builder.build());
        }
    }

    private void sendNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Nowe Powidomienie 3TP")
                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean et imperdiet nunc. Aliquam eleifend, metus tincidunt ornare sollicitudin, tellus erat lobortis lorem, non viverra risus orci ut purus. Morbi ultricies.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(3, builder.build());
    }
}
