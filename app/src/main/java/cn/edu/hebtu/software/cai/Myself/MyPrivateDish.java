package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.R;

public class MyPrivateDish extends Activity {
    ImageView imageView;
    EditText etDishName;
    EditText etRaw;
    EditText etMethod;
    Button btPhoto,btSubmit;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_private_dish);
        etDishName = findViewById(R.id.myself_private_dish_name);
        etRaw = findViewById(R.id.et_raw);
        etMethod = findViewById(R.id.et_method);
        btPhoto = findViewById(R.id.bt_photo);
        btSubmit = findViewById(R.id.bt_submit);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        //返回按钮
        imageView = findViewById(R.id.myself_private_dish_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPrivateDish.this.finish();
            }
        });
        //上传图片
        btPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传图片
            }
        });
        //提交保存
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = etDishName.getText().toString();
                String raw = etRaw.getText().toString();
                String method = etMethod.getText().toString();
                if(dishName != null && raw != null && method !=null){
                    addMenu();
                }else{
                    Toast.makeText(MyPrivateDish.this,"菜名/材料/方法都不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务器端项目名+包名类名
                String service = "cai04/ServicePrivateDish";
                String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                Map<String, String> params = new HashMap<String, String>();
                String account=sharedPreferences.getString("account",null);
                String dishName = etDishName.getText().toString();
                String raw = etRaw.getText().toString();
                String method = etMethod.getText().toString();
                params.put("account", account);
                params.put("dishName",dishName);
                params.put("raw",raw);
                params.put("method",method);
                String result = HttpUtilsHttpURLConncetion.getContextByHttp(url, params);
                Message msg = new Message();
                msg.what = 0x12;
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                handler.sendMessage(msg);
                Log.e("execute", "上传1");
            }
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    Log.e("execute", "上传2");
                    if (msg.what == 0x12) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");
                        Log.e("execute", "上传3");
                        try {
                            JSONObject jsonObject = new JSONObject(key);
                            String result = (String) jsonObject.get("result");
                            Log.e("execute", "上传4");
                            if ("success".equals(result)) {
                                Toast.makeText(MyPrivateDish.this,"上传成功",Toast.LENGTH_SHORT).show();
                                turnTo();
                            }else{
                                Toast.makeText(MyPrivateDish.this,"上传失败，请重新上传",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }).start();
    }
    //跳转页面，提交成功后
    public void turnTo(){
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loginstate",1);
        editor.commit();
//        Intent intent2 = new Intent();
//        intent2.setClass(MyPrivateDish.this,MainActivity.class);
//        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent2);
    }
}
