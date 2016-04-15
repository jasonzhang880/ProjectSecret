package com.example.secret.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.secret.Config;
import com.example.secret.R;
import com.example.secret.net.Comment;
import com.example.secret.net.GetComment;
import com.example.secret.net.PubComment;
import com.example.secret.tools.MD5Tool;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ActMessage extends ListActivity {

    private String phone_md5;
    private String msg;
    private String msg_Id;
    private String token;
    private TextView tvMsg;
    private EditText etComment;
    private Button  btnComment;

    private ActMessageCommentListAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_message);

        commentAdapter=new ActMessageCommentListAdapter(getBaseContext());
        setListAdapter(commentAdapter);

        Log.d(Config.TAG, "ActMessage启动成功");

        tvMsg=(TextView)findViewById(R.id.tvMessage);
        etComment=(EditText)findViewById(R.id.etComment);
        btnComment=(Button)findViewById(R.id.btnSendComment);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etComment.getText())) {
                    Toast.makeText(getBaseContext(),R.string.comment_cant_be_empty,Toast.LENGTH_LONG).show();
                    return;
                } else {
                    new PubComment(MD5Tool.md5(Config.getCachedPhoneNum(getBaseContext())), token, etComment.getText().toString(), msg_Id, new PubComment.SuccessCallback() {
                        @Override
                        public void onSuccess() {
                            etComment.setText("");
                            getComment();
                        }
                    }, new PubComment.FailCallback() {
                        @Override
                        public void onFail(int errorCode) {
                            if(errorCode==Config.RESULT_STATUS_INVALID_TOKEN) {
                                startActivity(new Intent(getBaseContext(),ActLogin.class));
                            } else {
                                Toast.makeText(getBaseContext(), R.string.fail_to_pubComment,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        Intent data=getIntent();
        phone_md5=data.getStringExtra(Config.KEY_PHONE_MD5);
        msg=data.getStringExtra(Config.KEY_MSG);
        msg_Id=data.getStringExtra(Config.KEY_MSG_ID);
        token=data.getStringExtra(Config.KEY_TOKEN);
        Log.d(Config.TAG, "从ActTimeline传入的数据"+phone_md5+"----"+msg+"----"+msg_Id+"----"+token);
        tvMsg.setText(msg);

        getComment();


    }

    private void getComment() {
        new GetComment(phone_md5, token, msg_Id, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, String page, String perpage, List<Comment> comments) {
                commentAdapter.clear();
                commentAdapter.addAll(comments);
                setListAdapter(commentAdapter);
            }
        }, new GetComment.FailCallback() {
            @Override
            public void onFail(int errorCode) {
                if (errorCode== Config.RESULT_STATUS_INVALID_TOKEN) {
                    startActivity(new Intent(getBaseContext(),ActLogin.class));
                }
                else {
                    Toast.makeText(getBaseContext(), R.string.fail_to_get_comment, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
