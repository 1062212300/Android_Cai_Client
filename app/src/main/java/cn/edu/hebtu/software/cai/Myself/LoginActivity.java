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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity{
    TextView textView,textView1,textView2;
    ImageView imageView;
    Button button;
    SharedPreferences sharedPreferences;
    private EditText etUserAccount;
    private EditText etPassword;
    private OkHttpClient okHttpClient;
    private String account,password;
    private String url = "http://10.7.89.236:8080/cai/login/toFormBody";
//    private String url = "http://10.7.89.43:8080/cai02/signIn";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //绑定注册TextView
        textView = findViewById(R.id.login_registe);
        //登录返回按钮
        imageView = findViewById(R.id.myself_login_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
        //单击TextView事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,RegisteActivity .class);
                startActivity(intent);
            }
        });
        textView1 = findViewById(R.id.myself_login_forgetpassword);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,ForgetPassword .class);
                startActivity(intent);
            }
        });
        textView2 = findViewById(R.id.myself_login_server);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"悄悄告诉你,这款APP不收费哦!",Toast.LENGTH_SHORT).show();
            }
        });
        etUserAccount = findViewById(R.id.login_account);
        etPassword = findViewById(R.id.login_password);
        okHttpClient = new OkHttpClient();
        //绑定登录按钮
        button = findViewById(R.id.login_login);
        //单击登录按钮事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断账号、密码是否正确
                account = etUserAccount.getText().toString();
                password = etPassword.getText().toString();
                //调用登录方法
                login(account,password);
            }
        });
    }
    //登录验证
    public void login(final String account, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务器端项目名+包名类名
                FormBody formBody = new FormBody.Builder()
                        .add("account", account)
                        .add("password", password)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
                //3. 创建Call对象
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(LoginActivity.this,"登录异常，请重新登录",Toast.LENGTH_SHORT).show();
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
                        if(returnMessage != "error"){
                            turnTo(returnMessage);
                        }else{
                            Toast.makeText(LoginActivity.this,"登录错误，请重新输入",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            };
        }).start();
    }
    //跳转页面，登陆成功时
    public void turnTo(String userName ){
        //保存用户名和密码
        String account = etUserAccount.getText().toString();
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loginstate",1);
        editor.putString("userName",userName);
//        editor.putString("userMessage",userMessage);
//        editor.putString("userEmail",userEmail);
        editor.putString("account",account);
//        editor.putString("userEmployment",employment);
//        editor.putString("userSex",sex);
//        editor.putString("problem",problem);
        editor.commit();
        Intent intent2 = new Intent();
        intent2.setClass(LoginActivity.this,MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}