package com.example.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.secret.activity.ActLogin;
import com.example.secret.activity.ActTimeline;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        MyContacts.getContactsJSONString(this);
//        Log.d(Config.TAG, "联系人读取结果" +MyContacts.getContactsJSONString(this));
        String token=Config.getCachedToken(this);
        String phone_num=Config.getCachedPhoneNum(this);
        if (token!=null&&phone_num!=null) {
            Intent intent=new Intent(this,ActTimeline.class);
            intent.putExtra(Config.KEY_TOKEN,token);
            Log.d(Config.TAG, "MainActivity-----------token值"+token);
            intent.putExtra(Config.KEY_PHONE_NUM,phone_num);
            startActivity(intent);
        }else {
            startActivity(new Intent(getBaseContext(),ActLogin.class));
        }
        finish();
    }

}
