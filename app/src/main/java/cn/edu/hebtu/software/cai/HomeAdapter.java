package cn.edu.hebtu.software.cai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<Homeitem> homeitems;

    public HomeAdapter(Context context, List<Homeitem> homeitems) {
        this.context = context;
        this.homeitems = homeitems;
    }

    @Override
    public int getCount() {
        return homeitems.size();
    }

    @Override
    public Object getItem(int position) {
        return homeitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        //1. 加载布局
        if(null == convertView){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.home_gridview_item, null);
            vh = new ViewHolder();
            vh.hg_name = convertView.findViewById(R.id.hg_name);
            vh.hg_text = convertView.findViewById(R.id.hg_text);
            vh.hg_img1=convertView.findViewById(R.id.hg_img1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //2. 填充数据
        Homeitem homeitem = homeitems.get(position);
        vh.hg_name.setText(homeitem.getHname());
        vh.hg_text.setText(homeitem.getHtext());
        int img1 = context.getResources().
                getIdentifier(homeitem.getImg1() , "drawable","cn.edu.hebtu.software.cai");

        vh.hg_img1.setImageResource(img1);
        return convertView;
    }

    //定义内部类ViewHolder，用于缓存item控件
    class ViewHolder{
        private TextView hg_name;
        private TextView hg_text;
        private ImageView hg_img1;
    }
}
