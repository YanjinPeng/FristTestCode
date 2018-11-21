package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ImageActivity extends AppCompatActivity {

	boolean isFirst;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if (actionBar != null){
			actionBar.hide();//隐藏系统标题栏
		}

		sp  =  getSharedPreferences("data",MODE_PRIVATE);
		isFirst = sp.getBoolean("FIRST",false);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (!isFirst) {
					SharedPreferences.Editor editor  =  sp.edit();
					editor.putBoolean("FIRST", true);
					editor.commit();

					Intent intent = new Intent(ImageActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}else {
					Intent intent = new Intent(ImageActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};
		timer.schedule(task, 1000);
	}
}