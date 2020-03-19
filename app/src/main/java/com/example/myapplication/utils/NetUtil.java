package com.example.songwenlong20191222;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil extends AsyncTask<String, Void, String> {
    private static final String TAG = "NetUtil";
    private CallBack callBack;

    public NetUtil(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String path = strings[0];
        try {
            String json = doGet(path);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (callBack != null) {
            if (!TextUtils.isEmpty(s)) {
                callBack.onSuccess(s);
            } else {
                callBack.onFailure("失败");
            }
        }
    }

    public interface CallBack {
        void onSuccess(String json);

        void onFailure(String msg);
    }

    private boolean netStutas() {

        ConnectivityManager connettivyService = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connettivyService.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            boolean available = activeNetworkInfo.isAvailable();
            return available;
        }
        return false;
    }

    private String doGet(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len));
            }
            String json = builder.toString();
            Log.d(TAG, "doGet: " + json);
            return json;
        }

        return null;
    }
}
