package com.example.biao.thehealth.user;

/**
 * Created by Biao on 2017/3/31.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.biao.thehealth.R;

/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    private Button user_btn_1,user_btn_2,user_btn_3,user_btn_4,user_btn_5;
    private View view;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.user_activity, container, false);
        Button loginbtn = (Button)view.findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext(),"没有登录",Toast.LENGTH_SHORT).show();
            }
        });

        initMyBtn();


        return view;
    }

    private void initMyBtn() {
        user_btn_1 = (Button) view.findViewById(R.id.user_btn_1);
        user_btn_2 = (Button) view.findViewById(R.id.user_btn_2);
        user_btn_3 = (Button) view.findViewById(R.id.user_btn_3);
        user_btn_4 = (Button) view.findViewById(R.id.user_btn_4);
        user_btn_5 = (Button) view.findViewById(R.id.user_btn_5);
        user_btn_1.setOnClickListener(this);
        user_btn_2.setOnClickListener(this);
        user_btn_3.setOnClickListener(this);
        user_btn_4.setOnClickListener(this);
        user_btn_5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_btn_1:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_btn_2:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_btn_3:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_btn_4:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_btn_5:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}