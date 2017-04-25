package com.example.biao.thehealth.user.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.biao.thehealth.R;

public class User_manager extends Activity implements View.OnClickListener {
    private TextView reback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_usermanager);

        initBtn();

    }


    private void initBtn() {
        reback = (TextView) findViewById(R.id.user_back);
        reback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_back:
                finish();
                break;
        }
    }
}
