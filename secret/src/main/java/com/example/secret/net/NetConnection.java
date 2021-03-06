package com.example.secret.net;

import android.os.AsyncTask;
import android.util.Log;

import com.example.secret.Config;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback,final FailCallback failCallback, final String...kvs) {
        new AsyncTask<Void ,Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuffer paramsStr=new StringBuffer();
                for(int i=0;i<kvs.length;i+=2) {
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");

                }
                    try {
                        URLConnection uc;
                        switch(method) {
                            case POST:
                                uc=new URL(url).openConnection();
                                uc.setDoOutput(true);
                                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                                bw.write(paramsStr.toString());
                                bw.flush();
                                break;
                            default:
                                uc=new URL(url+"?"+paramsStr.toString()).openConnection();
                                break;
                        }
                        Log.d(Config.TAG, "1请求的url" + uc.getURL());
                        Log.d(Config.TAG, "2请求的数据" +paramsStr);
                        BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                        String line=null;
                        StringBuffer result=new StringBuffer();
                        while((line=br.readLine())!=null) {
                            result.append(line);
                        }
                        Log.d(Config.TAG, "3读取的结果" + result);
                        return result.toString();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s!=null) {
                    if (successCallback!=null) {
                        successCallback.onSuccess(s);

                    }
                }else {

                    if (failCallback!=null) {
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(s);
            }

        }.execute();
    }
    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback {
        void onFail();
    }
}
