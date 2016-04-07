package com.example.secret.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.secret.Config;
import com.example.secret.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ActMessage extends ListActivity {

    private String phone_md5;
    private String msg;
    private String msg_Id;
    private TextView tvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);

        Log.d(Config.TAG, "ActMessage启动成功");

        tvMsg=(TextView)findViewById(R.id.tvMessage);
        Intent data=getIntent();
        phone_md5=data.getStringExtra(Config.KEY_PHONE_MD5);
        msg=data.getStringExtra(Config.KEY_MSG);
        msg_Id=data.getStringExtra(Config.KEY_MSG_ID);

        tvMsg.setText(msg);
    }
}
