package cn.edu.hebtu.software.cai;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> dataSource;
    private int item_layout_id;

    public CustomAdapter(Context context, List<Map<String,Object>> dataSource, int item_layout_id){
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
    }
    public  int getCount(){return dataSource.size();}
    public Object getItem(int position) { return dataSource.get(position); }
    public long getItemId(int position) { return position; }
    public View getView(final int position, View convertView, ViewGroup parent){
        if(null == convertView){
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(item_layout_id, null);
        }
        if(item_layout_id==R.layout.classification_gridview_item){
        ImageView img_header = convertView.findViewById(R.id.gv_image);
        TextView txt_nameg = convertView.findViewById(R.id.gv_name);
        final Map<String, Object> mItemDatag = dataSource.get(position);
        img_header.setImageResource((int)mItemDatag.get("image"));
        txt_nameg.setText(mItemDatag.get("name").toString());}
        else {
        TextView txt_name = convertView.findViewById(R.id.lv_name);
        final Map<String, Object> mItemData = dataSource.get(position);
        txt_name.setText(mItemData.get("name").toString());}
        return convertView;
    }


}
