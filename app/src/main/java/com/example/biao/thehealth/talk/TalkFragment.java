package com.example.biao.thehealth.talk;

/**
 * Created by Biao on 2017/3/31.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biao.thehealth.R;
import com.example.biao.thehealth.talk.ImageCycleView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 为了顺便演示ViewPager的机制，
 * 特意写成了四个Fragment！在onCreateView中打印创建Log！
 */
public class TalkFragment extends Fragment {

    private ImageView mImageView;
    private Bitmap mBitmap;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("ceshi","接收到消息");
            mImageView.setImageBitmap(mBitmap);
        }
    };
    private String path="http://139.199.171.66:8080/server/222.jpg";
    private ImageCycleView mImageCycleView;

    public TalkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.talk_activity, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_topbar);

        txt_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WorkThread().start();
            }
        });


        mImageCycleView = (ImageCycleView) view.findViewById(R.id.icv_topView);
//		mImageCycleView.setAutoCycle(false); //关闭自动播放
//		mImageCycleView.setCycleDelayed(2000);//设置自动轮播循环时间
//
//		mImageCycleView.setIndicationStyle(ImageCycleView.IndicationStyle.COLOR,
//				Color.BLUE, Color.RED, 1f);

		mImageCycleView.setIndicationStyle(ImageCycleView.IndicationStyle.IMAGE,
				R.drawable.dian_unfocus, R.drawable.dian_focus, 1f);

//		Log.e("eee", Environment.getExternalStorageDirectory().getPath()+ File.separator+"a1.jpg");

        List<ImageCycleView.ImageInfo> list=new ArrayList<ImageCycleView.ImageInfo>();

        //res图片资源
        list.add(new ImageCycleView.ImageInfo(R.drawable.aa1,"111111111111",""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.aa2,"222222222222",""));
        list.add(new ImageCycleView.ImageInfo(R.drawable.aa3,"333333333333",""));

        //SD卡图片资源
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a1.jpg"),"11111",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a2.jpg"),"22222",""));
//		list.add(new ImageCycleView.ImageInfo(new File(Environment.getExternalStorageDirectory(),"a3.jpg"),"33333",""));


        //使用网络加载图片
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/57ab6dc2-43f2-4087-81e2-b5ab5681642d.jpg","11","eeee"));
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/cb56a1a6-6c33-41e4-9c3c-363f4ec6b728.jpg","222","rrrr"));
//		list.add(new ImageCycleView.ImageInfo("http://img.lakalaec.com/ad/e4229e25-3906-4049-9fe8-e2b52a98f6d1.jpg", "333", "tttt"));

//		mImageCycleView.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
//			@Override
//			public void onClick(View imageView, ImageCycleView.ImageInfo imageInfo) {
//				Toast.makeText(MainActivity.this, "你点击了" + imageInfo.value.toString(), Toast.LENGTH_SHORT).show();
//			}
//		});

        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

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


        return view;
    }



    //工作线程
    private class WorkThread extends Thread {
        @Override
        public void run() {
            //......处理比较耗时的操作

            /*try {
                HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                System.out.println("tdw1");
                InputStream inputStream = conn.getInputStream();
                mBitmap = BitmapFactory.decodeStream(inputStream);


            } catch (Exception e) {
                e.printStackTrace();
            }*/
            mBitmap=getBitmapFromServer(path);

            //处理完成后给handler发送消息
            Message msg = new Message();
            System.out.println("222222222222222");
            handler.sendMessage(msg);
            //handler.handleMessage(msg);
        }
    }


    public Bitmap getBitmapFromServer(String imagePath) {

        Bitmap bitmap = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(imagePath).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}