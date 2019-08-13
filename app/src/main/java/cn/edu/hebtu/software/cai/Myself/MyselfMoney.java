package cn.edu.hebtu.software.cai.Myself;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.edu.hebtu.software.cai.R;

public class MyselfMoney extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_money);
        ImageView imageView = findViewById(R.id.myself_money_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyselfMoney.this.finish();
            }
        });
    }
}
