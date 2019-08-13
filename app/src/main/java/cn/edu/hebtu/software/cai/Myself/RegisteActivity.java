package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisteActivity extends Activity {
    public SharedPreferences sharedPreferences;
    private ImageView imageView;
    private TextView textView;
    private Button button;
    private EditText etUser;
    private EditText etAccount;
    private EditText etPassword;
    private OkHttpClient okHttpClient;
    private String username,account,password;
    private String url = "http://10.7.89.236:8080/cai/login/toRegister";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        //注册返回按钮
        imageView = findViewById(R.id.myself_registe_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisteActivity.this.finish();
            }
        });
        textView = findViewById(R.id.myself_registe_server);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisteActivity.this,"悄悄告诉你,这款APP不收费哦!",Toast.LENGTH_SHORT).show();
            }
        });
        button = findViewById(R.id.btn_registe);
        etUser = findViewById(R.id.et_user);
        etAccount = findViewById(R.id.registe_account);
        etPassword = findViewById(R.id.registe_password);
        okHttpClient = new OkHttpClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUser.getText().toString();
                account = etAccount.getText().toString();
                password = etPassword.getText().toString();
                //判断该用户是否符合注册项规范
                if(username.length() <3 ){
                    Toast.makeText(RegisteActivity.this,"用户名小于2个字符，请重新输入",Toast.LENGTH_LONG).show();
                }else if(account.length() < 6 ){
                    Toast.makeText(RegisteActivity.this,"账号小于6位，请重新输入",Toast.LENGTH_LONG).show();
                }else if(password.length() < 6){
                    Toast.makeText(RegisteActivity.this,"密码小于6位，请重新输入",Toast.LENGTH_LONG).show();
                }else{
                    UserBean user = new UserBean();
                    user.setUserName(username);
                    user.setAccount(account);
                    user.setPassword(password);
                    //注册
                    register(user);
                }
            }
        });

    }
    //注册
    public void register(final UserBean userBean){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String jsonObject = gson.toJson(userBean);
                MediaType type = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(type, jsonObject);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)//设置请求方式是POST，并指定传递的数据对象
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(RegisteActivity.this,"注册异常，请重新注册",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            handler.obtainMessage(0x12,response.body().string()).sendToTarget();
                        }
                    }
                });
            }
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == 0x12){
                        String returnMessage = (String)msg.obj;
                        Log.i("返回的信息：",returnMessage);
//                        final String result = new Gson().fromJson(returnMessage,String.class);
                        if(returnMessage == "error"){
                            Toast.makeText(RegisteActivity.this,"账号已注册，请重新输入",Toast.LENGTH_SHORT).show();
                        }else{
                            turnTo();
                        }
                    }
                }
            };
        }).start();
    }

    //跳转页面，注册成功
    public void turnTo(){
        //获取用户名
        String user = etUser.getText().toString();
        String account=etAccount.getText().toString();
//        String sex = "man";
//        String employment = "我的职业";
//        String userEmail="我的邮箱";
//        String userMessage="既然要吃，就要吃胖！Ψ(￣∀￣)Ψ";
//        String problem = "我的密保问题";
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loginstate",1);
//        editor.putString("userEmail",userEmail);
//        editor.putString("userMessage",userMessage);
        editor.putString("userName",user);
        editor.putString("account",account);
//        editor.putString("userEmployment",employment);
//        editor.putString("userSex",sex);
//        editor.putString("problem",problem);
        editor.commit();
        Intent intent2 = new Intent();
        intent2.setClass(RegisteActivity.this,MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
