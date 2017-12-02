package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Common.MyGridView;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

public class MainActivity extends Activity {

	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;
	// �ֲ�ͼ������
	private ScrollView mScrollView;
	private float mLastX;
	private ViewPager viewPager;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	// �ֲ�ͼͼƬ
	private int[] imageUrls = { R.drawable.banner_test, R.drawable.banner_test,
			R.drawable.banner_test, };

	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;

	Quanju q; // ����ȫ����
	private GridView gridView;
	private LinearLayout ll;
	ArrayList<HashMap<String, Object>> data_list;// ��ȡ�б�����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		q = (Quanju) getApplicationContext();// ��ȡ���б�����
		q.Init();// ��ʼ����
		// ������ʾ���¼�

		// �ȵ��Ƽ���̬����
		this.addview();

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
	
	

	// *********��ť�¼��뷽��******************

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
		cycleViewPager.setWheel(true);

		// �����ֲ�ʱ�䣬Ĭ��5000ms
		cycleViewPager.setTime(2000);
		// ����Բ��ָʾͼ���������ʾ��Ĭ�Ͽ���
		// cycleViewPager.setIndicatorCenter();
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

	// �����ͼ
	private void addview() {
		//���������λ
		ll = (LinearLayout) findViewById(R.id.fujin_btnlist_tl);
		//��̬��������
		this.addTwoClassify();
	}

	// �������GridView������ӵ�����
	private void addTwoClassify() {
		// ����GridView����
		gridView = new MyGridView(this);// ע������ʹ�õ���MyGridView,���ʹ��GridView�Ļ���ֻ����ʾһ�ж�һ�㣬�ڶ�����ʾ����ȫ��ʹ��MyGridView�Ļ����ܹ���ȫ��ʾ������commend
		gridView.setNumColumns(2);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.setSelector(R.color.selectorColor);
		// ���gridView�������¼�
		setSimple();
		// ���GirdView������
		ll.addView(gridView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}
	/**
	 * ���������ã�GridView��SimpleAdatper���ʵ���б��������--�����¼�
	 */
	public void setSimple() {
		// ģ����ѯ listת��ΪHashMap//��Ҫ��static
		data_list = Goods.getListToHashMap(Goods.selectGoodsByTop(q.GoodsList,
				4));
		SimpleAdapter adapter = new SimpleAdapter // ����SimpleAdapter������
		(MainActivity.this, // ��ǰ��
				data_list, // ѡ����������
				R.layout.find_list_item, // ������ƥ��Ĳ���
				new String[] { "Image", "Intro", "UintName", "PriceStr" }, // �ַ������飬����Ų�������
				new int[] { R.id.image_show, R.id.lable_Intro, R.id.lable_show,
						R.id.price_show } // int���飬��������ݵĿؼ�id��λ��Ҫ�������һһ��Ӧ��
		)
		/*
		 * { //SimpleAdapterÿ����¼���¼�
		 * 
		 * @Override public View getView(final int position, View convertView,
		 * ViewGroup parent) { // TODO Auto-generated method stub View view =
		 * super.getView(position, convertView, parent); Button bt1 =
		 * (Button)view.findViewById(R.id.button1); // ��view��button1 final
		 * TextView tv = (TextView)view.findViewById(R.id.text2); //
		 * ��view��button1 bt1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { if (position==0) {
		 * Toast.makeText(getApplicationContext(), "��һ�в���Ŀ��һ", 1) .show(); } if
		 * (position==1) { Toast.makeText(getApplicationContext(), tv.getText(),
		 * 1) .show(); tv.setText("������"); }
		 * 
		 * } }); return view;
		 * 
		 * }
		 * 
		 * }
		 */;
		// gridView = (GridView) findViewById(R.id.gview_index2);//
		// ��ListView�ؼ�new
		gridView.setAdapter(adapter); // �����������ø�ListView�ؼ�
		// ����ѡ���� ListViewÿ�еĵ����¼�
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "��ѡ����" + position, 1)
						.show();

			}
		});

	}

}
