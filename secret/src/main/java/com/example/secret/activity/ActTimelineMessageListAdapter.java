package com.example.secret.activity;

import android.content.Context;
import android.util.Log;
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
    public Message getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(Config.TAG, "调用自定义适配器getView方法");
        if (view==null) {
            view= LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell,null);
            view.setTag(new ListCell((TextView)view.findViewById(R.id.tvCelllabel)));
            Log.d(Config.TAG, "调用自定义适配器getView方法设置布局成功");
        }
        ListCell lc=(ListCell)view.getTag();

        Message msg=getItem(i);
        lc.getTvCellLabel().setText(msg.getMsg());

        return view;
    }

    public void addAll(List<Message> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public  Context getContext() {
        return context;
    }

    private static class ListCell{
        private TextView tvCellLabel;
        public ListCell(TextView CellLabel) {
            this.tvCellLabel=CellLabel;
        }
        public TextView getTvCellLabel( ) {
            return tvCellLabel;
        }

    }
}
