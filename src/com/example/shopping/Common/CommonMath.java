package com.example.shopping.Common;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.Log;

import com.example.shopping.R;

//����������
public class CommonMath {
	/**
	 * ��ȡͼƬ���ƻ�ȡͼƬ����Դid�ķ���
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResourceID(String imageName) {
		Class drawable = R.drawable.class;
		int r_id;
		Field field = null;
		try {
			field = drawable.getField(imageName);
			String a = field.getName();
			r_id = field.getInt(a);
		} catch (Exception e) {
			r_id = R.drawable.a0;
			Log.e("ERROR", "PICTURE NOT��FOUND��");
		}
		return r_id;
	}
	
	

}
