package ldu.xd.lft.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.hwrecsys.R;
import com.example.hwrecsys.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_rec;
	private Button btn_callcam;
	private TextView tv_username;
	private ImageView imgview;
	private List<Drawable> imglist = new ArrayList<Drawable>();
	private String photospath = "/sdcard/DCIM/Camera/";// 照片的存放位置。
	public final static int camera_msg = 101;// 跳转到照相机的请求码
	private int index = 0;// 当前显示的图片的索引
	private File filecurrent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setClikListener();

	}

	/**
	 * TODO</br>初始化控件
	 * 
	 * <pre>
	 * 显示图片
	 * 调用系统相机按钮
	 * 识别图片按钮
	 * </pre>
	 * 
	 * <pre>
	 * 
	 * </pre>
	 */
	private void initView() {
		// TODO Auto-generated method stub

		btn_rec = (Button) findViewById(R.id.main_btn_rec);
		btn_callcam = (Button) findViewById(R.id.main_btn_callcam);
		tv_username = (TextView) findViewById(R.id.main_tv_username);
		imgview = (ImageView) findViewById(R.id.main_imgv);
	}

	/**
	 * TODO为按钮添加监听事件</br>
	 * 
	 * <pre>
	 * 调用系统相机的点击事件
	 * 
	 * </pre>
	 * 
	 * <pre>
	 * 识别图片的点击事件
	 * </pre>
	 */
	private void setClikListener() {

		// TODO Auto-generated method stub
		this.btn_callcam.setOnClickListener(new OnClickListener() {
			// 调用系统相机
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA))
						+ ".jpg";// 以时间给照片命名
				filecurrent = new File(photospath + name);
				// 把文件地址转换成Uri格式
				Uri uri = Uri.fromFile(filecurrent);
				// 设置系统相机拍摄照片完成后图片文件的存放地址
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, camera_msg);
				// System.out.println("=========photoUri:" + photoUri);
			}
		});
		this.btn_rec.setOnClickListener(new OnClickListener() {
			// 识别照片中的文字
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == camera_msg) {
			Uri uri = Uri.fromFile(filecurrent);
			imgview.setImageURI(uri);
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

}
