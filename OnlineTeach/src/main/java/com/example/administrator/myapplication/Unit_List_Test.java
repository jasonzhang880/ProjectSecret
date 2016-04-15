package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class Unit_List_Test extends Activity {

    private GridView gridView1,gridView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list_test);

        gridView1 = (GridView) findViewById(R.id.unit51_grid);
        gridView2 = (GridView) findViewById(R.id.unit52_grid);

        String[] items = getResources().getStringArray(R.array.units);

        List<Map<String,Object>> listUnits=new ArrayList<Map<String,Object>>();

        for(int i=0;i<items.length;i++) {
            Map<String,Object> item=new HashMap<>();
            item.put("one_unit",items[i]);
            listUnits.add(item);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listUnits,R.layout.unit_cell,new String []{"one_unit"},new int[]{R.id.unit_cell_btn});
        SimpleAdapter simpleAdapter1=new SimpleAdapter(this,listUnits,R.layout.unit_cell,new String []{"one_unit"},new int[]{R.id.unit_cell_btn});
        Log.d("item","设置适配器");
        gridView1.setAdapter(simpleAdapter);
        gridView2.setAdapter(simpleAdapter1);

        gridView1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(),Unit_Content.class);
                intent.putExtra("unit_num",i);
                startActivity(intent);
                Log.d("item","第"+i+"个item被选中");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("item","设置监听器");
            }
        });
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(),Unit_Content.class);
                intent.putExtra("unit_num",i);
                startActivity(intent);
                Log.d("item","第"+i+"个item被选中");
            }
        });

        gridView2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(),Unit_Content.class);
                intent.putExtra("unit_num",i);
                startActivity(intent);
                Log.d("item","第"+i+"个item被选中");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("item","设置监听器");
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getBaseContext(),Unit_Content.class);
                intent.putExtra("unit_num",i);
                startActivity(intent);
                Log.d("item","第"+i+"个item被选中");
            }
        });
    }

}
