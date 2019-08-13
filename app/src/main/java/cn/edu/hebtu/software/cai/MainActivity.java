package cn.edu.hebtu.software.cai;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.edu.hebtu.software.cai.Fragment.Fragment1;
import cn.edu.hebtu.software.cai.Fragment.Fragment2;
import cn.edu.hebtu.software.cai.Fragment.Fragment3;
import cn.edu.hebtu.software.cai.Fragment.Fragment4;
import cn.edu.hebtu.software.cai.Fragment.Fragment5;

public class MainActivity extends AppCompatActivity {
    private Map<String,View> tabspecViews=new HashMap<>();
    private Map<String,ImageView> imageViewMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化FragmentTabHost对象
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),
                android.R.id.tabhost);
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tab1")
                .setIndicator("首页")
                .setIndicator(getTabSpecView("首页", R.drawable.tabhost_home2, "tab1"));
        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tab2")
                .setIndicator("分类")
                .setIndicator(getTabSpecView("分类", R.drawable.tabhost_list, "tab2"));
        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tab3")
                .setIndicator("")
                .setIndicator(getTabSpecView("", R.drawable.tabhost_add, "tab3"));
        TabHost.TabSpec tabSpec4 = fragmentTabHost.newTabSpec("tab4")
                .setIndicator("社区")
                .setIndicator(getTabSpecView("社区", R.drawable.tabhost_community, "tab4"));
        TabHost.TabSpec tabSpec5 = fragmentTabHost.newTabSpec("tab5")
                .setIndicator("我的")
                .setIndicator(getTabSpecView("我的", R.drawable.tabhost_main, "tab5"));
        //绑定选项卡和页面
        fragmentTabHost.addTab(tabSpec1, Fragment1.class, null);
        fragmentTabHost.addTab(tabSpec2, Fragment2.class, null);
        fragmentTabHost.addTab(tabSpec3, Fragment3.class, null);
        fragmentTabHost.addTab(tabSpec4, Fragment4.class, null);
        fragmentTabHost.addTab(tabSpec5, Fragment5.class, null);

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                    ImageView imageView1 = imageViewMap.get("tab1");
                    ImageView imageView2 = imageViewMap.get("tab2");
                    ImageView imageView4 = imageViewMap.get("tab4");
                    ImageView imageView5 = imageViewMap.get("tab5");
                    if (tabId.equals("tab1")){
                        imageView1.setImageResource(R.drawable.tabhost_home2);
                        imageView2.setImageResource(R.drawable.tabhost_list);
                        imageView4.setImageResource(R.drawable.tabhost_community);
                        imageView5.setImageResource(R.drawable.tabhost_main);
                    }else if(tabId.equals("tab2")){
                        imageView1.setImageResource(R.drawable.tabhost_home);
                        imageView2.setImageResource(R.drawable.tabhost_list2);
                        imageView4.setImageResource(R.drawable.tabhost_community);
                        imageView5.setImageResource(R.drawable.tabhost_main);
                    }else if(tabId.equals("tab3")){
                        imageView1.setImageResource(R.drawable.tabhost_home);
                        imageView2.setImageResource(R.drawable.tabhost_list);
                        imageView4.setImageResource(R.drawable.tabhost_community);
                        imageView5.setImageResource(R.drawable.tabhost_main);
                    }
                    else if(tabId.equals("tab4")){
                        imageView1.setImageResource(R.drawable.tabhost_home);
                        imageView2.setImageResource(R.drawable.tabhost_list);
                        imageView4.setImageResource(R.drawable.tabhost_community2);
                        imageView5.setImageResource(R.drawable.tabhost_main);
                    }else if(tabId.equals("tab5")){
                        imageView1.setImageResource(R.drawable.tabhost_home);
                        imageView2.setImageResource(R.drawable.tabhost_list);
                        imageView4.setImageResource(R.drawable.tabhost_community);
                        imageView5.setImageResource(R.drawable.tabhost_main2);
                    }
                }
        });
        fragmentTabHost.setCurrentTab(0);
    }

    private View getTabSpecView(String name, int imageId, String tag){
        LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.tabspc_layout,null);
            TextView textView = view.findViewById(R.id.textView);
            textView.setText(name);
            ImageView imageView = view.findViewById(R.id.image);
            imageView.setImageResource(imageId);
            tabspecViews.put(tag, view);
            imageViewMap.put(tag, imageView);
            return view;
    }
}