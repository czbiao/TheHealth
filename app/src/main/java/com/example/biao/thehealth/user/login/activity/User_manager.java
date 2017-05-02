package com.example.biao.thehealth.user.login.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biao.thehealth.R;
import com.example.biao.thehealth.user.login.entities.User;
import com.example.biao.thehealth.user.login.global.GlobalConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.biao.thehealth.user.UserFragment.login_username;

public class User_manager extends Activity implements View.OnClickListener {
    private TextView reback, user_maneger_text;
    private TextView username, passwd, sex, phone, commun_way;
    private ArrayList<User> user = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 3) {// 当服务器返回给客户端的标记为3时，说明获取信息成功
                username.setText(user.get(0).getUsername());
                passwd.setText(user.get(0).getPassword());
                sex.setText(user.get(0).getSex());
                phone.setText(user.get(0).getCommunication_num());
                commun_way.setText(user.get(0).getCommunication_way());


                Log.i("user_manager----->", "success");
                System.out.println("服务器返回值："+msg.what);


            } else {
                Toast.makeText(User_manager.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_usermanager);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initBtn();
        getSeversJson();
        user_maneger_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSeversJson();
            }
        });

    }


    private void initBtn() {
        reback = (TextView) findViewById(R.id.user_back);
        reback.setOnClickListener(this);
        username = (TextView)findViewById(R.id.getusername);
        passwd = (TextView)findViewById(R.id.getpasswd);
        sex = (TextView)findViewById(R.id.getsex);
        phone = (TextView)findViewById(R.id.getphone);
        commun_way = (TextView) findViewById(R.id.getcommun_way);
        //User user = getU
        user_maneger_text = (TextView)findViewById(R.id.user_maneger_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_back:
                finish();
                break;
        }
    }

    public void getSeversJson(){
        new Thread() {
            public void run() {
                String strResult="";
                HttpClient client = new DefaultHttpClient();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                NameValuePair pair = new BasicNameValuePair("index", "3");
                list.add(pair);

                NameValuePair pair1 = new BasicNameValuePair("username",
                        login_username.getText().toString());

                list.add(pair1);
                Log.i("user_manager----->到这", login_username.getText().toString());
                try {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                    HttpPost post = new HttpPost("http://139.199.171.66:8080/server/Servlet");
                    post.setEntity(entity);
                    HttpResponse response = client.execute(post);
                    int a= response.getStatusLine().getStatusCode();
                    Log.i("到这", a+"");
                    if (response.getStatusLine().getStatusCode() == 200) {
                        strResult = EntityUtils.toString(response.getEntity());
                        //JSONObject jsonObject = JSONObject.fromObject(strResult);
                        //user = (User)JSONObject.toBean(jsonObject,User.class);
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<User>>(){}.getType();
                        user = gson.fromJson(strResult, type);

                        Log.i("user_manager-->到这trymmm", "success");

                        //InputStream in = response.getEntity().getContent();
                        handler.sendEmptyMessage(3);// 将服务器返回给客户端的标记直接传给handler
                        //in.close();
                    }

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.i("user_manager----->到这try", "success");
            }

        }.start();
    }

}
