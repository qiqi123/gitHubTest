package com.example.gqiqi.model;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.gqiqi.common.BaseBean;
import com.example.gqiqi.common.HttpUtils;
import com.example.gqiqi.present.PresenterCallBackInf;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BaseModel {
    protected Context mContext = null;
    public BaseModel(Context context) {
        mContext = context;
    }

    public void getNetData(BaseBean baseBean, String code, final PresenterCallBackInf basePresenterInf) {
        HttpUtils.getInstance().requestDatas(mContext, code, baseBean, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                basePresenterInf.onHttpFailure("0", "错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                if(!TextUtils.isEmpty(responseStr)){
                    basePresenterInf.onBusinessSuccess(responseStr);
                }else{
                    basePresenterInf.onBusinessFailure("1","无数据");
                }
            }
        });
    }
}
