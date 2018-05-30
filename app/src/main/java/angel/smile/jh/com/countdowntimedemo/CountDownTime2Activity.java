package angel.smile.jh.com.countdowntimedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CountDownTime2Activity extends AppCompatActivity {
    TextView tv_count_show;
    Button btn_back;
    Intent countTimerServiceIntent;
    public static final String CODE = "code";
    private BroadcastReceiver countTimeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("BroadcastReceiver", "action:==" + action);
            if (CODE.equals(action)) {
                boolean isenable = intent.getBooleanExtra(MyCountTimeCode.IS_ENABLE, false);
                String msg = intent.getStringExtra(MyCountTimeCode.MESSAGE);
//                tv_count_show.setEnabled(isenable);
                tv_count_show.setText(msg);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_time2);
        tv_count_show = findViewById(R.id.tv_show_time2);
        btn_back = findViewById(R.id.btn_time_back);
        initLinstener();
        //注册接收验证码计时器信息的广播
        IntentFilter filter = new IntentFilter(CODE);
        registerReceiver(countTimeReceiver, filter);
    }

    private void initLinstener() {
        countTimerServiceIntent = new Intent(CountDownTime2Activity.this, CountTimeService.class);
        countTimerServiceIntent.setAction(CODE);
        tv_count_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("网络问题", "是否有网络:=" + NetWorkUtils.isNetWorkAvailable(CountDownTime2Activity.this));
                if (NetWorkUtils.isNetWorkAvailable(CountDownTime2Activity.this)) {
                    tv_count_show.setEnabled(false);
                    startService(countTimerServiceIntent);
                } else {
                    Toast.makeText(CountDownTime2Activity.this, "噢,天哪,你的网络罢工啦", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimerServiceIntent != null) stopService(countTimerServiceIntent);
        if (countTimeReceiver != null) unregisterReceiver(countTimeReceiver);
    }
}
