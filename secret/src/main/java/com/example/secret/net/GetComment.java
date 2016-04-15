package com.example.secret.net;


import android.util.Log;

import com.example.secret.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class GetComment {
    public GetComment(String phone_md5,String token,String msgId, int page, int perpage,final SuccessCallback successCallback,final FailCallback failCallback ) {
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    Log.d(Config.TAG, "GetComment获取的数据-------------"+jsonObject.toString());
                    switch (jsonObject.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCESS:
                            if (successCallback!=null) {
                                Log.d(Config.TAG, "GetComment将数据传入onSuccess方法-------------");
                                JSONArray jsonArray=jsonObject.getJSONArray(Config.KEY_COMMENTS);

                                List<Comment> listComment=new ArrayList<>();
                                JSONObject object;
                               for (int i=0;i<jsonArray.length();i++) {
                                    object=jsonArray.getJSONObject(i);
                                    listComment.add(new Comment(object.getString(Config.KEY_PHONE_MD5), object.getString(Config.KEY_CONTENT)));
                               }
                                successCallback.onSuccess(jsonObject.getString(Config.KEY_MSG_ID),
                                        jsonObject.getString(Config.KEY_PAGE),
                                        jsonObject.getString(Config.KEY_PERPAGE),
                                        listComment);
                                Log.d(Config.TAG, "-------------GetComment的onSuccess方法执行结束-------------");
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if (failCallback!=null) {
                                Log.d(Config.TAG, "GetComment无效方法-------------");
                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallback!=null) {
                                Log.d(Config.TAG, "GetComment失败方法-------------");
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback!=null) {
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }

            }
        },Config.KEY_ACTION,Config.ACTION_GET_COMMENT,
                Config.KEY_TOKEN,token,
                Config.KEY_MSG_ID,msgId,
                Config.KEY_PAGE,page+"",
                Config.KEY_PERPAGE,perpage+"");
    }

    public static interface SuccessCallback{
        void onSuccess(String msgId,String page,String perpage,List<Comment> comments);
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}
