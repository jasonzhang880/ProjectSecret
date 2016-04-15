package com.example.secret.net;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class Comment {
    private String content,phone_md5;
    public Comment(String phone_md5,String content) {
        this.content=content;
        this.phone_md5=phone_md5;
    }

    public String getContent() {
        return content;
    }

    public String getPhone_md5() {
        return phone_md5;
    }
}
