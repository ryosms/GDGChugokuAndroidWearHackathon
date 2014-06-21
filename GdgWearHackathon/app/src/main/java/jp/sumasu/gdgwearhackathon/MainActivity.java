package jp.sumasu.gdgwearhackathon;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.MenuItem;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends Activity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.send_button)
    protected void onClickSendButton() {
        Intent viewIntent = new Intent(this, MainActivity.class);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.bell)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bell))
                        .setContentTitle("GDG")
                        .setContentText("")
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.bell, "Bell", viewPendingIntent);

        NotificationCompat.BigPictureStyle iconPageStyle =
                new NotificationCompat.BigPictureStyle()
                        .setBigContentTitle("Second Page")
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        Notification iconNotification = new NotificationCompat.Builder(this).setStyle(iconPageStyle).build();

        Notification notification =
                new WearableNotifications.Builder(notificationBuilder)
                        .setHintHideIcon(true)
                        .addPage(iconNotification)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(0, notification);
    }
}
