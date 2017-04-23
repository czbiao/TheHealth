package com.example.biao.thehealth.health_frag;

/**
 * Created by Biao on 2017/3/31.
 */

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnCloseListener;

import com.example.biao.thehealth.R;
import com.example.biao.thehealth.TalkFragment;
import com.example.biao.thehealth.UserFragment;
import com.example.biao.thehealth.health_frag.BlankFragment;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchBox.MenuListener;
import com.quinny898.library.persistentsearch.SearchBox.SearchListener;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class HealthFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    private ListView mListView;

    private RadioGroup mRadioGroup;
    Boolean isSearch;
    private SearchBox search;



    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.health_avtivity, container, false);

        //mSearchView = (SearchView) view.findViewById(R.id.searchView);
        //mSearchView.setSubmitButtonEnabled(true);
        //mListView = (ListView) view.findViewById(R.id.listView);
        //mListView.setAdapter(new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_list_item_1, mStrs));
        //mListView.setTextFilterEnabled(true);

        /*// 设置搜索文本监听
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
        });*/

        mRadioGroup = (RadioGroup) view.findViewById(R.id.health_rg_btn);
        mRadioGroup.setOnCheckedChangeListener(this);
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BlankFragment mFragment = new BlankFragment();
        ft.replace(R.id.frag, mFragment).commit();

        search = (SearchBox) view.findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history));
            search.addSearchable(option);
        }
        search.setMenuListener(new MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                Toast.makeText(getActivity(), "Menu click", Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged() {
                //React to the search term changing
                //Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(getActivity(), searchTerm +" Searched", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked

            }

        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}