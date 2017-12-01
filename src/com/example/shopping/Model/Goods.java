package com.example.shopping.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.example.shopping.Common.Common;

//��Ʒ��
public class Goods {
	int ID;
	int Sort;
	String MerchantName; // �̼���
	String Name;// ��Ʒ����
	double Price;// ����
	String Uint;// ��װ��λ
	int ClassifyID;// ����ID
	String Classify;// ������
	String Intro;// ���
	String Image;// ͼƬ

	public Goods() {
	}

	public Goods(int iD, int sort, String merchantName, String name,
			double price, String uint, int classifyID, String classify,
			String intro, String image) {
		ID = iD;
		Sort = sort;
		MerchantName = merchantName;
		Name = name;
		Price = price;
		Uint = uint;
		ClassifyID = classifyID;
		Classify = classify;
		Intro = intro;
		Image = image;
	}

	// ***************��������***************************
	// ���Ӻ���

	// ģ��(��Ʒ���ƻ������\�̼���)��ѯ�б�
	public static List<Goods> selectGoodsByNameOrClass(List<Goods> list, String  findStr) {
		List<Goods> relist=new ArrayList<Goods>();
		for (Goods goods : list) {
			if (goods.Name.contains(findStr)||goods.Classify.contains(findStr)
					||goods.MerchantName.contains(findStr)) {
				relist.add(goods); // ������ݸ�list����
			}
		}
		return relist;
	}

	// ģ����ѯ�б�
		public static ArrayList<HashMap<String, Object>> getListToHashMap(List<Goods> list) {
			ArrayList<HashMap<String, Object>> list2=new ArrayList<HashMap<String,Object>>();
			for (Goods goods : list) {
					// ������ݸ�map����
					HashMap<String, Object> map1 = new HashMap<String, Object>(); // ����map����(���飩��һ��map���϶�ӦListView��һ����
					map1.put("MerchantName", goods.MerchantName);
					map1.put("Price", goods.Price);
					map1.put("PriceStr","�� " +goods.Price);
					map1.put("Name", goods.Name);
					map1.put("UintName", goods.Uint+"    "+goods.Name);
					map1.put("Classify", goods.Classify);
					map1.put("Intro", goods.Intro);
					map1.put("Image", new Common().getImageResource(goods.Image));
					// -----��map���ϷŽ�list������---------
					list2.add(map1); // ������ݸ�list����
				}
			return list2;
		}
	// ��ѯ������¼
	public Goods selectGoodsByID(List<Goods> list, int id) {
		Goods l = new Goods();
		for (Goods goods : list) {
			if (goods.ID == id) {
				l = goods;
				break;
			}
		}
		return l;
	}

	// ����ͨ����������
	public List<Goods> sortGoodsListBySort(List<Goods> list) {
		Comparator<Goods> comparator = new Comparator<Goods>() {
			public int compare(Goods s1, Goods s2) {
				// �������
				if (s1.Sort != s2.Sort) {
					return s1.Sort - s2.Sort;
				} else {
					// ������ͬ����������
					if (!s1.Name.equals(s2.Name)) {
						return s1.Name.compareTo(s2.Name);
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
	public boolean deleteGoodsByID(List<Goods> list, int id) {
		boolean b = false;
		Goods l = new Goods();
		for (Goods goods : list) {
			if (goods.ID == id) {
				l = goods;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// �޸�һ����¼ͨ��id
	public boolean updateGoodsByID(List<Goods> list, int id, String name) {
		boolean b = false;
		for (Goods goods : list) {
			if (goods.ID == id) {
				goods.Name = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
