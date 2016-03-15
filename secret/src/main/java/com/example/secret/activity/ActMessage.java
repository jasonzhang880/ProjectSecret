package com.example.secret.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.secret.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ActMessage extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);
    }
}
