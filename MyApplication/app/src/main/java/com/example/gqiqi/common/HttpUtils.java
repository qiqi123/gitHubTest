package com.example.gqiqi.common;

import android.content.Context;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by G-Qi-Qi on 2017/4/17.
 */

public class HttpUtils {
    private HttpUtils() {
        initHttp();
    }

    OkHttpClient okHttpClient = null;
    Request request = null;

    private void initHttp(){
        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(Urls.USER_URl)
                .build();
    }

    public static HttpUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void requestDatas(final Context context, final String url, final Object obj,  Callback iCallBack) {
        if(okHttpClient == null || request == null){
            initHttp();
        }
        request = request.newBuilder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(iCallBack);
    }



    private static class LazyHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }
}
