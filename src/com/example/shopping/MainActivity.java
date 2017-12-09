package com.example.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
import com.example.shopping.Model.ShoppingCar;
import com.stevenhu.android.phone.bean.ADInfo;

public class MainActivity extends Activity implements OnRefreshListener{

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
	private int[] imageUrls;

	private ImageView imagv_1, imagv_2, imagv_3, imagv_4;
	private TextView textV_1, textV_2, textV_3, textV_4;
	// ***********�Զ������********
	Quanju q; // ����ȫ����
	private GridView gridView;
	private LinearLayout ll;
	ArrayList<HashMap<String, Object>> data_list;// ��ȡ�б�����
	private SwipeRefreshLayout refresh_layout = null;//ˢ�¿ؼ�
	private static final int REFRESH_COMPLETE=200;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		q = (Quanju) getApplicationContext();// ��ȡ���б�����
		q.Init();// ��ʼ����
		imageUrls=q.bannerImageUrls;
		//��������
		q.currentUser=q.UserList.get(0);
		// �ȵ��Ƽ���̬����
		this.addGridView();
		// ҳ��ײ���������ת����
		routerPageFun();
		// ��ҳ�����¼���ʼ������
		btFindGo();
		// ��ʼ��banner�ֲ�ͼ
		initialize();
		//ˢ�²���
		refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
		refresh_layout.setColorScheme(R.color.green, R.color.gray, R.color.blue_50, R.color.light_white);//�����ܶ�����ɫֵ
		refresh_layout.setOnRefreshListener(this);//���������ļ���
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
	private void btFindGo() {
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

	// ��ת����ҳ��----һ����Ҫ˽��,��������ҵ�
	public void gotoFindIndex(View view) {
		String findEditStr = view.getTag().toString();// ͨ��tagȡֵ
		// ��ת���ڶ���ҳ��
		intent = new Intent(MainActivity.this, Find_index.class);
		intent.putExtra("findEditStr", findEditStr); // �����ַ�������
		startActivity(intent);
	}
    //�ֲ�ͼ��ʼ��
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
		imageView3.setBackgroundResource(imageUrls[0]);
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
					    refresh_layout.setEnabled(false);//����ˢ����ͼ����
						mScrollView.requestDisallowInterceptTouchEvent(true);
					}
				}
				if (action == MotionEvent.ACTION_UP) {
					// �û�̧����ָ���ָ�������״̬
					mScrollView.requestDisallowInterceptTouchEvent(false);
					refresh_layout.setEnabled(true);//����ˢ����ͼ����
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
				Toast.makeText(MainActivity.this,
						"position-->" + info.getContent(), Toast.LENGTH_SHORT)
						.show();
			}

		}

	};

	// �����ͼ
	private void addGridView() {
		// ���������λ
		ll = (LinearLayout) findViewById(R.id.fujin_btnlist_tl);
		ll.removeAllViews();//��ղ���
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
						intent = new Intent(MainActivity.this,
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

	   @Override
			public void onRefresh() {
				MyHadler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 1*1000);
				addGridView();
				Toast.makeText(MainActivity.this, "����ˢ��", Toast.LENGTH_LONG).show();
				
			}
		 
			private Handler MyHadler =new Handler(){
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case REFRESH_COMPLETE:
						Toast.makeText(MainActivity.this, "ˢ�����", Toast.LENGTH_LONG).show();
						refresh_layout.setRefreshing(false);
						break;
					default:
						break;
					}
					
				};
			};
}
