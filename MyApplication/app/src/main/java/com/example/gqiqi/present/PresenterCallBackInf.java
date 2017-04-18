package com.example.gqiqi.present;

public interface PresenterCallBackInf {
    void onBusinessSuccess(String data);

    void onBusinessFailure(String retCode, String retDesc);

    void onHttpFailure(String statusCode, String responseBody);
}
