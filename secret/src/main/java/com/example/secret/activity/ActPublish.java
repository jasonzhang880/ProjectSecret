package com.example.secret.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secret.Config;
import com.example.secret.R;
import com.example.secret.net.Publish;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class ActPublish extends Activity {

    private EditText etMsgContent;
    private Button btnPublish;
    private String phone_md5,token;
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.aty_publish);

        phone_md5=getIntent().getStringExtra(Config.KEY_PHONE_MD5);
        token=getIntent().getStringExtra(Config.KEY_TOKEN);
        etMsgContent=(EditText)findViewById(R.id.etMsgContent);
        btnPublish=(Button)findViewById(R.id.btnPublish);

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etMsgContent.getText())) {
                    Toast.makeText(getBaseContext(),getString(R.string.publish_cant_be_empty),Toast.LENGTH_LONG);
                    return;
                }

                new Publish(phone_md5, token, etMsgContent.getText().toString(), new Publish.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        setResult(1);

                        Toast.makeText(getBaseContext(),"发布消息成功",Toast.LENGTH_LONG);
                        finish();
                    }
                }, new Publish.FailCallback() {
                    @Override
                    public void onFail(int errCode) {
                        if(errCode==Config.RESULT_STATUS_INVALID_TOKEN) {
                            startActivity(new Intent(getBaseContext(),ActLogin.class));
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(),getString(R.string.fail_to_publish_message),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
