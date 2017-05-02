package com.example.biao.thehealth.user.login.activity;



import android.app.Activity;
import android.os.Bundle;
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
