package com.example.shopping.Common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Goods_more_index;
import com.example.shopping.R;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

//����������
public class CommonMath extends Activity {
	
	// �ֲ�ͼ������
		private ScrollView mScrollView;
		private float mLastX;
		private ViewPager viewPager;
		private List<ImageView> views = new ArrayList<ImageView>();
		private List<ADInfo> infos = new ArrayList<ADInfo>();
		private CycleViewPager cycleViewPager;
		
	
	/**
	 * ��ȡͼƬ���ƻ�ȡͼƬ����Դid�ķ���
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResourceID(String imageName) {
		String[] str = imageName.split(",");
		imageName = str[0];
		Class drawable = R.drawable.class;
		int r_id;
		Field field = null;
		try {
			field = drawable.getField(imageName);
			String a = field.getName();
			r_id = field.getInt(a);
		} catch (Exception e) {
			r_id = R.drawable.noproduct;
			Log.e("ERROR", "PICTURE NOT��FOUND��");
		}
		return r_id;
	}

	// ����ͼƬ����ԴID
	public static int[] getImageResourceListID(String imageName) {
		String[] str = imageName.split(",");
		int[] r_ids = {};
		Class drawable = R.drawable.class;

		try {
			for (int i = 0; i < str.length; i++) {
				r_ids = addIntArry(r_ids, getImageResourceID(str[i]));
			}
		} catch (Exception e) {
			r_ids = addIntArry(r_ids, R.drawable.noproduct);
			Log.e("ERROR", "PICTURE NOT��FOUND��");
		}
		return r_ids;
	}

	// �������Ԫ��
	@SuppressLint("NewApi")
	static int[] addIntArry(int[] ary, int value) {
		ary = Arrays.copyOf(ary, ary.length + 1);
		ary[ary.length - 1] = value;
		return ary;
	}

	// �޸�һ����¼������ͨ��id
	public boolean updateGoodsByID(int id) {
		boolean b = false;
		Quanju q = (Quanju) getApplicationContext();// ��ȡ���б�����
		for (Goods goods : q.GoodsList) {
			if (goods.getID() == id) {
				goods.setVistNum(goods.getVistNum() + 1);
				b = true;
				break;
			}
		}
		return b;
	}
	

	// �ֲ�ͼ��ʼ������-----��������
	@SuppressLint("NewApi")
	public  void initialize(int[] imageUrls,ImageCycleViewListener mAdCycleViewListener ) {

		cycleViewPager = (CycleViewPager) getFragmentManager()
				.findFragmentById(R.id.fragment_cycle_viewpager_content);

		for (int i = 0; i < imageUrls.length; i++) {
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
			info.setContent("ͼƬ-->" + i);
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
		// �����ֲ�
		cycleViewPager.setWheel(false);

		// �����ֲ�ʱ�䣬Ĭ��5000ms
		cycleViewPager.setTime(2000);
		// ����Բ��ָʾͼ���������ʾ��Ĭ�Ͽ���
		// cycleViewPager.setIndicatorCenter();
	}
	
	
		
	

}
