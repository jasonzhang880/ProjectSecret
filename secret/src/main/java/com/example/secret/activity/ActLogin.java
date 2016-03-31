package com.example.secret.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secret.Config;
import com.example.secret.R;
import com.example.secret.net.GetCode;
import com.example.secret.net.Login;
import com.example.secret.tools.MD5Tool;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class ActLogin extends Activity{
    private EditText etPhone;
    private EditText etCode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        etPhone=(EditText)findViewById(R.id.etPhoneNum);
        etCode=(EditText)findViewById(R.id.etCode);

        findViewById(R.id.btnGetCode).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(TextUtils.isEmpty(etPhone.getText())) {
                            Toast.makeText(getBaseContext(), R.string.phone_num_cant_be_empty,Toast.LENGTH_LONG).show();
                            return;
                        }
//                        final ProgressDialog pd=ProgressDialog.show(getBaseContext(),getString(R.string.connecting),getString(R.string.connecting_to_server));
                        new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
                            @Override
                            public void onSuccess() {
//                                pd.dismiss();
                                Toast.makeText(getBaseContext(), R.string.success_to_get_code,Toast.LENGTH_LONG).show();
                            }
                        }, new GetCode.FailCallback() {
                            @Override
                            public void onFail() {
                                Toast.makeText(getBaseContext(), R.string.fail_to_get_code,Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etPhone.getText())) {
                    Toast.makeText(getBaseContext(), R.string.phone_num_cant_be_empty,Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(etCode.getText().toString())) {
                    Toast.makeText(getBaseContext(), R.string.code_cant_be_empty,Toast.LENGTH_LONG).show();
                    return;
                }

                new Login(MD5Tool.md5(etPhone.getText().toString()), etCode.getText().toString(), new Login.SuccessCallback() {
                    @Override
                    public void onSuccess(String token) {
                        Config.cacheToken(getBaseContext(), token);
                        Config.cachePhoneNum(getBaseContext(),etPhone.getText().toString());
                        Intent i=new Intent(ActLogin.this,ActTimeline.class);
                        i.putExtra(Config.KEY_TOKEN,token);
                        i.putExtra(Config.KEY_PHONE_NUM,etPhone.getText().toString());
                        startActivity(i);

                        finish();
                    }
                }, new Login.FailCallback() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getBaseContext(), R.string.login_failed,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


}
