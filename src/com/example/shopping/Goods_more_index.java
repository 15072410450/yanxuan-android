package com.example.shopping;

import java.util.ArrayList;
import java.util.List;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.stevenhu.android.phone.bean.ADInfo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Goods_more_index extends Activity {

	
	// �ֲ�ͼ������
		private ScrollView mScrollView;
		private float mLastX;
		private ViewPager viewPager;
		private List<ImageView> views = new ArrayList<ImageView>();
		private List<ADInfo> infos = new ArrayList<ADInfo>();
		private CycleViewPager cycleViewPager;
		// �ֲ�ͼͼƬ
		private int[] imageUrls = {
				R.drawable.banner_test,
				R.drawable.banner_test1,
				R.drawable.banner_test,
				R.drawable.banner_test,
				R.drawable.banner_test
				};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_more_index);
		// �ֲ�ͼ��ʼ��
		initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_more_index, menu);
		return true;
	}
	
	// �ֲ�ͼ��ʼ������
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
//		cycleViewPager.setIndicatorCenter();
	}
	
	// ���ͼƬ֮��ļ�������
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(Goods_more_index.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}
			
		}

	};

}
