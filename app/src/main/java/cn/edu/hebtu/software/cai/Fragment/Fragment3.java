package cn.edu.hebtu.software.cai.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hebtu.software.cai.HomeAdapter;
import cn.edu.hebtu.software.cai.Homeitem;
import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.Myself.ChangePassword;
import cn.edu.hebtu.software.cai.Myself.LoginActivity;
import cn.edu.hebtu.software.cai.Myself.MyPrivateDish;
import cn.edu.hebtu.software.cai.Myself.MyselfSetting;
import cn.edu.hebtu.software.cai.R;


public class Fragment3 extends Fragment{
    Button button;
    SharedPreferences sharedPreferences;
    SmartRefreshLayout srl;
    HomeAdapter adapter;
    GridView gridView;
    List<Homeitem> homeitems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_addmenu,
                container,
                false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srl = getActivity().findViewById(R.id.srl);
        //给智能刷新控件注册下拉刷新监听器
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        srl.finishRefresh();
                    }
                }, 200);
            }
        });

        //给智能刷新控件注册上拉加载更多监听器
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData(homeitems.size());
                srl.finishLoadMore();
                if(homeitems.size() > 100){//表示加载完所有的数据
                    srl.finishLoadMoreWithNoMoreData();
                } else {
                    srl.setNoMoreData(false);
                }
            }
        });

        //gridview
        gridView=getActivity().findViewById(R.id.home_gv);
        homeitems=initData();
        adapter=new HomeAdapter(getContext(),homeitems);
        gridView.setAdapter(adapter);


    }
    //初始化数据
    private List<Homeitem> initData() {
        List<Homeitem> homeitems = new ArrayList<>();
        Homeitem h1 = new Homeitem("水煮肉片","经典川菜，让你嘴不能停",
                "home_shuizhuroupian1");
        homeitems.add(h1);
        Homeitem h2 =new Homeitem("九转大肠","经典鲁菜，让你嘴不能停"
                ,"home_jiuzhuandachang1");
        homeitems.add(h2);
        Homeitem h3 =new Homeitem("白切鸡","经典川菜，让你嘴不能停",
                "home_baiqieji2");
        homeitems.add(h3);
        Homeitem h4 =new Homeitem("文思豆腐","经典苏菜，让你嘴不能停",
                "home_wensidoufu1");
        homeitems.add(h4);
        Homeitem h5 = new Homeitem("剁椒鱼头","经典湘菜，让你嘴不能停",
                "home_duojiaoyutou1");
        homeitems.add(h5);
        Homeitem h6 =new Homeitem("黄山炖鸽","经典徽菜，让你嘴不能停"
                ,"home_huangshandunge1");
        homeitems.add(h6);
        return homeitems;
    }
    //定义刷新数据的方法
    private void refreshData(){
        homeitems.clear();
        homeitems.addAll(initData());
        adapter.notifyDataSetChanged();
    }

    //定义加载更多数据的方法
    private void loadMoreData(int n){
        Homeitem h1;
        Homeitem h2;
        switch (n){
            case 6:
                h1= new Homeitem("西湖醋鱼","经典浙菜，让你嘴不能停",
                        "home_xihucuyu1");
                homeitems.add(h1);
                h2=new Homeitem("佛跳墙","经典闽菜，让你嘴不能停"
                        ,"home_fotiaoqiang1");
                homeitems.add(h2);
                break;
            case 8:
                h1= new Homeitem("锅包肉","经典吉菜，让你嘴不能停",
                        "home_guobaorou");
                homeitems.add(h1);
                h2 =new Homeitem("毛血旺","经典川菜，让你嘴不能停"
                        ,"home_maoxuewang");
                homeitems.add(h2);
                break;
            case 10:
                h1= new Homeitem("溜肉段","经典吉菜，让你嘴不能停",
                        "home_liurouduan");
                homeitems.add(h1);
                h2 =new Homeitem("红烧排骨","经典家常菜，让你嘴不能停"
                        ,"home_paihangbang_hongshaopaigu");
                homeitems.add(h2);
                break;
            case 12:
                h1= new Homeitem("木须柿子","经典家常菜，让你嘴不能停",
                        "home_paihangbang_xihongshichaojidan");
                homeitems.add(h1);
                h2 =new Homeitem("可乐鸡翅","经典家常菜，让你嘴不能停"
                        ,"home_paihangbang_kelejichi");
                homeitems.add(h2);
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
