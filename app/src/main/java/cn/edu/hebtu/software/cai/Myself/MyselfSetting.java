package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.edu.hebtu.software.cai.MainActivity;
import cn.edu.hebtu.software.cai.R;


public class MyselfSetting extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_setting);
        ImageView imageView = findViewById(R.id.myself_setting_return);
        LinearLayout linearLayout = findViewById(R.id.myself_setting_personaldata);
        LinearLayout linearLayout1 = findViewById(R.id.myself_setting_we);
        LinearLayout linearLayout2 = findViewById(R.id.myself_setting_help);
        LinearLayout linearLayout3 = findViewById(R.id.myself_setting_switchaccount);
        LinearLayout linearLayout4 = findViewById(R.id.myself_setting_break);
        LinearLayout linearLayout5 = findViewById(R.id.myself_setting_passWord);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyselfSetting.this.finish();
            }
        });
        //更改个人资料
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                int loginstate=sharedPreferences.getInt("loginstate",0);
                if(loginstate==1){
                    Intent intent = new Intent();
                    intent.setClass(MyselfSetting.this,MyselfSettingPersonaldate.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(MyselfSetting.this, "请先登录" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        //关于我们
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MyselfSetting.this,MyselfSettingWe .class);
                startActivity(intent);
            }
        });
        //帮助与反馈
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyselfSetting.this, "帮助与反馈功能搬家了呢" , Toast.LENGTH_SHORT).show();
            }
        });
        //切换账户
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent=new Intent();
                intent.setClass(MyselfSetting.this,LoginActivity .class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //退出登录
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                int loginstate=sharedPreferences.getInt("loginstate",0);
                if(loginstate==1){
                    AlertDialog.Builder adBuilder = new AlertDialog.Builder(MyselfSetting.this);
                    adBuilder.setTitle("温馨提示");
                    adBuilder.setMessage("您确定要退出当前登录吗?");
                    adBuilder.setPositiveButton("确定退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                            sharedPreferences.edit().clear().commit();
                            Intent intent=new Intent();
                            intent.setClass(MyselfSetting.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    adBuilder.setNegativeButton("取消",null);
                    AlertDialog ad = adBuilder.create();
                    ad.show();
                } else{
                    Toast.makeText(MyselfSetting.this, "请先登录" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        //修改密码
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                int loginstate=sharedPreferences.getInt("loginstate",0);
                if(loginstate==1){
                    Intent intent = new Intent();
                    intent.setClass(MyselfSetting.this,ChangePassword.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(MyselfSetting.this, "请先登录" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}