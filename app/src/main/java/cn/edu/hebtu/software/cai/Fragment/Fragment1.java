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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.cai.Home.HomePaihangbang;
import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.R;
import cn.edu.hebtu.software.cai.WebActivity;


public class Fragment1 extends Fragment{
    EditText etSearch;
    TextView btSearch;
    String Sname;
    private LinearLayout horzi;
    private LayoutInflater mInflater;
    String[] names1,names2;
    String[] texts1,texts2;
    int[] images1,images2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,
                container,
                false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInflater=LayoutInflater.from(this.getContext());
        initData();
        initView(names1,texts1,images1,R.id.home_horzi1);
        initView(names2,texts2,images2,R.id.home_horzi2);




        LinearLayout linearLayout = getActivity().findViewById(R.id.home_paihangbang);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), HomePaihangbang.class);
                startActivity(intent);
            }
        });
        //跳转
        btSearch = getActivity().findViewById(R.id.home_btn_search);
        etSearch = getActivity().findViewById(R.id.home_et_search);
        getActivity().findViewById(R.id.home_btn_search).setOnClickListener(new View.OnClickListener() {
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



    }
    //初始化数据
    public void initData(){
         names1=new String[]{"新奇创意","家常菜","汤羹","小吃甜点","芝士的力量","日式料理"};
         texts1=new String[]{"创意不停","发现家常菜","滋补身体","好吃停不下来","补充能量","自然原味"};
         images1=new int[]{R.drawable.home_chuangxin,R.drawable.home_jiachangcai,R.drawable.home_tanggeng,
                                R.drawable.home_xiaochi,R.drawable.home_zhishi,R.drawable.home_riliao};

        names2=new String[]{"消热解暑","多味沙冰","缤纷水果","夏日特饮","特色冰粉","美味冰淇淋"};
        texts2=new String[]{"绿豆汤","今明后","冰镇西瓜","蜜雪冰城","手工冰粉","哈根达斯"};
        images2=new int[]{R.drawable.home_lvdoutang,R.drawable.home_shabing,R.drawable.home_xigua,
                R.drawable.home_teyin,R.drawable.home_bingfen,R.drawable.home_bingpilin};
    }


    //实例化横向移动控件
    public void initView(String[] names,String[] texts,int[] images,int id){
        horzi=getActivity().findViewById(id);
        for (int i=0;i<names.length;i++){
            View view=mInflater.inflate(R.layout.home_horizontalscrollview_item,horzi,false);
            ImageView img=view.findViewById(R.id.hh_image);
            TextView name=view.findViewById(R.id.hh_name);
            TextView text=view.findViewById(R.id.hh_text);
            img.setImageResource(images[i]);
            name.setText(names[i]);
            text.setText(texts[i]);
            horzi.addView(view);
        }


    }
}

