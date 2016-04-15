package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class Unit_Content extends Activity {
    private int unit_num;
    private TextView textView;
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.unit_content);

        textView=(TextView)findViewById(R.id.unit_contentTv);
        unit_num=getIntent().getIntExtra("unit_num",0);

        textView.setText("第"+unit_num+"单元"+'\n');
        textView.append(getResources().getString(R.string.class5_unit1));

    }
}
