package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.content.Intent;
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

public class ForgetPassword extends Activity {
    ImageView imageView;
    TextView textView;
    Button button;
    EditText etAccount;
    EditText etProblem;
    EditText etNewpassword1;
    EditText etNewpassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //返回按钮
        imageView = findViewById(R.id.myself_forgetpassword_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword.this.finish();
            }
        });
        //服务条款TextView按钮以及其单击事件
        textView = findViewById(R.id.myself_login_forgetpassword_server);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForgetPassword.this,"悄悄告诉你,这款APP不收费哦!",Toast.LENGTH_SHORT).show();
            }
        });
        etAccount = findViewById(R.id.et_account);
        etProblem = findViewById(R.id.et_problem);
        etNewpassword1 =findViewById(R.id.et_fNewpassword1);
        etNewpassword2 = findViewById(R.id.et_fNewpassword2);
        //保存
        button = findViewById(R.id.myself_forgetpassword_preservation);
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
                String service = "cai02/ServiceForgetPasssword";
                String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                Map<String, String> params = new HashMap<String, String>();
                String account = etAccount.getText().toString();
                String problem = etProblem.getText().toString();
                String newPassword1 = etNewpassword1.getText().toString();
                String newPassword2 = etNewpassword2.getText().toString();
                if(newPassword1 != null && newPassword1.equals(newPassword2)){
                    params.put("account",account);
                    params.put("problem",problem);
                    params.put("newPassword", newPassword2);
                    String result = HttpUtilsHttpURLConncetion.getContextByHttp(url, params);
                    Message msg = new Message();
                    msg.what = 0x12;
                    Bundle data = new Bundle();
                    data.putString("result", result);
                    msg.setData(data);
                    handler.sendMessage(msg);
                    Log.e("execute", "修改密码1");
                }else{
                    Toast.makeText(ForgetPassword.this,"两次输入密码不相同，请重新输入",Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(ForgetPassword.this,"修改成功",Toast.LENGTH_SHORT).show();
                                turnTo();
                            }else{
                                Toast.makeText(ForgetPassword.this,"账号或者密保答案错误，请重新输入或注册",Toast.LENGTH_SHORT).show();
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
        intent2.setClass(ForgetPassword.this,LoginActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
