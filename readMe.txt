   ����
   |---find_list.xml   ������Ϣ���� 
   |---activity_main.xml ��ҳ
   |---activity_main2.xml �Ƽ���ҳ
   |---activity_main3.xml ���ﳵ
   |---activity_main4.xml �ҵ�
   
����
���
   |---banner.xml �ֲ�ͼ���
   |---find.xml ���������
   |---find_list2.xml �ȵ��Ƽ����
   |---lable.xml ��ǩ1 ���
   |---lable2.xml ��ǩ2 ���
   |---show_com_list.xml ��ʾ������Ʒ���
    
    
    
    
    
    
    
    
    
    
    
    ����222
    
    
    // ��������
    android:ellipsize="end"
	android:singleLine="true" 
	
	// ���Զ���ȡ����
	android:focusable="true" 
    android:focusableInTouchMode="true" 
    
    // GridView
    1.android:numColumns=��auto_fit��   //GridView����������Ϊ�Զ�
	2.android:columnWidth=��90dp "       //ÿ�еĿ�ȣ�Ҳ����Item�Ŀ��
	3.android:stretchMode=��columnWidth"//�������п��Сͬ��
	4.android:verticalSpacing=��10dp��          //����֮��ı߾�
	5.android:horizontalSpacing=��10dp��      //����֮��ı߾� 
	6.android:cacheColorHint="#00000000" //ȥ���϶�ʱĬ�ϵĺ�ɫ����
	7.android:listSelector="#00000000"        //ȥ��ѡ��ʱ�Ļ�ɫ��ɫ
	8.android:scrollbars="none"                   //����GridView�Ĺ�����
	9.android:fadeScrollbars="true"             //����Ϊtrue�Ϳ���ʵ�ֹ��������Զ����غ���ʾ
	10.android:fastScrollEnabled="true"      //GridView���ֿ��ٹ����İ�ť(���ٹ���4ҳ�Ż���ʾ)
	11.android:fadingEdge="none"                //GridView˥��(��ȥ)��Ե��ɫΪ�գ�ȱʡֵ��vertical��(�������Ϊ���±�Ե����ʾɫ)
	12.android:fadingEdgeLength="10dip"   //�����˥��(��ȥ)��Ե�ĳ���
	13.android:stackFromBottom="true"       //����Ϊtrueʱ�������õ��б�ͻ���ʾ���б��������
	14.android:transcriptMode="alwaysScroll" //���㶯̬�������ʱ���б��Զ����¹������µ���Ŀ�����Զ����������ӷ�Χ��
	15.android:drawSelectorOnTop="false"  //���ĳ����¼���ţ���ɫ���ڼ�¼�ĺ����Ϊ����ɫ,���ݵ����ֿɼ�(ȱʡΪfalse)

