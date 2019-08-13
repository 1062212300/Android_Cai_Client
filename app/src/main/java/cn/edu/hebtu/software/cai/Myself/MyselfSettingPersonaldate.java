package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hebtu.software.cai.HttpUtilsHttpURLConncetion;
import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.R;

public class MyselfSettingPersonaldate extends Activity {
    ImageView imageView;
    EditText etName;
    EditText etEmail;
    EditText etMessages;
    EditText etEmployment;
    EditText etProblem;
    Button buSubmit;
    private RadioButton man,woman;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_setting_personaldata);
        imageView = findViewById(R.id.myself_setting_personaldata_return);
        etName = findViewById(R.id.myself_setting_personaldata_username);
        etEmail =findViewById(R.id.myself_setting_personaldata_email);
        etMessages = findViewById(R.id.myself_setting_personaldata_hobby);
        etEmployment = findViewById(R.id.myself_setting_personal_employment);
        etProblem = findViewById(R.id.myself_setting_personalproblem_hobby);
        buSubmit = findViewById(R.id.myself_setting_personaldata_keep);
        man = findViewById(R.id.man);
        woman =findViewById(R.id.female);
        etProblem.setTransformationMethod(PasswordTransformationMethod.getInstance());//设置密保问题隐藏

        //用户名
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName","悬疑");
        String sex = sharedPreferences.getString("userSex",null);
        String userEmployment = sharedPreferences.getString("userEmployment","我的职业");
        String userEmail = sharedPreferences.getString("userEmail","我的邮箱");
        String userMessage = sharedPreferences.getString("userMessage","既然要吃，就要吃胖！");
        String problem = sharedPreferences.getString("problem","我的密保答案");
        etName.setHint(userName);
        etEmployment.setHint(userEmployment);
        etEmail.setHint(userEmail);
        etMessages.setHint(userMessage);
        etProblem.setHint(problem);
        setSex(sex);
        //返回按钮
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyselfSettingPersonaldate.this.finish();
            }
        });
        //提交保存
        buSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }
    //保存修改的个人资料
    public void submit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务器端项目名+包名类名
                String service = "cai02/ServiceUpdate";
                String url = HttpUtilsHttpURLConncetion.BASE_URL + service;
                Map<String, String> params = new HashMap<String, String>();
                String userName = ((etName.getText().toString().equals(""))?(etName.getHint().toString()):(etName.getText().toString()));
                String account=sharedPreferences.getString("account",null);
                String employment =((etEmployment.getText().toString().equals(""))?(etEmployment.getHint().toString()):(etEmployment.getText().toString()));
                String email =((etEmail.getText().toString().equals(""))?(etEmail.getHint().toString()):(etEmail.getText().toString()));
                String messages = ((etMessages.getText().toString().equals(""))?(etMessages.getHint().toString()):(etMessages.getText().toString()));
                String problem = ((etProblem.getText().toString().equals(""))?(etProblem.getHint().toString()):(etProblem.getText().toString()));
                String sex = getSex();
                params.put("account",account);
                params.put("userName",userName);
                params.put("employment",employment);
                params.put("email",email);
                params.put("messages",messages);
                params.put("sex",sex);
                params.put("problem",problem);
                String result = HttpUtilsHttpURLConncetion.getContextByHttp(url,params);
                Message msg = new Message();
                msg.what = 0x12;
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                handler.sendMessage(msg);
                Log.e("execute", "更新1");
            }
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.e("execute", "更新2");
                    if(msg.what == 0x12){
                        Bundle data = msg.getData();
                        String key = data.getString("result");
                        Log.e("execute", "更新3");
                        try {
                            JSONObject jsonObject = new JSONObject(key);
                            String result = (String) jsonObject.get("result");
                            Log.e("execute", "更新4");
                            if ("success".equals(result)) {
                                Toast.makeText(MyselfSettingPersonaldate.this,"个人资料修改成功",Toast.LENGTH_SHORT).show();
                                turnTo();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }).start();
    }
    //跳转页面，更新成功
    public void turnTo(){
        //获取用户名....
        String username =((etName.getText().toString().equals(""))?(etName.getHint().toString()):(etName.getText().toString()));
        String userEmployment =((etEmployment.getText().toString().equals(""))?(etEmployment.getHint().toString()):(etEmployment.getText().toString()));
        String userEmail = ((etEmail.getText().toString().equals(""))?(etEmail.getHint().toString()):(etEmail.getText().toString()));
        String userMessage = ((etMessages.getText().toString().equals(""))?(etMessages.getHint().toString()):(etMessages.getText().toString()));
        String problem = ((etProblem.getText().toString().equals(""))?(etProblem.getHint().toString()):(etProblem.getText().toString()));
        String sex = getSex();
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loginstate",1);
        editor.putString("userName",username);
        editor.putString("userEmployment",userEmployment);
        editor.putString("userMessage",userMessage);
        editor.putString("userEmail",userEmail);
        editor.putString("userSex",sex);
        editor.putString("problem",problem);
        editor.commit();
        Intent intent2 = new Intent();
        intent2.setClass(MyselfSettingPersonaldate.this,MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
    //获取性别
    public String getSex(){
        String sex = null;
        if(man.isChecked()){
            sex = "man";
        }else if(woman.isChecked()){
            sex = "woman";
        }else{
            sex = null;
        }
        return sex;
    }
    //查看性别信息
    public void setSex(String sex){
        if(sex.equals("man")){
            man.setChecked(true);
        }else if(sex.equals("woman")){
            woman.setChecked(true);
        }else{
            man.setChecked(false);
            woman.setChecked(false);
        }
    }

}
