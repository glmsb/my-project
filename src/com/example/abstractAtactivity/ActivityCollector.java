package com.example.abstractAtactivity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;	
/**���������
	 * */
public class ActivityCollector {
	
	public static List<Activity> activitys=new ArrayList<Activity>();
	
	public  static void addActivity(Activity activity){
		activitys.add(activity);
	}
	public static  void removeActivity(Activity activity){
		activitys.remove(activity);
	}
	
	/**����������ʲô�ط��˳�����ֻ��Ҫ���� ActivityCollector.finishAll()����
	�Ϳ�����*/
	public  static void finishAll(){
		for(Activity activity :activitys){
			if(!activity.isFinishing()){//�жϵ�ǰ��Ƿ��ѽ���
				activity.finish();
			}
		}
	}
}
