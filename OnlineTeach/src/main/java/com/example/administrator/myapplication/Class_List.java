package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class Class_List extends Activity {

    private String[] class_level_array=new String[] {"一年级","二年级","三年级","四年级","五年级","六年级"};

    private String[] descs=new String[] {"一年级","二年级","三年级","四年级","五年级","六年级"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes_list);

        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();

        for(int i=0;i<class_level_array.length;i++) {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("class_level",class_level_array[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(this, listItems, R.layout.item_class_list, new String []{"class_level"}, new int[]{R.id.item_show});

        ListView list=(ListView)findViewById(R.id.class_list);
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(class_level_array[i]+"被选择了");

                Intent intent=new Intent(getBaseContext(),Units_List.class);
                intent.putExtra("class",i);
                startActivity(intent);
            }
        });
    }
}
