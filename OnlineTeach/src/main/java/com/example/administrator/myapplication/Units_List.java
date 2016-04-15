package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class Units_List extends Activity implements View.OnClickListener {
    private int classNum;
    private Button btn_ok1_1,btn_ok1_2,btn_ok1_3,btn_ok1_4,btn_ok1_5,btn_ok1_6,btn_ok1_7,btn_ok1_8,btn_ok1_9,btn_ok1_10,
            btn_ok2_1,btn_ok2_2,btn_ok2_3,btn_ok2_4,btn_ok2_5,btn_ok2_6,btn_ok2_7,btn_ok2_8,btn_ok2_9,btn_ok2_10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list);

        btn_ok1_1=(Button)findViewById(R.id.ok1_1);
        btn_ok1_2=(Button)findViewById(R.id.ok1_2);
        btn_ok1_3=(Button)findViewById(R.id.ok1_3);
        btn_ok1_4=(Button)findViewById(R.id.ok1_4);
        btn_ok1_5=(Button)findViewById(R.id.ok1_5);
        btn_ok1_6=(Button)findViewById(R.id.ok1_6);
        btn_ok1_7=(Button)findViewById(R.id.ok1_7);
        btn_ok1_8=(Button)findViewById(R.id.ok1_8);
        btn_ok1_9=(Button)findViewById(R.id.ok1_9);
        btn_ok1_10=(Button)findViewById(R.id.ok1_10);

        btn_ok2_1=(Button)findViewById(R.id.ok2_1);
        btn_ok2_2=(Button)findViewById(R.id.ok2_2);
        btn_ok2_3=(Button)findViewById(R.id.ok2_3);
        btn_ok2_4=(Button)findViewById(R.id.ok2_4);
        btn_ok2_5=(Button)findViewById(R.id.ok2_5);
        btn_ok2_6=(Button)findViewById(R.id.ok2_6);
        btn_ok2_7=(Button)findViewById(R.id.ok2_7);
        btn_ok2_8=(Button)findViewById(R.id.ok2_8);
        btn_ok2_9=(Button)findViewById(R.id.ok2_9);
        btn_ok2_10=(Button)findViewById(R.id.ok2_10);

        btn_ok1_1.setOnClickListener(this);btn_ok2_1.setOnClickListener(this);
        btn_ok1_2.setOnClickListener(this);btn_ok2_2.setOnClickListener(this);
        btn_ok1_3.setOnClickListener(this);btn_ok2_3.setOnClickListener(this);
        btn_ok1_4.setOnClickListener(this);btn_ok2_4.setOnClickListener(this);
        btn_ok1_5.setOnClickListener(this);btn_ok2_5.setOnClickListener(this);
        btn_ok1_6.setOnClickListener(this);btn_ok2_6.setOnClickListener(this);
        btn_ok1_7.setOnClickListener(this);btn_ok2_7.setOnClickListener(this);
        btn_ok1_8.setOnClickListener(this);btn_ok2_8.setOnClickListener(this);
        btn_ok1_9.setOnClickListener(this);btn_ok2_8.setOnClickListener(this);

        classNum=1+getIntent().getIntExtra("class",1);
    }

    @Override
    public void onClick(View view) {
        Log.d("back_result","触发点击方法");
        Intent intent=new Intent(getBaseContext(),Unit_Content.class);
        switch(view.getId()) {
            case R.id.ok1_1:
                Log.d("back_result","单机第一个按钮");
                intent.putExtra("unit_num",1);
                startActivity(intent);
                break;
            case R.id.ok1_2:
                Log.d("back_result","单机第二个按钮");
                intent.putExtra("unit_num",2);
                startActivity(intent);
                break;
            case R.id.ok1_3:
                Log.d("back_result","单机第三个按钮");
                intent.putExtra("unit_num",3);
                startActivity(intent);
                break;
        }

    }
}
