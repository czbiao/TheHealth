package com.example.biao.thehealth.user.login.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.example.biao.thehealth.user.login.global.GlobalConstants;

import com.example.biao.thehealth.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Login
 *用户在打开该App时看到的界面，包括用户的登陆，链接注册，使用说明等界面
 */

public class Login extends Activity {
    private static final String PREFS_NAME = "MyInfo";// 用来保存用户信息

    private EditText userName = null;
    private EditText password = null;
    private Button register = null;
    // private Button instruction = null;
    private Button login = null;
    private TextView login_title = null;
    private CheckBox checkBox1;
    private Button pwdback = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 1) {// 当服务器返回给客户端的标记为1时，说明登陆成功
                //Intent login_main = new Intent(Login.this, MainActivity.class);

                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                bundle.putString("user",userName.getText().toString());
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);

                Log.i("login_main----->", "success");
                System.out.println("服务器返回值："+msg.what);
                //startActivity(login_main);
                finish();
            } else {
                Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initListener();
    }



    /*
     * login页面参数初始化，获取login页面对应的元素
     */
    private void init() {
        userName = (EditText) this.findViewById(R.id.userName);
        password = (EditText) this.findViewById(R.id.password);
        register = (Button) this.findViewById(R.id.register);
        // instruction = (Button) this.findViewById(R.id.instruction);
        login = (Button) this.findViewById(R.id.login);
        login_title = (TextView) this.findViewById(R.id.login_title);
        checkBox1 = (CheckBox) this.findViewById(R.id.checkBox1);
        pwdback = (Button) this.findViewById(R.id.pwdback);// 找回密码
    }

    /*
     * 对login上的页面元素设置监听
     */
    private void initListener() {
        this.login.setOnClickListener(new LoginListener());// 登陆
        this.register.setOnClickListener(new ButtonRegister());// 注册
        this.pwdback.setOnClickListener(new ButtonPwdback());// 注册
        // this.instruction.setOnClickListener(new ButtonInstruction());
    }

    /**
     *
     * @author 登陆事件 用户提供的数据和用户数据库中的数据进行匹配，有则可以登陆，无则提示先注册
     */

    private class LoginListener implements OnClickListener {
        String myUserName = userName.getText().toString();
        String passwd = password.getText().toString();

        @Override
        public void onClick(View v) {

            new Thread() {
                public void run() {
                    HttpClient client = new DefaultHttpClient();
                    List<NameValuePair> list = new ArrayList<NameValuePair>();
                    NameValuePair pair = new BasicNameValuePair("index", "0");
                    list.add(pair);
                    NameValuePair pair1 = new BasicNameValuePair("username",
                            userName.getText().toString());
                    NameValuePair pair2 = new BasicNameValuePair("password",
                            password.getText().toString());

                    list.add(pair1);
                    list.add(pair2);
                    try {
                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                                list, "UTF-8");
                        HttpPost post = new HttpPost(
                                GlobalConstants.CATEGORY_URL);
                        post.setEntity(entity);
                        HttpResponse response = client.execute(post);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            InputStream in = response.getEntity().getContent();
                            handler.sendEmptyMessage(in.read());// 将服务器返回给客户端的标记直接传给handler
                            in.close();
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

                }

            }.start();
        }

    }

    /**
     * 设置register监听
     */
    private class ButtonRegister implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            Intent login_agreement = new Intent(Login.this, Agreement.class);
            Log.i("login_agreement----->", "success");
            startActivity(login_agreement);
            finish();
        }
    }

    /**
     * 设置pwdback找回密码的舰艇时间
     */
    private class ButtonPwdback implements OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // 暂时无响应事件
            Toast.makeText(Login.this, "该功能正在开发中...", Toast.LENGTH_SHORT).show();

        }

    }

}
