package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.fragment.Fragment_one;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Util {
    private static Util util=new Util();

    private Util() {

    }
    public static Util getInstance(){
        return util;
    }
    Handler handler=new Handler();

    public Boolean isWiFi(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }

    public void getImage(final String path, final ImageView img){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(7000);
                    conn.setConnectTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    Log.i("xxx","图片"+responseCode);
                    if (responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageBitmap(bitmap);
                                Log.i("xxx","图片设置成功");
                            }
                        });
                    }else {
                        Log.i("xxx","图片设");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void getJson(final String path, final Inter inter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(7000);
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        int len=0;
                        StringBuilder sb = new StringBuilder();
                        byte[] bytes = new byte[1024];
                        while ((len=inputStream.read(bytes))!=-1){
                            String s = new String(bytes, 0, len);
                            sb.append(s);
                        }
                        final String s = sb.toString();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                    inter.Ok(s);
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface Inter{
        void Ok(String json);
    }
}
