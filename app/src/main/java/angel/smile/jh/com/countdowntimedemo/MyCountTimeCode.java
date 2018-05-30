package angel.smile.jh.com.countdowntimedemo;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class MyCountTimeCode extends CountDownTimer {
    private static final String TAG = "MyCountTimeCode";
    public static final String MESSAGE = "countInfo";
    public static final String IS_ENABLE = "isenable";
    private Context mContext;
    private Intent countTimeReceiver;
    private String codemess;

    public String getCodemess() {
        return codemess;
    }

    public void setCodemess(String codemess) {
        this.codemess = codemess;
    }

    public MyCountTimeCode(long millisInFuture, long countDownInterval, Context mContext, String action) {
        super(millisInFuture, countDownInterval);
        this.mContext = mContext;
        countTimeReceiver = new Intent(action);
    }

    public MyCountTimeCode(long millisInFuture, long countDownInterval, Context mContext) {
        super(millisInFuture, countDownInterval);
        this.mContext = mContext;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //每过millisUntilFinished的间隔时间，就会发送广播
        countTimeReceiver.putExtra(IS_ENABLE, false);
        Log.e(TAG, "millisUntilFinished:=" + millisUntilFinished);
        countTimeReceiver.putExtra(MESSAGE, (millisUntilFinished / 1000 - 1) + "\n" + "s后重新发送");
        setCodemess((millisUntilFinished / 1000 - 1) + "\n" + "s后重新发送");
        mContext.sendBroadcast(countTimeReceiver);
    }

    @Override
    public void onFinish() {
        countTimeReceiver.putExtra(IS_ENABLE, true);
        countTimeReceiver.putExtra(MESSAGE, "获取验证码");
        setCodemess("获取验证码");
        mContext.sendBroadcast(countTimeReceiver);
    }
}
