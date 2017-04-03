package com.example.biao.thehealth;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button community_btn;
    private Button nutrition_btn;
    private Button consult_btn;
    private Button mould_btn;
    private Button user_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);

        init_bottombtn();
    }

    //UI组件初始化与事件绑定
    public void init_bottombtn(){
        community_btn = (Button)this.findViewById(R.id.community_btn);
        nutrition_btn = (Button)this.findViewById(R.id.nutrition_btn);
        consult_btn = (Button)this.findViewById(R.id.consult_btn);
        mould_btn = (Button)this.findViewById(R.id.mould_btn);
        user_btn = (Button)this.findViewById(R.id.user_btn);

        community_btn.setOnClickListener(this);
        nutrition_btn.setOnClickListener(this);
        consult_btn.setOnClickListener(this);
        mould_btn.setOnClickListener(this);
        user_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.community_btn:
                community_btn.setHighlightColor(Color.CYAN);
                //intent = new Intent(this, CommunityActivity.class);
                //startActivity(intent);
                setContentView(R.layout.activity_main);
                break;
            case R.id.nutrition_btn:
                nutrition_btn.setHighlightColor(Color.CYAN);
                //intent = new Intent(this, NutritionActivity.class);
                //startActivity(intent);
                setContentView(R.layout.activity_main);
                break;
            case R.id.consult_btn:
                consult_btn.setHighlightColor(Color.CYAN);
                //intent = new Intent(this, ConsultActivity.class);
                //startActivity(intent);
                setContentView(R.layout.activity_main);
                break;
            case R.id.mould_btn:
                mould_btn.setHighlightColor(Color.CYAN);
                //intent = new Intent(this, MouldActivity.class);
                //startActivity(intent);
                setContentView(R.layout.activity_main);
                break;
            case R.id.user_btn:
                user_btn.setHighlightColor(Color.CYAN);
                //intent = new Intent(this, UserActivity.class);
                //startActivity(intent);
                setContentView(R.layout.my_activity);
                break;
        }
    }
}
