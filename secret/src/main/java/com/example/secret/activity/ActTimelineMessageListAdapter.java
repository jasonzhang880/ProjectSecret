package com.example.secret.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.secret.Config;
import com.example.secret.R;
import com.example.secret.net.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6 0006.
 */
public class ActTimelineMessageListAdapter extends BaseAdapter{
    private Context context=null;
    private List<Message> data =new ArrayList<>();

    public ActTimelineMessageListAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null) {
            view= LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell,null);
        }

        TextView tvCellLabel=(TextView)view.findViewById(R.id.tvCelllabel);
        return null;
    }

    public void addAll(List<Message> data) {
        data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public  Context getContext() {
        return context;
    }
}
