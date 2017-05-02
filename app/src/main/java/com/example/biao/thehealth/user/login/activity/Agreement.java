package com.example.biao.thehealth.user.login.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.biao.thehealth.R;








public class Agreement extends Activity {
    private RadioGroup agreement = null;
    private RadioButton agree = null;
    private RadioButton disagree = null;
    private Button next = null;
    private Button goback = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        init();
        initListener();
    }
    /**
     * 页面元素初始化
     */
    private void init (){
        this.agreement = (RadioGroup)this.findViewById(R.id.agreement);
        this.agree = (RadioButton)this.findViewById(R.id.agree);
        this.disagree = (RadioButton)this.findViewById(R.id.disagree);
        this.next = (Button)this.findViewById(R.id.next);
        this.goback = (Button)this.findViewById(R.id.gobackInAgreement);
    }
    /**
     * 对agreement上的页面元素设置监听
     */
    private void initListener(){
        this.agreement.setOnCheckedChangeListener(new RadioGroupAgreement());
        this.next.setOnClickListener(new ButtonNext());
        this.goback.setOnClickListener(new ButtonGoback());
    }
    /*
     * 设置RadioGroup--agreement的监听
     */
    private class RadioGroupAgreement implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            if (checkedId == agree.getId()) {
                next.setEnabled(true);
                Log.i("onCheckedChanged----------------->", "success");
            }else{
                next.setEnabled(false);
            }
        }
    }
    /*
     * 设置next的监听
     */
    private class ButtonNext implements OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent agreement_register = new Intent(Agreement.this, Register.class);
            Log.i("agreement_register----------------->", "success");
            startActivity(agreement_register);
            finish();
        }
    }
    /*
     * 设置goback监听
     */
    private class ButtonGoback implements OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent agreement_login = new Intent(Agreement.this, Login.class);
            Log.i("agreement_login----------------->", "success");
            startActivity(agreement_login);
            finish();
        }
    }
}
