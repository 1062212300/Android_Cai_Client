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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.R;

public class ChangePassword extends Activity {
    ImageView imageView;
    TextView textView;
    Button button;
    SharedPreferences sharedPreferences;
    EditText password1;
    EditText password2;
    EditText password3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //返回按钮
        imageView = findViewById(R.id.myself_change_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword.this.finish();
            }
        });
        //服务条款TextView按钮以及其单击事件
        textView = findViewById(R.id.myself_login_change_server);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangePassword.this,"悄悄告诉你,这款APP不收费哦!",Toast.LENGTH_SHORT).show();
            }
        });
        password1 = findViewById(R.id.et_oldpassword);
        password2 = findViewById(R.id.et_newpassword1);
        password3 = findViewById(R.id.et_newpassword2);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        //保存
        button = findViewById(R.id.myself_change_preservation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改密码
                updatePassword();
            }
        });
    }
    //修改密码
    public void updatePassword(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务器端项目名+包名类名
                String service = "cai02/ServiceUpdatePassword";
                String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                String account=sharedPreferences.getString("account",null);
                Map<String, String> params = new HashMap<String, String>();
                String oldPassword = password1.getText().toString();
                String newPassword1 = password2.getText().toString();
                String newPassword2 = password3.getText().toString();
                if(oldPassword != null && newPassword1 != null && newPassword1.equals(newPassword2)){
                    params.put("account",account);
                    params.put("newPassword", newPassword2);
                    String result = HttpUtilsHttpURLConncetion.getContextByHttp(url, params);
                    Message msg = new Message();
                    msg.what = 0x12;
                    Bundle data = new Bundle();
                    data.putString("result", result);
                    msg.setData(data);
                    handler.sendMessage(msg);
                    Log.e("execute", "修改密码1");
                }
            }
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    Log.e("execute", "修改密码2");
                    if (msg.what == 0x12) {
                        Bundle data = msg.getData();
                        String key = data.getString("result");
                        Log.e("execute", "修改密码3");
                        try {
                            JSONObject jsonObject = new JSONObject(key);
                            String result = (String) jsonObject.get("result");
                            Log.e("execute", "修改密码4");
                            if ("success".equals(result)) {
                                Toast.makeText(ChangePassword.this,"修改成功",Toast.LENGTH_SHORT).show();
                                turnTo();
                            }else{
                                Toast.makeText(ChangePassword.this,"两次输入密码不相同，请重新输入",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }).start();
    }
    //跳转页面，修改成功时
    public void turnTo(){
        //保存用户名和密码
        Intent intent2 = new Intent();
        intent2.setClass(ChangePassword.this,MyselfSetting.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
