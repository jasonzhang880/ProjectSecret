package com.example.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.secret.Id.MyContacts;
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
            intent.putExtra(Config.KEY_PHONE_NUM,phone_num);
            startActivity(intent);
        }else {
            startActivity(new Intent(getBaseContext(),ActLogin.class));
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
