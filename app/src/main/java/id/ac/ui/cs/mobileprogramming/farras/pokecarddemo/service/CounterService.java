package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class CounterService extends Service {
    private final static String TAG = "CounterService";
    public static final String COUNTER_BR = "id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.service";
    Intent intent = new Intent(COUNTER_BR);

    private final int REFRESH_RATE = 100;
    private Handler mHandler = new Handler();
    private long counter;
    private String counterText;

    @Override
    public void onCreate() {
        super.onCreate();

        counter = 0;
        mHandler.removeCallbacks(startCounter);
        mHandler.postDelayed(startCounter, 0);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(startCounter);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateCounter (float counter){
        counterText = String.valueOf(counter);
        intent.putExtra("counter", counterText);
        sendBroadcast(intent);

    }//end Update Timer

    private Runnable startCounter = new Runnable() {
        @Override
        public void run() {
            counter += 1;
            updateCounter(counter);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };
}
