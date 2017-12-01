package com.example.shopping.Common;


import java.lang.reflect.Field;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.shopping.R;

//����������
public class Common extends Activity {
	/**
	 * ��ȡͼƬ���ƻ�ȡͼƬ����Դid�ķ���
	 * 
	 * @param imageName
	 * @return
	 */
	public static int getImageResource(String imageName) {
		Class drawable = R.drawable.class;
		Field field = null;
		int r_id;
		try {
			field = drawable.getField("a1");
			field = drawable.getField(imageName);
			r_id = field.getInt(field.getName());
		} catch (Exception e) {
			r_id = R.drawable.a0;
			Log.e("ERROR", "PICTURE NOT��FOUND��");
		}
		return r_id;
	}

	/**
	 * ����ͼƬ�����ƻ�ȡ��Ӧ����Դid
	 * 
	 * @param resourceName
	 * @return
	 */
	public int getDrawResourceID(String resourceName) {
		ApplicationInfo appInfo = getApplicationInfo();
		return getResources().getIdentifier(resourceName, "drawable",
				appInfo.packageName);
	}
}
