package com.example.biao.thehealth;

/**
 * Created by Biao on 2017/3/31.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class MouldFragment extends Fragment {


    public MouldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_topbar);
        txt_content.setText("第4个Fragment");

        return view;
    }

}