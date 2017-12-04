package com.example.shopping.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//�û���
public class User {
	int ID;
	int Sort;
	String LoginName;
	String Password;
	String Name;
	int Sex;
	String Image;// ͷ��ͼƬ
	String RegisterTime;// ע��ʱ��
	String Birthday;// ����
	String Site;// �ջ���ַ
	
	public User() {
	}

	public User(int iD, int sort,String loginName, String password,
			String name, int sex, String image, String registerTime,
			String birthday, String site) {
		super();
		LoginName = loginName;
		Password = password;
		ID = iD;
		Sort = sort;
		Name = name;
		Sex = sex;
		Image = image;
		RegisterTime = registerTime;
		Birthday = birthday;
		Site = site;
	}



	// ***************��������***************************

	// ��ѯ������¼
	public User selectUserByID(List<User> list, int id) {
		User l = new User();
		for (User user : list) {
			if (user.ID == id) {
				l = user;
				break;
			}
		}
		return l;
	}

	// ����ͨ����������
	public List<User> sortUserListBySort(List<User> list) {
		Comparator<User> comparator = new Comparator<User>() {
			public int compare(User s1, User s2) {
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
	public boolean deleteUserByID(List<User> list, int id) {
		boolean b = false;
		User l = new User();
		for (User user : list) {
			if (user.ID == id) {
				l = user;
				b = true;
				break;
			}
		}
		list.remove(l);
		return b;
	}

	// �޸�һ����¼ͨ��id
	public boolean updateUserByID(List<User> list, int id, String name) {
		boolean b = false;
		for (User user : list) {
			if (user.ID == id) {
				user.Name = name;
				b = true;
				break;
			}
		}
		return b;
	}

}
