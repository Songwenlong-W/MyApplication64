package com.example.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.JsonBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BaseAdapt extends BaseAdapter {
    Context context;
    ArrayList<JsonBean.ResultBean> list;

    public BaseAdapt(Context context, ArrayList<JsonBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        JsonBean.ResultBean resultBean = list.get(position);
        int type = resultBean.getType();
        if (type==1){
            return 0;
        }else if (type==2){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder =null;
        if (convertView==null){
            if (type==0){
                convertView=View.inflate(context, R.layout.item1,null);
            }else if (type==1){
                convertView=View.inflate(context, R.layout.item2,null);
            }else {
                convertView=View.inflate(context, R.layout.item3,null);
            }
            holder = new ViewHolder();
            holder.tu=convertView.findViewById(R.id.iv);
            holder.zi=convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        JsonBean.ResultBean resultBean = list.get(position);
        String title = resultBean.getTitle();
        String imgsrc = resultBean.getImgsrc();
        holder.zi.setText(title);
        Picasso.get().load(imgsrc).into(holder.tu);
        return convertView;
    }
    public class ViewHolder{
        public ImageView tu;
        public TextView zi;
    }
}
