/*  A service is created by extended the Service class.
 *  onBind(), onStartCommand() and onDestroy() must be overridden
 *
 *  The onBind() method enables a programmer to bind an activity to a service. This in turn enables an activity
 *  to directly access members and methods inside a service.
 *
 *  The onStartCommand() method is called when a programmer starts the service explicitly using the startService()
 *  method. This method signifies the start of the service, and the programmer should code it to do the
 *  things he/she needs to do for his/her service. In this method, if the constant START_STICKY is returned it
 *  implies that the service will continue to run until it is explicitly stopped.
 *
 *  The onDestroy() method is called when the service is stopped using the stopService() method. This is
 *  where a programmer cleans up the resources used by the service.
 *
 *  All services that you have created must be declared in the AndroidManifest.xml file, like this:
 *  <service android:name=".MyService" />
 *
 *  If you want your service to be available to other applications, you can always add an intent filter with
 *  an action name, like this:
 *  <service android:name=".MyService">
 *     <intent-filter>
 *        <action android:name="com.example.MyService" />
 *     </intent-filter>
 *  </service>
 *
 *  To start a service, you use the startService() method, like this:
 *  startService(new Intent(getBaseContext(), MyService.class));
 *
 *  If you are calling an external service, then the call to the startService() method will look like this:
 *  startService(new Intent("com.example.MyService"));
 *
 *  To stop a service, use the stopService() method (or stopSelf() ), like this:
 *  stopService(new Intent(getBaseContext(), MyService.class));
 *
 */
package com.example.week13_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    public IBinder onBind(Intent arg0) {
        return null;
    }

    //1. onCreate()
    //2. onStartCommand();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Wants this service to continue running until it is explicitly
        // stopped, so return START_STICKY
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}