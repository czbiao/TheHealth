package com.example.biao.thehealth.health;

/**
 * Created by Biao on 2017/3/31.
 */

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.biao.thehealth.R;
import com.example.biao.thehealth.talk.TalkFragment;
import com.example.biao.thehealth.user.UserFragment;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.MenuListener;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class HealthFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup mRadioGroup;
    Boolean isSearch;
    private SearchBox search;
    private View view;
    private Button health_bar_btn1,health_bar_btn2,health_bar_btn3,health_bar_btn4,health_bar_btn5,health_bar_btn6,health_bar_btn7,health_bar_btn8;

    public HealthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.health_avtivity, container, false);

        initBannerBtn();

        mRadioGroup = (RadioGroup) view.findViewById(R.id.health_rg_btn);
        mRadioGroup.setOnCheckedChangeListener(this);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        TalkFragment mFragment = new TalkFragment();
        ft.replace(R.id.frag, mFragment).commit();

        search = (SearchBox) view.findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("result" + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history));
            search.addSearchable(option);
        }
        search.setMenuListener(new MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                Toast.makeText(getActivity(), "Menu click", Toast.LENGTH_SHORT).show();
            }

        });
        search.setSearchListener(new SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
                Toast.makeText(getActivity(), " onSearchOpened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
                Toast.makeText(getActivity(), " onSearchClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchTermChanged() {
                //React to the search term changing
                //Called after it has updated results
                //搜索数据改变时候提示
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(getActivity(), searchTerm +" Searched", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
                Toast.makeText(getActivity(), " onSearchCleared", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }

    public void initBannerBtn(){
        health_bar_btn1 = (Button) view.findViewById(R.id.health_bar_btn1);
        health_bar_btn2 = (Button) view.findViewById(R.id.health_bar_btn2);
        health_bar_btn3 = (Button) view.findViewById(R.id.health_bar_btn3);
        health_bar_btn4 = (Button) view.findViewById(R.id.health_bar_btn4);
        health_bar_btn5 = (Button) view.findViewById(R.id.health_bar_btn5);
        health_bar_btn6 = (Button) view.findViewById(R.id.health_bar_btn6);
        health_bar_btn7 = (Button) view.findViewById(R.id.health_bar_btn7);
        health_bar_btn8 = (Button) view.findViewById(R.id.health_bar_btn8);
        health_bar_btn1.setOnClickListener(this);
        health_bar_btn2.setOnClickListener(this);
        health_bar_btn3.setOnClickListener(this);
        health_bar_btn4.setOnClickListener(this);
        health_bar_btn5.setOnClickListener(this);
        health_bar_btn6.setOnClickListener(this);
        health_bar_btn7.setOnClickListener(this);
        health_bar_btn8.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager fm=getChildFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            BlankFragment mFragment = new BlankFragment();
            TalkFragment mTalkFragment = new TalkFragment();

        switch (checkedId) {
            case R.id.health_btn1:
                ft.replace(R.id.frag,mTalkFragment).commit();
                break;
            case R.id.health_btn2:
                ft.replace(R.id.frag,mFragment).commit();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.health_bar_btn1:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn2:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn3:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn4:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn5:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn6:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn7:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.health_bar_btn8:
                Toast.makeText(getActivity(), "该功能正在开发中...", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}