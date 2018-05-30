package angel.smile.jh.com.countdowntimedemo;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class CountDownTime3Activity extends AppCompatActivity {
    TextView tv_show_time;
    Button btn_back;
    CountDownT downT;
    private Handler mHandler;
    private boolean isStart = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_time3);
        tv_show_time = findViewById(R.id.count_time);
        btn_back = findViewById(R.id.btn_count_back);

        findViewById(R.id.btn_start_count_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downT = new CountDownT(20000, 1000);
                downT.start();

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharedPreferences = getSharedPreferences("countTime", MODE_PRIVATE);
        isStart = sharedPreferences.getBoolean("isStart", false);
        long time = sharedPreferences.getLong("countTime", 0);
        if (isStart && time != 0) {
            downT = new CountDownT(time, 1000);
            downT.start();
        }
    }

    class CountDownT extends CountDownTimer {

        public CountDownT(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_show_time.setText((millisUntilFinished / 1000 - 1) + "s");
            Log.e("倒计时事件", "time:==" + millisUntilFinished);
            if (isFinishing()) {
                Log.e("倒计时页面返回事件", "time:==" + millisUntilFinished);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("countTime", millisUntilFinished);
                editor.putBoolean("isStart", true);
                editor.commit();
            }
        }

        @Override
        public void onFinish() {

        }
    }

}
