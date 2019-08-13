package cn.edu.hebtu.software.cai.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import cn.edu.hebtu.software.cai.R;


public class Fragment4 extends Fragment{
    private ImageView Img_forum_dianzan1;
    private ImageView Img_forum_dianzan2;
    private ImageView Img_forum_dianzan3;
    private ImageView Img_forum_dianzan4;
    private ImageView Img_forum_dianzan5;
    private ImageView Img_forum_dianzan6;
    private VideoView forum_VideoView;
    boolean isChanged = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_forum,
                container,
                false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Img_forum_dianzan1 = getActivity().findViewById(R.id.Img_forum_dianzan1);
        Img_forum_dianzan2 = getActivity().findViewById(R.id.Img_forum_dianzan2);
        Img_forum_dianzan3 = getActivity().findViewById(R.id.Img_forum_dianzan3);
        Img_forum_dianzan4 = getActivity().findViewById(R.id.Img_forum_dianzan4);
        Img_forum_dianzan5 = getActivity().findViewById(R.id.Img_forum_dianzan5);
        Img_forum_dianzan6 = getActivity().findViewById(R.id.Img_forum_dianzan6);

        Img_forum_dianzan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan1)
                {
                    if(isChanged){
                        Img_forum_dianzan1.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }else
                    {
                        Img_forum_dianzan1.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        Img_forum_dianzan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan2)
                {
                    if(isChanged){
                        Img_forum_dianzan2.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }else
                    {
                        Img_forum_dianzan2.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        Img_forum_dianzan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan3)
                {
                    if(isChanged){
                        Img_forum_dianzan3.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }else
                    {
                        Img_forum_dianzan3.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        Img_forum_dianzan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan4)
                {
                    if(isChanged){
                        Img_forum_dianzan4.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }else
                    {
                        Img_forum_dianzan4.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        Img_forum_dianzan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan5)
                {
                    if(isChanged){
                        Img_forum_dianzan5.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }else
                    {
                        Img_forum_dianzan5.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }
                    isChanged = !isChanged;

                }
            }
        });
        Img_forum_dianzan6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == Img_forum_dianzan6)
                {
                    if(isChanged){
                        Img_forum_dianzan6.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan));
                    }else
                    {
                        Img_forum_dianzan6.setImageDrawable(getResources().getDrawable(R.drawable.forum_dianzan_pink));
                    }
                    isChanged = !isChanged;

                }
            }
        });

    }
}