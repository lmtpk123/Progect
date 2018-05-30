package angel.smile.jh.com.countdowntimedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class CountTimeService extends Service {
    private static final String TAG = "CountTimeService";
    private MyCountTimeCode timeCode;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        String action = intent.getAction();
        Log.e(TAG, "action:=" + action);
        timeCode = new MyCountTimeCode(60000, 1000, this, action);
        timeCode.start();
    }
}
