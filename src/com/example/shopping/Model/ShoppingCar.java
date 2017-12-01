package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//���ﳵ��
public class ShoppingCar {
	int ID;
	int Sort;
	String ShoppingCarID;// ��ƷID
	String ShoppingCarName;// ��Ʒ��
	double Price;// ����
	String Uint;// ��װ��λ
	int Num;// ������Ŀ
	String Image;// ����ͼƬ
	String Time;// ����ʱ��

	public ShoppingCar() {
	}

	public ShoppingCar(int iD, int sort, String shoppingCarID, String shoppingCarName,
			double price, String uint, int num, String image, String time) {
		ID = iD;
		Sort = sort;
		ShoppingCarID = shoppingCarID;
		ShoppingCarName = shoppingCarName;
		Price = price;
		Uint = uint;
		Num = num;
		Image = image;
		Time = time;
	}

	// ***************��������***************************

	// ��ѯ������¼
	public ShoppingCar selectShoppingCarByID(List<ShoppingCar> list, int id) {
		ShoppingCar l = new ShoppingCar();
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				l = shoppingCar;
				break;
			}
		}
		return l;
	}

	// ����ͨ����������
	public List<ShoppingCar> sortShoppingCarListBySort(List<ShoppingCar> list) {
		Comparator<ShoppingCar> comparator = new Comparator<ShoppingCar>() {
			public int compare(ShoppingCar s1, ShoppingCar s2) {
				// �������
				if (s1.Sort != s2.Sort) {
					return s1.Sort - s2.Sort;
				} else {
					// ������ͬ����������
					if (!s1.ShoppingCarName.equals(s2.ShoppingCarName)) {
						return s1.ShoppingCarName.compareTo(s2.ShoppingCarName);
					} else {
						// ����Ҳ��ͬ��ѧ������
						return s1.ID - s2.ID;
					}
				}
			}
		};
		// ����ͻ��Զ����ݹ����������
		Collections.sort(list, comparator);
		return list;
	}

	// ɾ��һ����¼
	public boolean deleteShoppingCarByID(List<ShoppingCar> list, int id) {
		boolean b = false;
		ShoppingCar l = new ShoppingCar();
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				l = shoppingCar;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// �޸�һ����¼ͨ��id
	public boolean updateShoppingCarByID(List<ShoppingCar> list, int id, String name) {
		boolean b = false;
		for (ShoppingCar shoppingCar : list) {
			if (shoppingCar.ID == id) {
				shoppingCar.ShoppingCarName = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
