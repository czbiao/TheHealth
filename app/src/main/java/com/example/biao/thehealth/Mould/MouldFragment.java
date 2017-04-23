package com.example.biao.thehealth.Mould;

/**
 * Created by Biao on 2017/3/31.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.biao.thehealth.Mould.Mould_ImageCycleView;
import com.example.biao.thehealth.R;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class MouldFragment extends Fragment {
    private Mould_ImageCycleView mMouldImageCycleView;
    Boolean isSearch;
    private SearchBox search;

    public MouldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mould_activity, container, false);


        mMouldImageCycleView = (Mould_ImageCycleView) view.findViewById(R.id.mould_View);
//		mImageCycleView.setAutoCycle(false); //关闭自动播放
//		mImageCycleView.setCycleDelayed(2000);//设置自动轮播循环时间
//
//        mMouldImageCycleView.setIndicationStyle(Mould_ImageCycleView.IndicationStyle.COLOR,
//				Color.BLUE, Color.RED, 1f);

//        mMouldImageCycleView.setIndicationStyle(Mould_ImageCycleView.IndicationStyle.IMAGE,
//              R.drawable.dian_unfocus, R.drawable.dian_focus, 1f);

//		Log.e("eee", Environment.getExternalStorageDirectory().getPath()+ File.separator+"a1.jpg");

        List<Mould_ImageCycleView.ImageInfo> list=new ArrayList<Mould_ImageCycleView.ImageInfo>();

        //res图片资源
        list.add(new Mould_ImageCycleView.ImageInfo(R.drawable.aa1,"111111111111","第一张"));
        list.add(new Mould_ImageCycleView.ImageInfo(R.drawable.aa2,"222222222222","第二张"));
        list.add(new Mould_ImageCycleView.ImageInfo(R.drawable.aa3,"333333333333","第三张"));
        list.add(new Mould_ImageCycleView.ImageInfo(R.drawable.aa2,"333333333333","第四张"));

        //SD卡图片资源
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a1.jpg"),"11111",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a2.jpg"),"22222",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a3.jpg"),"33333",""));


        //使用网络加载图片
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg","11","eeee"));
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg","222","rrrr"));
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg", "333", "tttt"));

        mMouldImageCycleView.setOnPageClickListener(new Mould_ImageCycleView.OnPageClickListener() {
			@Override
			public void onClick(View imageView, Mould_ImageCycleView.ImageInfo imageInfo) {
				Toast.makeText(getActivity(), "你点击了" + imageInfo.value.toString(), Toast.LENGTH_SHORT).show();
			}
		});

        mMouldImageCycleView.loadData(list, new Mould_ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(Mould_ImageCycleView.ImageInfo imageInfo) {

                //本地图片
                ImageView imageView=new ImageView(getActivity());
                imageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
                return imageView;


//				//使用SD卡图片
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageURI(Uri.fromFile((File)imageInfo.image));
//				return smartImageView;

//				//使用SmartImageView，既可以使用网络图片也可以使用本地资源
//				SmartImageView smartImageView=new SmartImageView(MainActivity.this);
//				smartImageView.setImageResource(Integer.parseInt(imageInfo.image.toString()));
//				return smartImageView;

                //使用BitmapUtils,只能使用网络图片
//				BitmapUtils bitmapUtils = new BitmapUtils(MainActivity.this);
//				ImageView imageView = new ImageView(MainActivity.this);
//				bitmapUtils.display(imageView, imageInfo.image.toString());
//				return imageView;
            }
        });


        search = (SearchBox) view.findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);
        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.ic_history));
            search.addSearchable(option);
        }
        search.setMenuListener(new SearchBox.MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                Toast.makeText(getActivity(), "Menu click", Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchBox.SearchListener(){

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}