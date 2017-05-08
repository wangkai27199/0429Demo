package com.example.dell.a0429demo;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by DELL on 2017/4/29.
 */

public class MyAsyncTask extends AsyncTask<Object,Void,String> {
    @Override
    protected String doInBackground(Object... params) {

        Map map = (Map) params[0];
        String result = "";
        String path = "http://www.93.gov.cn/93app/data.do";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            StringBuilder builder = new StringBuilder();
            Set<String> set = map.keySet();
            for (String key : set){
                builder.append(key);
                builder.append("=");
                builder.append(map.get(key));
                builder.append("&");
            }
            builder = builder.deleteCharAt(builder.length()-1);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(builder.toString().getBytes());
            outputStream.flush();

            if (connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int len = 0;
                while((len = is.read(b))!= -1){
                    bos.write(b,0,len);
                }
                result = bos.toString();
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
