package com.wl.testveri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.bind_btn);
        String APPKEY="1469bcfb423d2";
        String APPSECRET="36907670428a22ac246885d8e6260cbe";
        //    initSDK(Context context, java.lang.String appkey, java.lang.String appSecrect, boolean warnOnReadContact)
        SMSSDK.initSDK(this, APPKEY, APPSECRET,false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPage registerPage = new RegisterPage();
                registerPage.show(MainActivity.this);
                Toast.makeText(MainActivity.this,"哇哈哈qqqqqqqqqqqq",Toast.LENGTH_SHORT).show();
                registerPage.setRegisterCallback(new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            Toast.makeText(MainActivity.this,"哇哈哈",Toast.LENGTH_SHORT).show();     //最后一步验证成功后
                            Map<String, Object> mapData = (Map<String, Object>) data;
                            String country = (String) mapData.get("country");
                            String phone = (String) mapData.get("phone");
                            //提交用户信息
                            Random r=new Random();
                            String uid=Math.abs(r.nextInt())+"";
                            String nickname="wla";
                            String avatar=null;
                            SMSSDK.submitUserInfo(uid,nickname,avatar,country,phone);
                            startActivity(new Intent(MainActivity.this,OtherActivity.class));
                        }
                    }
                });
            }
        });

    }




}
