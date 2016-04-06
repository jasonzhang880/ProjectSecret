package com.example.secret.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));
        new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallback() {
            @Override
            public void onSuccess() {
                pd.dismiss();
                loadMessage();
            }
        }, new UploadContacts.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                pd.dismiss();
                if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
                    startActivity(new Intent(getBaseContext(),ActLogin.class));
                    finish();
                }else {
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage() {
        final ProgressDialog pd=ProgressDialog.show(this,getResources().getString(R.string.connecting),getResources().getString(R.string.connecting_to_server));

        new TimeLine(phone_md5, token, 1, 20, new TimeLine.SuccessCallback() {
            @Override
            public void onSuccess(int page, int perpage,List<Message> timeline) {
                pd.dismiss();

                adapter.addAll(timeline);
                setListAdapter(adapter);
            }
        }, new TimeLine.FailCallback() {
            @Override
            public void onFail() {
                pd.dismiss();
                Toast.makeText(getBaseContext(),R.string.fail_to_load_timeline_data,Toast.LENGTH_LONG).show();
            }
        });
        System.out.println(">>>>>>>>>>>>>>>>loadMessage");
    }

}
