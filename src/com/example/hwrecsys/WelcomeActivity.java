package com.example.hwrecsys;

import ldu.xd.lft.activities.LoginActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	private TextView tv_time;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				tv_time.setText(String.valueOf(msg.arg1));
				break;
			case 2:
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
	}

	/**
	 * TODO
	 * <p>
	 * 初始化控件
	 * </p>
	 *
	 * @param <p>
	 *            </br> </p>
	 *
	 */
	private void initView() {
		// TODO Auto-generated method stub
		tv_time = (TextView) findViewById(R.id.wel_tv_time);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Thread t = new Thread(new Runnable() {// 另起一个线程实现倒计时
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 5; i > 0; i--) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Message msg = new Message();
							msg.what = 1;
							msg.arg1 = i;
							handler.sendMessage(msg);
						}
						handler.sendEmptyMessage(2);// 倒计时结束消息
					}
				});
		t.start();
	}
}
