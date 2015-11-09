package com.example.yinjianjun.application;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.webservice.manager.WebServicesTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends ActionBarActivity {

    final static String TAG = "LOGCAT";
    WebServicesTask task;
    String result;
    Button btnToNext;
    Button btnGetPwdByYjj;
    TextView txtShowPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToNext = (Button) findViewById(R.id.toNext);
        btnToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.setClass(MainActivity.this, NextActivity.class);
                intent.setClass(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });


        btnGetPwdByYjj = (Button) findViewById(R.id.getPwdByYjj);
        txtShowPwd = (TextView) findViewById(R.id.showPwd);

        btnGetPwdByYjj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "我点了");
                Message msg = handler.obtainMessage();
                msg.what = 111;
                handler.sendMessageDelayed(msg, 500);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 111) {
                task = new WebServicesTask();
            }
            try {
                task.execute("yjj");
                result = task.get(5000, TimeUnit.MILLISECONDS);
                txtShowPwd.setText(result);
                Log.i(TAG, "好了 " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
                result = "";
            } catch (ExecutionException e) {
                e.printStackTrace();
                result = "";
            } catch (TimeoutException e) {
                e.printStackTrace();
                result = "";
            }
        }
    };
}
