package cn.edu.hebtu.software.cai;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog extends Dialog {
    public Button positiveButton, negativeButton;
    public TextView title,message;
    public String titleStr,messageStr;
    public CustomDialog(Context context,String title,String message) {
        super(context);
        this.titleStr = title;
        this.messageStr = message;
        setCustomDialog();
    }
    private void setCustomDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除dialog标题
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.activity_fankui_dialog, null);
        title = (TextView) mView.findViewById(R.id.dialog_tv);
        message = (TextView) mView.findViewById(R.id.dialog_et);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);

        title.setText(titleStr);
        message.setText(messageStr);
        super.setContentView(mView);
    }
//    确定键监听器
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
//    取消键监听器
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }

}