package ldu.xd.lft.activities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwrecsys.R;

public class LoginActivity extends Activity {
	private String url = "http://112.237.255.221:8080/HwRecSysServer/login/login.action";// 服务器端登陆的action的位置
	private EditText et_name;
	private EditText et_pwd;
	private Button btn_login;
	private TextView tv_forget;
	private TextView tv_new;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
				break;
			case 2:
				Toast.makeText(LoginActivity.this, "登陆失败，请检查您的用户名密码！",
						Toast.LENGTH_LONG).show();
				break;
			case 3:
				Toast.makeText(LoginActivity.this, "远程服务器已经关闭，请稍后重试！",
						Toast.LENGTH_LONG).show();
				break;
			case 4:
				Toast.makeText(LoginActivity.this, "未知错误！", Toast.LENGTH_LONG)
						.show();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// 初始化控件
		initView();
		setListener();
	}

	/**
	 * TODO为控件添加相应的点击事件</br>
	 * 
	 * <pre>
	 * 登陆的点击事件
	 * </pre>
	 * 
	 * <pre>
	 * 注册新用户，忘记密码的点击事件
	 * </pre>
	 */
	private void setListener() {
		// TODO Auto-generated method stub

		this.btn_login.setOnClickListener(new View.OnClickListener() {// 登陆按钮的点击事件监听

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Android中的网络连接属于耗时操作，需要在子线程中开启。
						Thread t = new Thread(new Runnable() {

							@Override
							public void run() {
//								String username = et_name.getText().toString()
//										.trim();
//								String password = et_pwd.getText().toString()
//										.trim();
//								// TODO Auto-generated method stub
//								try {
//
//									DefaultHttpClient dhc = new DefaultHttpClient();
//									HttpPost hp = new HttpPost(url);
//									List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//									params.add(new BasicNameValuePair(
//											"username", username));
//									params.add(new BasicNameValuePair(
//											"password", password));
//									UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
//											params);
//									hp.setEntity(entity);
//									HttpResponse response = dhc.execute(hp);// 执行post请求
//									if (response.getStatusLine()
//											.getStatusCode() == 200) {// 服务器正确响应
//										String res = EntityUtils
//												.toString(response.getEntity());// 得到返回的实体。
//										// System.out.println("========" + res);
//										// 处理返回的json数据
//										JSONObject json = new JSONObject(res);
//
//										if (username.equals(json
//												.getString("username"))
//												&& password.equals(json
//														.getString("password"))) {// 该用户存在
//											System.out.println("-------------"
//													+ json.getString("username"));
//											System.out.println("-------------"
//													+ json.getString("password"));
//											// 发送消息，进行跳转。
											Message msg = new Message();
											msg.what = 1;
											handler.sendMessage(msg);
//
//										} else {
//											handler.sendEmptyMessage(2);// 用户名密码不正确或者没有该用户
//										}
//									} else {
//										handler.sendEmptyMessage(3);// 服务器没有响应
//									}
//								} catch (Exception e) {
//									// TODO Auto-generated catch block
//									handler.sendEmptyMessage(4);// 其他未知错误
//									e.printStackTrace();
//								}
							}
						});
						t.start();// 启动线程

					}
				});
		this.tv_forget.setOnClickListener(new View.OnClickListener() {// 忘记密码的点击事件监听

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

					}
				});
		this.tv_new.setOnClickListener(new View.OnClickListener() {// 新用户的点击事件监听

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * TODO 初始化控件
	 * 
	 * <pre>
	 * 用户名，密码文本输入框，
	 * 登陆按钮
	 * 忘记密码，新用户文本框
	 * </pre>
	 */
	private void initView() {
		// TODO Auto-generated method stub
		et_name = (EditText) findViewById(R.id.login_edt_username);
		et_pwd = (EditText) findViewById(R.id.login_edt_pwd);
		btn_login = (Button) findViewById(R.id.login_btn_login);
		tv_forget = (TextView) findViewById(R.id.login_tv_forget);
		tv_new = (TextView) findViewById(R.id.login_tv_newuser);
	}

}
