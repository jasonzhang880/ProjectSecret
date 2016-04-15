package com.example.secret.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.secret.Config;
import com.example.secret.Id.MyContacts;
import com.example.secret.R;
import com.example.secret.net.Message;
import com.example.secret.net.TimeLine;
import com.example.secret.net.UploadContacts;
import com.example.secret.tools.MD5Tool;

import org.json.JSONArray;

import java.util.List;


/**
 * Created by Administrator on 2016/3/15.
 */
public class ActTimeline extends ListActivity {

    private String phone_num,token,phone_md5;
    private ActTimelineMessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);
        adapter=new ActTimelineMessageListAdapter(this);
        setListAdapter(adapter);


        phone_num=getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token=getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5= MD5Tool.md5(phone_num);
//        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
        new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
//                pd.dismiss();
                loadMessage();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
//                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
                    startActivity(new Intent(getBaseContext(),ActLogin.class));
                    finish();
                }else {
                    loadMessage();
                }
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(Config.TAG, "调用onListItemClick方法");
        Message msg=adapter.getItem(position);
        Intent intent=new Intent(getBaseContext(),ActMessage.class);
        intent.putExtra(Config.KEY_MSG,msg.getMsg());
        intent.putExtra(Config.KEY_MSG_ID, msg.getMsgId());
        intent.putExtra(Config.KEY_PHONE_MD5,msg.getPhone_md5());
        intent.putExtra(Config.KEY_TOKEN,token);
        startActivity(intent);
        Log.d(Config.TAG, "启动ActMessage");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_act_timeline,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuShowActPublish :
                Intent intent=new Intent(getBaseContext(),ActPublish.class);
                intent.putExtra(Config.KEY_PHONE_MD5,phone_md5);
                intent.putExtra(Config.KEY_TOKEN,token);
                startActivityForResult(intent,0);
                break;
            default:
                break;
        }

        return true;
    }

    private void loadMessage() {
//        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));

        new TimeLine(phone_md5, token, 1, 20, new TimeLine.SuccessCallback() {
            @Override
            public void onSuccess(int page, int perpage,List<Message> timeline) {
//                pd.dismiss();
                adapter.addAll(timeline);
                setListAdapter(adapter);
                Log.d(Config.TAG, "ActTimeline设置适配器成功");
            }
        }, new TimeLine.FailCallback() {
            @Override
            public void onFail(int errorCode) {
//                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
                    startActivity(new Intent(getBaseContext(),ActLogin.class));
                    finish();
                } else {
                    Toast.makeText(getBaseContext(),R.string.fail_to_load_timeline_data,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Config.NEED_REFRESH:
                loadMessage();
                break;
            default:
                break;
        }
    }
}
