package cn.edu.hebtu.software.cai.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import cn.edu.hebtu.software.cai.R;

public class HomePaihangbang extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activyty_home_paihangbang);
        ImageView imageView = findViewById(R.id.myself_home_paihangbang_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePaihangbang.this.finish();
            }
        });
    }
}