package com.example.yinjianjun.application;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class NextActivity extends ActionBarActivity {

    final static String TAG = "LOGCAT";

    private static final String[] m={"A型","B型","O型","AB型","其他"};
    private TextView view ;
    private Button btnToMain;
    private Button btnToThird;
    private Spinner spinner;
    ArrayAdapter<String> adapter;

    private Button getSelected;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        btnToMain = (Button)findViewById(R.id.toMain);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NextActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnToThird = (Button)findViewById(R.id.toThird);
        btnToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NextActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
        view = (TextView) findViewById(R.id.spinnerText);
        spinner = (Spinner) findViewById(R.id.Spinner01);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);

        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        spinner.setVisibility(View.VISIBLE);

        getSelected = (Button) findViewById(R.id.getSelected);
        getSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
                radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                Log.i(TAG, radioButton.getText().toString());
                showMessage(radioButton.getText().toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_next, menu);
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



    //使用数组形式操作
    class SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            view.setText("你的血型是："+m[arg2]);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    private void showMessage(String content)
    {
        new AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage(content)
            .setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                        DialogInterface dialoginterface, int i) {
                            Log.i(TAG,"23333333");
                        }
                }).show();
    }


}
