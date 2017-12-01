package com.example.shopping;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

public class MainActivity extends Activity {

	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;
	
	
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	
	private int[] imageUrls = {
			R.drawable.banner_test,
			R.drawable.banner_test,
			R.drawable.banner_test,
			};
	

	Quanju q; // ����ȫ����
	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		q = (Quanju) getApplicationContext();// ��ȡ���б�����
		q.Init();// ��ʼ������

		// ҳ��ײ���������ת����
		routerPageFun();
		 // �����¼���ʼ������ 
		  btFindGo();
		// ��ʼ��banner�ֲ�ͼ
			initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// ҳ��ײ���������ת����
	public void routerPageFun() {
		layout_menu_1 = (LinearLayout) findViewById(R.id.layout_menu_1);
		layout_menu_2 = (LinearLayout) findViewById(R.id.layout_menu_2);
		layout_menu_3 = (LinearLayout) findViewById(R.id.layout_menu_3);
		layout_menu_4 = (LinearLayout) findViewById(R.id.layout_menu_4);

		// ����ͼ��
		imagv_1 = (ImageView) findViewById(R.id.imagev_1);
		imagv_1.setImageResource(R.drawable.center1_2);
		// ����������ɫ
		textV_1 = (TextView) findViewById(R.id.textV_1);
		textV_1.setTextColor(getResources().getColor(R.color.text_bg));

		// ��ת����
		layout_menu_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity2.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});

		layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity3.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();

			}
		});
		layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity.this, MainActivity4.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
	}
	
	
	// *********��ť�¼�����******************

		// ��ҳ�����¼�
		public void btFindGo() {
			TextView bt = (TextView) MainActivity.this.findViewById(R.id.find);
			bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					TextView tv = (TextView) MainActivity.this
							.findViewById(R.id.findText);
					String findEditStr = tv.getText().toString().trim();// ��ȡ�����ֵ
					// ��ת���ڶ���ҳ��
				    intent = new Intent(MainActivity.this, Find_index.class);
					intent.putExtra("findEditStr", findEditStr); // �����ַ�������
					startActivity(intent);
				}
			});

		}
		
		
		@SuppressLint("NewApi")
		private void initialize() {
			
			cycleViewPager = (CycleViewPager) getFragmentManager()
					.findFragmentById(R.id.fragment_cycle_viewpager_content);
			
			for(int i = 0; i < imageUrls.length; i ++){
				ADInfo info = new ADInfo();
				info.setUrl(imageUrls[i]);
				info.setContent("ͼƬ-->" + i );
				infos.add(info);
			}
			
			
			ImageView imageView = new ImageView(this);  
			imageView.setBackgroundResource(imageUrls[0]);
			views.add(imageView);
			
			for (int i = 0; i < infos.size(); i++) {
				ImageView imageView2 = new ImageView(this);  
				imageView2.setBackgroundResource(imageUrls[i]);
				views.add(imageView2);
			}
			// ����һ��ImageView��ӽ���
			ImageView imageView3 = new ImageView(this);  
			imageView3.setBackgroundResource(imageUrls[2]);
			views.add(imageView3);
			
			// ����ѭ�����ڵ���setData����ǰ����
			cycleViewPager.setCycle(true);

			// �ڼ�������ǰ�����Ƿ�ѭ��
			cycleViewPager.setData(views, infos, mAdCycleViewListener);
			//�����ֲ�
			cycleViewPager.setWheel(false);

		    // �����ֲ�ʱ�䣬Ĭ��5000ms
			cycleViewPager.setTime(2000);
			//����Բ��ָʾͼ���������ʾ��Ĭ�Ͽ���
//			cycleViewPager.setIndicatorCenter();
		}
		
		// ���ͼƬ֮��ļ�������
		private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

			@Override
			public void onImageClick(ADInfo info, int position, View imageView) {
				if (cycleViewPager.isCycle()) {
					position = position - 1;
					Toast.makeText(MainActivity.this,
							"position-->" + info.getContent(), Toast.LENGTH_SHORT)
							.show();
				}
				
			}

		};

}
