package com.example.secret.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.secret.R;
import com.example.secret.net.GetCode;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class ActLogin extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        etPhone=(EditText)findViewById(R.id.etPhoneNum);
        findViewById(R.id.btnGetCode).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(TextUtils.isEmpty(etPhone.getText())) {
                            Toast.makeText(getBaseContext(), R.string.phone_num_cant_be_empty,Toast.LENGTH_LONG).show();
                            return;
                        }
                        final ProgressDialog pd=ProgressDialog.show(getBaseContext(),getString(R.string.connecting),getString(R.string.connecting_to_server));
                        new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
                            @Override
                            public void onSuccess() {
                                pd.dismiss();
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
    }

    private EditText etPhone;
}
