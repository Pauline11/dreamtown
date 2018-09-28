package com.example.pagetabdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class LogInActivity extends Activity {

    private Button signIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          //      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * 标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效，需要去掉标题
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);

        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (Button) findViewById(R.id.sign_up);

        //TODO 登录注册事件

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}


