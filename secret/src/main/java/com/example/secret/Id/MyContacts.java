package com.example.secret.Id;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.secret.Config;
import com.example.secret.tools.MD5Tool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class MyContacts {
    public static String getContactsJSONString(Context context) {
        Cursor cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String phone_NUM;
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject;
        while(cursor.moveToNext()) {
            phone_NUM=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if(phone_NUM.charAt(0)=='+'&& phone_NUM.charAt(1)=='8'&& phone_NUM.charAt(2)=='6') {
                phone_NUM = phone_NUM.substring(3);
            }
            jsonObject=new JSONObject();
            try {
                jsonObject.put(Config.KEY_PHONE_MD5, MD5Tool.md5(phone_NUM));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
