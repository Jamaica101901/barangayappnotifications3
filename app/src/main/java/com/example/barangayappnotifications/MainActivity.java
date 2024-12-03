package com.example.barangayappnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "default_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        Button buttonNotify = findViewById(R.id.button_notify);
        buttonNotify.setOnClickListener(view -> sendNotification("Barangay Notification", "This is all about barangay notification"));

        Button buttonEmergency = findViewById(R.id.button_emergency);
        buttonEmergency.setOnClickListener(view -> sendEmergencyNotification());

        Button buttonEvent = findViewById(R.id.button_event);
        buttonEvent.setOnClickListener(view -> sendEventNotification());

        Button buttonMaintenance = findViewById(R.id.button_maintenance);
        buttonMaintenance.setOnClickListener(view -> sendMaintenanceNotification());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Send a simple notification
    private void sendNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)  // Set your notification icon
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    private void sendEmergencyNotification() {
        String title = "Emergency Alert!";
        String message = "Severe weather warning in your area. Please take necessary precautions.";
        sendNotification(title, message);
    }

    private void sendEventNotification() {
        String title = "Upcoming Event";
        String message = "Don't miss our community gathering this Saturday at 5 PM!";
        sendNotification(title, message);
    }

    private void sendMaintenanceNotification() {
        String title = "Utilities Maintenance";
        String message = "Scheduled maintenance on water supply tomorrow from 10 AM to 2 PM.";
        sendNotification(title, message);
    }
}