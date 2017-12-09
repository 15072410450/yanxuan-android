package com.example.shopping;

import com.example.shopping.Model.Quanju;
import com.example.shopping.Model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	private EditText userName,userPas;
	private TextView login_go;
	private User user = new User();
	private Intent intent;
	Quanju q; // ����ȫ����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// ��ȡ���б�����
		q = (Quanju) getApplicationContext();
		// ��¼�ж�
		goLoginFun();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void goLoginFun() {
		userName = (EditText) Login.this.findViewById(R.id.userName);
		userPas = (EditText) Login.this.findViewById(R.id.userPas);
		login_go = (TextView) Login.this.findViewById(R.id.login_go);
		
		login_go.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = userName.getText().toString().trim();
				String pas = userPas.getText().toString().trim();
				
				// TODO Auto-generated method stub
				// �û���������Ϊ��ʱ
				if(name.equals("") || pas.equals("")) {
					Toast.makeText(Login.this, "�û��������벻��Ϊ��", 1).show();
				} else if (!user.getName().equals(name) || !user.getPassword().equals(pas)) {
//					�û������벻��ȷ
					Toast.makeText(Login.this, "�û��������벻��ȷ", 1).show();
				} else {
					// ��ʼ���û���Ϣ
					q.currentUser = new User(1, 1,"admin","123", "admin", 1, "header_pic.png", "2017-11-29",
							"1995-10-8", "�����б��������������ֵ��ӿƼ���ѧ");
					Toast.makeText(getApplicationContext(), "��¼�ɹ�", 1).show();
					intent = new Intent();
					intent.setClass(Login.this, MainActivity3.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

}
