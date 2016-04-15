package com.example.secret.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.secret.Config;
import com.example.secret.R;
import com.example.secret.net.Comment;
import com.example.secret.net.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/4/8 0008.
 */
public class ActMessageCommentListAdapter extends BaseAdapter {
    private List<Comment> comments=new ArrayList<>();
    private Context context;

    public ActMessageCommentListAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null) {
            view=LayoutInflater.from(getContext()).inflate(R.layout.aty_message_comment_cell,null);
            view.setTag(new ListCell((TextView)view.findViewById(R.id.tvCommentCell)));
        }

        ListCell lc=(ListCell)view.getTag();

        Comment comment=getItem(i);
        lc.getTvCommentCell().setText(comment.getContent());//获取一个ListCell对象的TextView成员
        return view;
    }
    public void addAll(List<Comment> data) {  //将所有的评论数据加入List对象中
        this.comments.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        comments.clear();
        notifyDataSetChanged();
    }
    public Context getContext() {
        return context;
    }

    private static class ListCell{
        private TextView tvCommentCell;
        public ListCell(TextView Cell) {
            this.tvCommentCell=Cell;
        }
        public TextView getTvCommentCell( ) {
            return tvCommentCell;
        }

    }
}
