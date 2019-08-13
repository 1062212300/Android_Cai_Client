package cn.edu.hebtu.software.cai;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class JushApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //设置开启调试模式
        JPushInterface.setDebugMode(true);
        //初始化
        JPushInterface.init(this);
    }
}
