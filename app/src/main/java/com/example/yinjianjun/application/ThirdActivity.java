package com.example.yinjianjun.application;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_third);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.activity_third,
                new String[]{"title","info","img"},
                new int[]{R.id.title,R.id.info,R.id.img});
        this.setListAdapter(adapter);

    }



    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String,Object>
        map = new HashMap<String,Object>();
        map.put("title","G1");
        map.put("info","google 1");
        map.put("img",R.drawable.img1);
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("title","G2");
        map.put("info","google 2");
        map.put("img", R.drawable.img2);
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("title","G3");
        map.put("info","google 3");
        map.put("img", R.drawable.img3);
        list.add(map);
        return list;
    }

}
