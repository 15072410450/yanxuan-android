package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.androiddevelop.cycleviewpager.lib.CycleViewPager;
import cn.androiddevelop.cycleviewpager.lib.CycleViewPager.ImageCycleViewListener;

import com.example.shopping.Common.MyGridView;
import com.example.shopping.Model.Goods;
import com.example.shopping.Model.Quanju;
import com.stevenhu.android.phone.bean.ADInfo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity {
	private LinearLayout layout_menu_1, layout_menu_2, layout_menu_3,
			layout_menu_4;
	Intent intent;
	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;
	// �ֲ�ͼ������
	private ScrollView mScrollView;
	private float mLastX;
	private ViewPager viewPager;
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private CycleViewPager cycleViewPager;
	// �ֲ�ͼͼƬ
	private int[] imageUrls;

	// ***********�Զ������********
	Quanju q; // ����ȫ����
	private GridView gridView;
	private LinearLayout ll;
	ArrayList<HashMap<String, Object>> data_list;// ��ȡ�б�����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity2);
		q = (Quanju) getApplicationContext();// ��ȡ���б�����
		imageUrls=q.bannerImageUrls2;
		// ҳ��ײ���������ת����
		routerPageFun();
		// �����Ƽ���̬����
		this.addGridView((LinearLayout) MainActivity2.this
				.findViewById(R.id.fujin_btnlist_tl),Goods.selectGoodsByTop(Goods.sortGoodsListBySort(q.GoodsList, 0),
						4));
		// ��ɫ�Ƽ���̬����
		this.addGridView((LinearLayout) MainActivity2.this
				.findViewById(R.id.fujin_btnlist_t2),Goods.selectGoodsByTop(Goods.sortGoodsListBySort(q.GoodsList, 1),
						4));
		// ��ʼ��banner�ֲ�ͼ
		initialize();
		TextView tv=(TextView)MainActivity2.this
			.findViewById(R.id.textView_title);
		tv.setText("�ȼ�");
		//λ�ö�λ
		mScrollView.smoothScrollTo(0,20);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

	// ҳ��ײ���������ת����
	public void routerPageFun() {
		layout_menu_1 = (LinearLayout) findViewById(R.id.layout_menu_1);
		layout_menu_2 = (LinearLayout) findViewById(R.id.layout_menu_2);
		layout_menu_3 = (LinearLayout) findViewById(R.id.layout_menu_3);
		layout_menu_4 = (LinearLayout) findViewById(R.id.layout_menu_4);

		imagv_2 = (ImageView) findViewById(R.id.imagev_2);
		imagv_2.setImageResource(R.drawable.center2_2);

		textV_2 = (TextView) findViewById(R.id.textV_2);
		textV_2.setTextColor(getResources().getColor(R.color.text_bg));

		layout_menu_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity3.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
		layout_menu_4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
				intent.setClass(MainActivity2.this, MainActivity4.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
				finish();
			}
		});
	}

	// ��ת����ҳ��----һ����Ҫ˽��,��������ҵ�
	public void gotoFindIndex(View view) {
		String findEditStr = view.getTag().toString();// ͨ��tagȡֵ
		// ��ת���ڶ���ҳ��
		intent = new Intent(MainActivity2.this, Find_index.class);
		intent.putExtra("findEditStr", findEditStr); // �����ַ�������
		startActivity(intent);
	}

	// �ֲ�ͼ��ʼ��
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
		// ������ͼ��ȡ
		mScrollView = (ScrollView) findViewById(R.id.scrollView1);
		// �����ֲ���ͼ-�����ScrollView��ͻ
		viewPager = cycleViewPager.getViewPager();
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();

				if (action == MotionEvent.ACTION_DOWN) {
					// ��¼�����ViewPagerʱ����ָ��X����
					mLastX = event.getX();
				}
				if (action == MotionEvent.ACTION_MOVE) {
					// ������ֵ
					if (Math.abs(event.getX() - mLastX) > 60f) {
						// mRefreshLayout.setEnabled(false);//����ˢ����ͼ����
						mScrollView.requestDisallowInterceptTouchEvent(true);
					}
				}
				if (action == MotionEvent.ACTION_UP) {
					// �û�̧����ָ���ָ�������״̬
					mScrollView.requestDisallowInterceptTouchEvent(false);
					// mRefreshLayout.setEnabled(true);//����ˢ����ͼ����
				}
				return false;
			}
		});

	}

	// ���ͼƬ֮��ļ�������
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(ADInfo info, int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				position = position - 1;
				Toast.makeText(MainActivity2.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}

		}

	};

	// �����ͼ
	private void addGridView(LinearLayout addll,List<Goods> list) {
		// ���������λ
		ll = addll;
		// ����GridView����
		gridView = new MyGridView(this);// ע������ʹ�õ���MyGridView,���ʹ��GridView�Ļ���ֻ����ʾһ�ж�һ�㣬�ڶ�����ʾ����ȫ��ʹ��MyGridView�Ļ����ܹ���ȫ��ʾ������commend
		gridView.setNumColumns(2);
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridView.setSelector(R.color.selectorColor);
		// ���gridView�������¼�
		setSimple(list);
		// ���GirdView������
		ll.addView(gridView, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * ���������ã�GridView��SimpleAdatper���ʵ���б��������--�����¼�
	 */
	public void setSimple(List<Goods> list) {
		// ģ����ѯ listת��ΪHashMap//��Ҫ��static
		data_list = Goods.getListToHashMap(list);
		SimpleAdapter adapter = new SimpleAdapter // ����SimpleAdapter������
		(MainActivity2.this, // ��ǰ��
				data_list, // ѡ����������
				R.layout.find_list_item, // ������ƥ��Ĳ���
				new String[] { "GoodsID", "Image", "Intro", "UintName",
						"PriceStr" }, // �ַ������飬����Ų�������
				new int[] { R.id.lable_GoodsID, R.id.image_show,
						R.id.lable_Intro, R.id.lable_show, R.id.price_show } // int���飬��������ݵĿؼ�id��λ��Ҫ�������һһ��Ӧ��
		) {
			// SimpleAdapterÿ����¼���¼�
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView lableGoods = (TextView) view
						.findViewById(R.id.lable_GoodsID);
				final int goodsID = Integer.parseInt(lableGoods.getText()
						.toString());// idתint ��Ҫ���ձ���
				LinearLayout lineargoods = (LinearLayout) view
						.findViewById(R.id.linear_goods);
				lineargoods.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						intent = new Intent(MainActivity2.this,
								Goods_more_index.class);
						intent.putExtra("goodsID", goodsID); // �����ַ�������
						startActivity(intent);
					}
				});
				return view;
			}
		};

		gridView.setAdapter(adapter); // �����������ø�ListView�ؼ�
		/*
		 * // ����ѡ���� ListViewÿ�еĵ����¼� gridView.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { Toast.makeText(getApplicationContext(),
		 * "��ѡ����" + position, 1) .show();
		 * 
		 * } });
		 */
	}

}