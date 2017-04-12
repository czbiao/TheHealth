package com.example.biao.thehealth;

/**
 * Created by Biao on 2017/3/31.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.biao.thehealth.health_frag.BlankFragment;


/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class HealthFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    private ListView mListView;

    private RadioGroup mRadioGroup;

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.health_avtivity, container, false);


        mSearchView = (SearchView) view.findViewById(R.id.searchView);
        mSearchView.setSubmitButtonEnabled(true);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(container.getContext(), "您的选择是:" + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

        mRadioGroup = (RadioGroup)view.findViewById(R.id.health_rg_btn);
        mRadioGroup.setOnCheckedChangeListener(this);
        FragmentManager fm=getChildFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        BlankFragment mFragment = new BlankFragment();
        ft.replace(R.id.frag,mFragment).commit();


        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager fm=getChildFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            BlankFragment mFragment = new BlankFragment();

        switch (checkedId) {
            case R.id.health_btn1:
                ft.replace(R.id.frag,mFragment).commit();
                break;
            case R.id.health_btn2:
                TalkFragment mTalkFragment = new TalkFragment();
                ft.replace(R.id.frag,mTalkFragment).commit();
                break;
            case R.id.health_btn3:
                ft.replace(R.id.frag,mFragment).commit();
                break;
            case R.id.health_btn4:
                UserFragment mUserFragment = new UserFragment();
                ft.add(R.id.frag,mUserFragment).commit();
                break;

        }
    }
}