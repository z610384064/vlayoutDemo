package com.example.administrator.myapplication;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
        initX5();
    }
    private void initX5() {
        QbSdk.setDownloadWithoutWifi(true);

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
                QbSdk.setTbsListener(new TbsListener() {
                    @Override
                    public void onDownloadFinish(int i) {
                        Log.d("app", " onDownloadFinish is " + i);
                    }

                    @Override
                    public void onInstallFinish(int i) {

                    }

                    @Override
                    public void onDownloadProgress(int i) {
                        Log.d("app", " onDownloadProgress is " + i);
                    }
                });
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);




    }

}
