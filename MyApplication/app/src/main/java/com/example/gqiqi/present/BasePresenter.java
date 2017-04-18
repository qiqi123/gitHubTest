package com.example.gqiqi.present;

import android.content.Context;

import com.example.gqiqi.common.BaseBean;
import com.example.gqiqi.model.BaseModel;


public class BasePresenter {
    protected Context mContext = null;
    protected String urlCode = "";
    private BaseModel baseModel = null;

    private BasePresenter(){
    }

    public BasePresenter(Context context, String urlCode) {
        mContext = context;
        this.urlCode = urlCode;
        baseModel = new BaseModel(mContext);
    }

    public void setUrlCode(String urlCode){
        this.urlCode = urlCode;
    }

    public void getMaskLayer(PresenterCallBackInf presenterCallBackInf) {
        if (null != presenterCallBackInf) {
            baseModel.getNetData(null, urlCode, presenterCallBackInf);
        }
    }
}
