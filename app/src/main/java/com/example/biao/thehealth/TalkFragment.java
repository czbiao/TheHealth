package com.example.biao.thehealth;

/**
 * Created by Biao on 2017/3/31.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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


    public TalkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.talk_activity, container, false);
        mImageView = (ImageView) view.findViewById(R.id.imageView);

        TextView txt_content = (TextView) view.findViewById(R.id.txt_topbar);

        txt_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*new Thread(){
                    public void run(){
                        handler.post(runnableUi);
                    }
                }.start();*/
                new WorkThread().start();
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

                URL url;
                url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                mBitmap = BitmapFactory.decodeStream(is);
                // 加载到布局文件中
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                System.out.println("tdw1");
                    InputStream inputStream = conn.getInputStream();
                    mBitmap = BitmapFactory.decodeStream(inputStream);


            } catch (Exception e) {
                e.printStackTrace();
            }

            //处理完成后给handler发送消息
            Message msg = new Message();
            System.out.println("222222222222222");
            handler.sendMessage(msg);
            //handler.handleMessage(msg);
        }
    }

    // 构建Runnable对象，在runnable中更新界面
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            mBitmap = getBitmapFromServer(path);
            mImageView.setImageBitmap(mBitmap);
        }

    };


    public static Bitmap getBitmapFromServer(String imagePath) {

        HttpGet get = new HttpGet(imagePath);
        HttpClient client = new DefaultHttpClient();
        Bitmap pic = null;
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            pic = BitmapFactory.decodeStream(is);   // 关键是这句代码

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pic;
    }

}