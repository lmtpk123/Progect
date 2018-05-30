package angel.smile.jh.com.countdowntimedemo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CountDownTimeActivity extends AppCompatActivity {
    TextView tv_countTime;
    private CountDownTime countDownTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_time);
        tv_countTime = findViewById(R.id.tv_show_time);
        countDownTime = new CountDownTime(6000, 1000);
        tv_countTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("我那个落", "" + NetWorkUtils.isNetWorkAvailable(CountDownTimeActivity.this));
                if (NetWorkUtils.isNetWorkAvailable(CountDownTimeActivity.this)) {
                    countDownTime.start();
                } else {
                    countDownTime.cancel();
                    Toast.makeText(CountDownTimeActivity.this, "噢,天哪,你的网络罢工啦", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTime != null && this.isFinishing() == true) {
            countDownTime.cancel();
        }
    }

    class CountDownTime extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_countTime.setText("获取验证码" + "\n" + (millisUntilFinished / 1000 - 1) + "s");
        }

        @Override
        public void onFinish() {

        }
    }
}
