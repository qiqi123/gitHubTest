package com.example.gqiqi.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.g_qi_qi.myapplication.R;
import com.example.gqiqi.common.ImageUtil;
import com.example.gqiqi.common.RepoBean;
import com.example.gqiqi.common.Urls;
import com.example.gqiqi.common.UserBean;
import com.example.gqiqi.present.BasePresenter;
import com.example.gqiqi.present.PresenterCallBackInf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private EditText inputEdit = null;
    private TextView userLan = null;
    private ImageView userIcon = null;
    private LinearLayout layout = null;
    private BasePresenter basePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEdit = (EditText) findViewById(R.id.input_edit);
        userLan = (TextView) findViewById(R.id.user_lan);
        userIcon = (ImageView) findViewById(R.id.user_icon);
        layout = (LinearLayout) findViewById(R.id.layout);
        basePresenter = new BasePresenter(MainActivity.this, Urls.USER_URl);
        (findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = inputEdit.getText().toString().trim() + "";
                Toast.makeText(v.getContext(), "userName" + userName, Toast.LENGTH_LONG).show();
                basePresenter.setUrlCode(Urls.USER_URl + userName);
                basePresenter.getMaskLayer(userPresentCallBack);
                userLan.setText("请稍后。。。。。。");
            }
        });
    }

    private PresenterCallBackInf repoPresentCallBack = new PresenterCallBackInf() {
        @Override
        public void onBusinessSuccess(final String data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<RepoBean> list = new ArrayList<RepoBean>(JSONArray.parseArray(data, RepoBean.class));
                    int temp = 0;
                    String language = "";
                    List <String> languageList = new ArrayList();
                    if (null != list && list.size() > 0) {
                        for (RepoBean repoItem : list) {
                            languageList.add(repoItem.getLanguage());
                        }

                        for(String languageItem : languageList){
                            int currCunt = Collections.frequency(languageList, languageItem);
                            if (temp <= currCunt) {
                                temp = currCunt;
                                language = languageItem;
                            }
                        }
                        layout.setVisibility(View.VISIBLE);
                        userLan.setText(userLan.getText() + "使用的最多的语言是：" +language);
                    }
                }
            });
        }

        @Override
        public void onBusinessFailure(String retCode, String retDesc) {

        }

        @Override
        public void onHttpFailure(String statusCode, String responseBody) {

        }
    };

    private PresenterCallBackInf userPresentCallBack = new PresenterCallBackInf() {
        @Override
        public void onBusinessSuccess(final String data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    UserBean responseBean = JSON.parseObject(data, UserBean.class);
                    if (responseBean.getItems().size() > 0) {
                        ImageUtil.displayGlide(MainActivity.this, userIcon, responseBean.getItems().get(0).getAvatar_url(), ImageUtil.NODEFAULT);
                        basePresenter.setUrlCode(responseBean.getItems().get(0).getRepos_url());
                        basePresenter.getMaskLayer(repoPresentCallBack);
                        userLan.setText(responseBean.getItems().get(0).getLogin());
                    }
                }
            });
        }

        @Override
        public void onBusinessFailure(String retCode, String retDesc) {

        }

        @Override
        public void onHttpFailure(String statusCode, String responseBody) {

        }
    };

}
