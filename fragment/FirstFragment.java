package com.example.dell.a0429demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.a0429demo.MyAdapter;
import com.example.dell.a0429demo.MyAsyncTask;
import com.example.dell.a0429demo.NewsBean;
import com.example.dell.a0429demo.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by DELL on 2017/4/29.
 */

public class FirstFragment extends Fragment {

    private ListView lv;
    private List<NewsBean.DataBean> list = new ArrayList<NewsBean.DataBean>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            Gson gson = new Gson();
            NewsBean newsBean = gson.fromJson(result, NewsBean.class);
            List<NewsBean.DataBean> data = newsBean.getData();
            list.addAll(data);
            if (list != null){
                lv.setAdapter(adapter);
            }
        }
    };
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item,null);

        lv = (ListView) view.findViewById(R.id.lv);
        adapter = new MyAdapter(list,getActivity());
        getData();
        return view;
    }

    private void getData() {

        Map map = new HashMap();
        map.put("channelId",0);
        map.put("startNum",0);

        try {
            String str = new MyAsyncTask().execute(map).get();
            Message msg = Message.obtain();
            msg.obj = str;
            handler.sendMessage(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
