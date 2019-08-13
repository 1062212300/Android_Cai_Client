package cn.edu.hebtu.software.cai.Fragment;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hebtu.software.cai.CustomAdapter;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.R;
import cn.edu.hebtu.software.cai.WebActivity;


public class Fragment2 extends Fragment {
    EditText etSearch;
    TextView btSearch;
    String Gname;
    String Sname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_classification,
                container,
                false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btSearch = getActivity().findViewById(R.id.class_btn_search);
        etSearch = getActivity().findViewById(R.id.class_et_search);
        getActivity().findViewById(R.id.class_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sname = etSearch.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //服务器端项目名+包名类名
                        String service = "cai02/ServiceSearch";
                        String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("search", Sname);
                        String result = HttpUtilsHttpURLConncetion.getContextByHttp(url, params);
                        Message msg = new Message();
                        msg.what = 0x12;
                        Bundle data = new Bundle();
                        data.putString("result", result);
                        msg.setData(data);
                        handler.sendMessage(msg);
                        Log.e("execute", "搜索1");
                    }
                    Handler handler = new Handler() {
                        public void handleMessage(Message msg) {
                            Log.e("execute", "搜索2");
                        }
                    };
                }).start();
                Intent intent2 = new Intent();
                intent2.putExtra("name", Sname);
                intent2.setClass(getActivity(), WebActivity.class);
                startActivity(intent2);
            }
        });
        //创建listView
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() {
        };
        ListView listView = getActivity().findViewById(R.id.lv_list);
        final Map<String, Object> item1 = new HashMap<>();
        item1.put("name", "菜系");
        list.add(item1);
        Map<String, Object> item2 = new HashMap<>();
        item2.put("name", "食材");
        list.add(item2);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("name", "味道");
        list.add(item3);
        Map<String, Object> item4 = new HashMap<>();
        item4.put("name", "烹饪方法");
        list.add(item4);
        Map<String, Object> item5 = new HashMap<>();
        item5.put("name", "适宜人群");
        list.add(item5);
        CustomAdapter customAdapter = new CustomAdapter(getContext(), list, R.layout.classification_list_item);
        listView.setAdapter(customAdapter);
        //创建GridView
        final GridView gridView = getActivity().findViewById(R.id.gv_list);
        //gridview点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    TextView textView = view.findViewById(R.id.gv_name);
                    Gname = (String) textView.getText().toString();
                    Log.e("name", Gname);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //服务器端项目名+包名类名
                            String service = "cai02/ServiceSearch";
                            String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("search", Gname);
                            String result = HttpUtilsHttpURLConncetion.getContextByHttp(url, params);
                            Message msg = new Message();
                            msg.what = 0x12;
                            Bundle data = new Bundle();
                            data.putString("result", result);
                            msg.setData(data);
                            handler.sendMessage(msg);
                            Log.e("execute", "搜索1");
                        }
                        Handler handler = new Handler() {
                            public void handleMessage(Message msg) {
                                Log.e("execute", "搜索2");
                            }
                        };
                    }).start();
                    Intent intent = new Intent();
                    intent.putExtra("name", Gname);
                    intent.setClass(getActivity(), WebActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //菜系
        final List<Map<String, Object>> gvlistcaixi = new ArrayList<>();
        Map<String, Object> gvitem11 = new HashMap<>();
        gvitem11.put("name", "川菜");
        gvitem11.put("image", R.drawable.classification_caixi_chuancai);
        gvlistcaixi.add(gvitem11);
        Map<String, Object> gvitem12 = new HashMap<>();
        gvitem12.put("name", "鲁菜");
        gvitem12.put("image", R.drawable.classification_caixi_lucai);
        gvlistcaixi.add(gvitem12);
        Map<String, Object> gvitem13 = new HashMap<>();
        gvitem13.put("name", "粤菜");
        gvitem13.put("image", R.drawable.classification_caixi_yuecai);
        gvlistcaixi.add(gvitem13);
        Map<String, Object> gvitem14 = new HashMap<>();
        gvitem14.put("name", "苏菜");
        gvitem14.put("image", R.drawable.classification_caixi_sucai);
        gvlistcaixi.add(gvitem14);
        Map<String, Object> gvitem15 = new HashMap<>();
        gvitem15.put("name", "浙菜");
        gvitem15.put("image", R.drawable.classification_caixi_zhecai);
        gvlistcaixi.add(gvitem15);
        Map<String, Object> gvitem16 = new HashMap<>();
        gvitem16.put("name", "闽菜");
        gvitem16.put("image", R.drawable.classification_caixi_mincai);
        gvlistcaixi.add(gvitem16);
        Map<String, Object> gvitem17 = new HashMap<>();
        gvitem17.put("name", "湘菜");
        gvitem17.put("image", R.drawable.classification_caixi_xiangcai);
        gvlistcaixi.add(gvitem17);
        Map<String, Object> gvitem18 = new HashMap<>();
        gvitem18.put("name", "徽菜");
        gvitem18.put("image", R.drawable.classification_caixi_huicai);
        gvlistcaixi.add(gvitem18);
        //食材
        final List<Map<String, Object>> gvlistshicai = new ArrayList<>();
        Map<String, Object> gvitem21 = new HashMap<>();
        gvitem21.put("name", "热门");
        gvitem21.put("image", R.drawable.classification_shicai_remen);
        gvlistshicai.add(gvitem21);
        Map<String, Object> gvitem22 = new HashMap<>();
        gvitem22.put("name", "肉禽");
        gvitem22.put("image", R.drawable.classification_shicai_rouqin);
        gvlistshicai.add(gvitem22);
        Map<String, Object> gvitem23 = new HashMap<>();
        gvitem23.put("name", "水产品");
        gvitem23.put("image", R.drawable.classification_shicai_shuichanping);
        gvlistshicai.add(gvitem23);
        Map<String, Object> gvitem24 = new HashMap<>();
        gvitem24.put("name", "蔬菜");
        gvitem24.put("image", R.drawable.classification_shicai_shucai);
        gvlistshicai.add(gvitem24);
        Map<String, Object> gvitem25 = new HashMap<>();
        gvitem25.put("name", "果品");
        gvitem25.put("image", R.drawable.classification_shicai_guopin);
        gvlistshicai.add(gvitem25);
        Map<String, Object> gvitem26 = new HashMap<>();
        gvitem26.put("name", "药食");
        gvitem26.put("image", R.drawable.classification_shicai_yaoshi);
        gvlistshicai.add(gvitem26);
        Map<String, Object> gvitem27 = new HashMap<>();
        gvitem27.put("name", "调味品");
        gvitem27.put("image", R.drawable.classification_shicai_tiaoweipin);
        gvlistshicai.add(gvitem27);
        Map<String, Object> gvitem28 = new HashMap<>();
        gvitem28.put("name", "米面豆乳");
        gvitem28.put("image", R.drawable.classification_shicai_mimiandouru);
        gvlistshicai.add(gvitem28);
        //味道
        final List<Map<String, Object>> gvlistweidao = new ArrayList<>();
        Map<String, Object> gvitem31 = new HashMap<>();
        gvitem31.put("name", "酸");
        gvitem31.put("image", R.drawable.classification_weidao_suan);
        gvlistweidao.add(gvitem31);
        Map<String, Object> gvitem32 = new HashMap<>();
        gvitem32.put("name", "甜");
        gvitem32.put("image", R.drawable.classification_weidao_tian);
        gvlistweidao.add(gvitem32);
        Map<String, Object> gvitem33 = new HashMap<>();
        gvitem33.put("name", "苦");
        gvitem33.put("image", R.drawable.classification_weidao_ku);
        gvlistweidao.add(gvitem33);
        Map<String, Object> gvitem34 = new HashMap<>();
        gvitem34.put("name", "辣");
        gvitem34.put("image", R.drawable.classification_weidao_la);
        gvlistweidao.add(gvitem34);
        Map<String, Object> gvitem35 = new HashMap<>();
        gvitem35.put("name", "咸");
        gvitem35.put("image", R.drawable.classification_weidao_xian);
        gvlistweidao.add(gvitem35);
        //烹饪方法
        final List<Map<String, Object>> gvlistpengren = new ArrayList<>();
        Map<String, Object> gvitem41 = new HashMap<>();
        gvitem41.put("name", "炒");
        gvitem41.put("image", R.drawable.classification_pengren_chao);
        gvlistpengren.add(gvitem41);
        Map<String, Object> gvitem42 = new HashMap<>();
        gvitem42.put("name", "炸");
        gvitem42.put("image", R.drawable.classification_pengren_zha);
        gvlistpengren.add(gvitem42);
        Map<String, Object> gvitem43 = new HashMap<>();
        gvitem43.put("name", "煎");
        gvitem43.put("image", R.drawable.classification_pengren_jian);
        gvlistpengren.add(gvitem43);
        Map<String, Object> gvitem44 = new HashMap<>();
        gvitem44.put("name", "烤");
        gvitem44.put("image", R.drawable.classification_pengren_kao);
        gvlistpengren.add(gvitem44);
        Map<String, Object> gvitem45 = new HashMap<>();
        gvitem45.put("name", "蒸");
        gvitem45.put("image", R.drawable.classification_pengren_zheng);
        gvlistpengren.add(gvitem45);
        Map<String, Object> gvitem46 = new HashMap<>();
        gvitem46.put("name", "炖");
        gvitem46.put("image", R.drawable.classification_pengren_dun);
        gvlistpengren.add(gvitem46);
        Map<String, Object> gvitem47 = new HashMap<>();
        gvitem47.put("name", "煮");
        gvitem47.put("image", R.drawable.classification_pengren_zhu);
        gvlistpengren.add(gvitem47);
        Map<String, Object> gvitem48 = new HashMap<>();
        gvitem48.put("name", "涮");
        gvitem48.put("image", R.drawable.classification_pengren_shuan);
        gvlistpengren.add(gvitem48);
        //适宜人群
        final List<Map<String, Object>> gvlistrenqun = new ArrayList<>();
        Map<String, Object> gvitem51 = new HashMap<>();
        gvitem51.put("name", "男性");
        gvitem51.put("image", R.drawable.classification_renqun_nan);
        gvlistrenqun.add(gvitem51);
        Map<String, Object> gvitem52 = new HashMap<>();
        gvitem52.put("name", "女性");
        gvitem52.put("image", R.drawable.classification_renqun_nv);
        gvlistrenqun.add(gvitem52);
        Map<String, Object> gvitem53 = new HashMap<>();
        gvitem53.put("name", "青少年");
        gvitem53.put("image", R.drawable.classification_renqun_qingshaonian);
        gvlistrenqun.add(gvitem53);
        Map<String, Object> gvitem54 = new HashMap<>();
        gvitem54.put("name", "养生保健");
        gvitem54.put("image", R.drawable.classification_renqun_yangsheng);
        gvlistrenqun.add(gvitem54);
        Map<String, Object> gvitem55 = new HashMap<>();
        gvitem55.put("name", "美容减肥");
        gvitem55.put("image", R.drawable.classification_renqun_jianfei);
        gvlistrenqun.add(gvitem55);
        Map<String, Object> gvitem56 = new HashMap<>();
        gvitem56.put("name", "孕前哺乳");
        gvitem56.put("image", R.drawable.classification_renqun_yunqian);
        gvlistrenqun.add(gvitem56);
        CustomAdapter customAdaptercaixi = new CustomAdapter(getContext(), gvlistcaixi, R.layout.classification_gridview_item);
        gridView.setAdapter(customAdaptercaixi);

        //list点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //点击菜系
                    case 0:
                        CustomAdapter customAdaptercaixi = new CustomAdapter(getContext(), gvlistcaixi, R.layout.classification_gridview_item);
                        gridView.setAdapter(customAdaptercaixi);
                        break;
                    //点击食材
                    case 1:
                        CustomAdapter customAdaptershicai = new CustomAdapter(getContext(), gvlistshicai, R.layout.classification_gridview_item);
                        gridView.setAdapter(customAdaptershicai);
                        break;
                    //点击味道
                    case 2:
                        CustomAdapter customAdapterweidai = new CustomAdapter(getContext(), gvlistweidao, R.layout.classification_gridview_item);
                        gridView.setAdapter(customAdapterweidai);
                        break;
                    //点击烹饪方法
                    case 3:
                        CustomAdapter customAdapterpengren = new CustomAdapter(getContext(), gvlistpengren, R.layout.classification_gridview_item);
                        gridView.setAdapter(customAdapterpengren);
                        break;
                    //点击适宜人群
                    case 4:
                        CustomAdapter customAdapterrenqun = new CustomAdapter(getContext(), gvlistrenqun, R.layout.classification_gridview_item);
                        gridView.setAdapter(customAdapterrenqun);
                        break;
                }
            }
        });
    }
}