package com.example.dell.a0429demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by DELL on 2017/4/29.
 */

public class MyAdapter extends BaseAdapter {

    private List<NewsBean.DataBean> list;
    private Context context;
    public MyAdapter(List<NewsBean.DataBean> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getIMAGEURL() == null){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        ViewHolderT holderT = null;
        int type = getItemViewType(position);
        if (convertView == null){
            if (type == 1){
                holder = new ViewHolder();
                convertView = View.inflate(context,R.layout.image_item,null);

                holder.imageView = (ImageView) convertView.findViewById(R.id.imageview_item);
                holder.text_title_item = (TextView) convertView.findViewById(R.id.text_image_title);
                holder.text_data_item = (TextView) convertView.findViewById(R.id.text_image_data);

                convertView.setTag(holder);

            }else {
                holderT = new ViewHolderT();
                convertView = View.inflate(context,R.layout.item_text,null);
                holderT.text_title = (TextView) convertView.findViewById(R.id.text_title);
                holderT.text_data = (TextView) convertView.findViewById(R.id.text_data);

                convertView.setTag(holderT);

            }
        }else {

            if (type == 1){
                holder = (ViewHolder) convertView.getTag();
            }else {
                holderT = (ViewHolderT) convertView.getTag();
            }

        }


        if (type == 1){
            ImageLoader.getInstance().displayImage(list.get(position).getIMAGEURL(),holder.imageView);
            holder.text_title_item.setText(list.get(position).getTITLE());
            holder.text_data_item.setText(list.get(position).getSUBTITLE());
        }else {
            holderT.text_title.setText(list.get(position).getTITLE());
            holderT.text_data.setText(list.get(position).getSUBTITLE());
        }

        return convertView;
    }


    class ViewHolder{
        ImageView imageView;
        TextView text_title_item;
        TextView text_data_item;
    }
    class ViewHolderT{
        TextView text_title;
        TextView text_data;
    }
}
