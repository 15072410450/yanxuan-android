package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class Quanju extends Application {

	// �����
	public List<Classify> ClassifyList = new ArrayList<Classify>();
	// ��Ʒ��
	public List<Goods> GoodsList = new ArrayList<Goods>();
	// ���ﳵ��
	public List<ShoppingCar> ShoppingCarList = new ArrayList<ShoppingCar>();
	// �û���
	public List<User> UserList = new ArrayList<User>();

	// ��ʼ�����ݿ�
	public void Init() {
		//������ʼ��
		if (ClassifyList.size()<=0) {
			String[] ArrayClassify = { "����ϵ��ز�", "����������ʳ", "�����Ⱦ����ʳ", "���Դ�Խ����ʳ" };
			for (int i = 0; i < ArrayClassify.length; i++) {
				ClassifyList.add(new Classify(i, i, ArrayClassify[i]));
			}
		}
		//��Ʒ���ʼ��
		if (GoodsList.size()<=0) {
			String[] merchantNameList = { "�Ĵ�����ʳƷ", "������ʳ԰ʳƷ���޹�˾", "��������������ҵ",
					"���������̲����޹�˾", "�Ϻ��󶫻������޹�˾", "����������ʳƷ�������޹�˾",
					"�����ع�����ó�������ι�˾", "����ͨɽ������ҵ", "��ڰ�װ�������޹�˾", "���������ľ��Ʒ��" };
			String[] nameList = { "������", "����", "ţ����",
					"���ɰ�", "������", "������",
					"ţ���", "ţ����", "������", "ţ������" };
			double [] priceList={12.1,1.3,23.1,1.3,3.8,
					12.1,1.3,23.1,1.3,3.8};
			String[] uintList = { "��", "��", "��",
					"��", "��", "��",
					"��", "��", "��", "��" };
			int [] classifyIDList={1,1,1,1,1,
					1,1,1,1,1};
			String[] classifyList = { "����������ʳ", "����������ʳ", "����������ʳ",
					"����������ʳ", "�����Ⱦ����ʳ", "�����Ⱦ����ʳ",
					"�����Ⱦ����ʳ", "�����Ⱦ����ʳ", "����������ʳ", "����������ʳ" };
			
			String[] introList = { "����������ʳ1", "����������ʳ2", "����������ʳ3",
					"����������ʳ4", "�����Ⱦ����ʳ5", "�����Ⱦ����ʳ6",
					"�����Ⱦ����ʳ7", "�����Ⱦ����ʳ8", "����������ʳ9", "����������ʳ10" };
			String[] imageList = { "a1.png", "a2.png", "a3.png",
					"a4.png", "a5.png", "a6.png",
					"a7.png", "a8.png", "a9.png", "a10.png" };
			/*
			 * iD, int sort, String merchantName, String name, double price,
			 * String uint, int classifyID, String classify, String intro,
			 * String image
			 */
			for (int i = 0; i < merchantNameList.length; i++) {
            	GoodsList.add(new Goods(i, i, merchantNameList[i],
            			nameList[i], priceList[i],
            			uintList[i], classifyIDList[i], 
            			classifyList[i], introList[i],
            			imageList[i]));
			}
		}
		//�û����ʼ��
		if (UserList.size()<=0) {
			/*int iD, int sort, String name, int sex, String image,
			String registerTime, String birthday, String site
			*/
			
			UserList.add(new User(1, 1,"admin",
        			1, "a1.png",
        			"2017-11-29", "1995-10-8", 
        			"�����б��������������ֵ��ӿƼ���ѧ"));
		}

	}

	
}
