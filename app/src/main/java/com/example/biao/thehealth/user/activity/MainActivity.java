package com.example.biao.thehealth.user.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.biao.thehealth.R;

public class MainActivity extends Activity {
    Button exit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//		exit = (Button)this.findViewById(R.id.main_exit);
//		exit.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				MainActivity.this.finish();
//			}
//		});
    }
}
