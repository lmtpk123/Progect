package angel.smile.jh.com.countdowntimedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CountTimerReceiver extends BroadcastReceiver {
    public static final String CODE = "code";
    private String codeTime;

    public String getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(String codeTime) {
        this.codeTime = codeTime;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (CODE.equals(action)) {
            String msg = intent.getStringExtra("");
            setCodeTime(msg);
        }
    }
}
