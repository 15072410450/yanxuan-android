package com.example.shopping;

import com.example.shopping.Model.Quanju;
import com.example.shopping.Model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends Activity {
	private LinearLayout 
	layout_menu_1,
	layout_menu_2,
	layout_menu_3,
	layout_menu_4;
	Intent intent;
	private ImageView imagv_1,imagv_2,imagv_3,imagv_4;
	private TextView textV_1,textV_2,textV_3,textV_4, user_msg_name1, user_msg_name2, login_state;
	private ImageView user_msg_header;
	private User user = new User();
	
	Quanju q; // ����ȫ����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity4);
		// �ײ�������תҳ�淽��
		routerPageFun();
		// ��ȡ���б�����
		q = (Quanju) getApplicationContext();
		
		changeUserInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity4, menu);
		return true;
	}
	
	// �ײ�������תҳ�淽��
	public void routerPageFun() {
		layout_menu_1=(LinearLayout)findViewById(R.id.layout_menu_1);
        layout_menu_2=(LinearLayout)findViewById(R.id.layout_menu_2);
        layout_menu_3=(LinearLayout)findViewById(R.id.layout_menu_3);
        layout_menu_4=(LinearLayout)findViewById(R.id.layout_menu_4);
        imagv_4=(ImageView)findViewById(R.id.imagev_4);
        imagv_4.setImageResource(R.drawable.center4_2);
        
        textV_4=(TextView)findViewById(R.id.textV_4);
        textV_4.setTextColor(getResources().getColor(R.color.text_bg));
        
        layout_menu_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity4.this,MainActivity2.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			}
		});
        layout_menu_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity4.this,MainActivity3.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			}
		});
        layout_menu_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent = new Intent();
			    intent.setClass(MainActivity4.this,MainActivity.class);
			    startActivity(intent);
			    overridePendingTransition(0,0); 
			    finish();
			}
		});
		
	}

	// �ı��û���Ϣ
	public void changeUserInfo() {
		
		user_msg_name1 = (TextView) MainActivity4.this.findViewById(R.id.user_msg_name1);
		user_msg_name2 = (TextView) MainActivity4.this.findViewById(R.id.user_msg_name2);
		login_state = (TextView) MainActivity4.this.findViewById(R.id.login_state);
		user_msg_header= (ImageView) MainActivity4.this.findViewById(R.id.user_msg_header);
		
		// �û�δ��¼ʱ
		if (q.currentUser == null) {
			user_msg_name1.setText("���¼");
			user_msg_name2.setText("��¼�������");
			login_state.setText("��¼    >");
			user_msg_header.setImageResource(R.drawable.header_pic2);
			
			login_state.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),
							"���¼", 1).show();
					intent = new Intent();
					intent.setClass(MainActivity4.this, Login.class);
					startActivity(intent);
				}
			});
			
		} else {
			user_msg_name1.setText(user.getName().toString());
			user_msg_name2.setText("����Ҳ����");
			login_state.setText("ǩ��    >");
			user_msg_header.setImageResource(R.drawable.header_pic);
		}
		
		
	}

}
