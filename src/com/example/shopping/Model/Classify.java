package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//�����
public class Classify {
	int ID;
	int Sort;
	String Name;// ��������
	
	public Classify() {
	}
	public Classify(int iD, int sort, String name) {
		super();
		ID = iD;
		Sort = sort;
		Name = name;
	}
	
	// ***************��������***************************
		// ���Ӻ���

		// ��ѯ������¼
		public Classify selectClassifyByID(List<Classify> list, int id) {
			Classify l = new Classify();
			for (Classify classify : list) {
				if (classify.ID == id) {
					l = classify;
					break;
				}
			}
			return l;
		}
		
		// ����ͨ����������
		public List<Classify> sortClassifyListBySort(List<Classify> list) {
			Comparator<Classify> comparator = new Comparator<Classify>() {
				public int compare(Classify s1, Classify s2) {
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
		public boolean deleteClassifyByID(List<Classify> list, int id) {
			boolean b = false;
			Classify l = new Classify();
			for (Classify classify : list) {
				if (classify.ID == id) {
					l = classify;
					b = true;
					break;
				}
			}
			list.remove(l);
			return b;
		}
	 
		//�޸�һ����¼ͨ��id
		public boolean updateClassifyByID(List<Classify> list, int id, String name) {
			boolean b = false;
			for (Classify classify : list) {
				if (classify.ID == id) {
					classify.Name = name;
					b = true;
					break;
				}
			}
			return b;
		}
		
	
	
}
