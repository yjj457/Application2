package yjj.android.util;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by yinjianjun on 15-7-30.
 */
public class Alert extends ActionBarActivity {
    public void alert(String title, String content){
        new AlertDialog.Builder(Alert.this).setTitle(title).setMessage(content).show();
    }
}
