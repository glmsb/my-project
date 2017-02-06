package com.example.abstractAtactivity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**找到每个界面对应的活动*/
		Log.d("BaseActivity",getClass().getSimpleName());//获取活动名
		
		//将当前正在创建的活动添加到活动管理器里
		ActivityCollector.addActivity(this);
	}
	
	//重写 onDestroy()方法
	protected void onDestory(){
		super.onDestroy();
		
		//将一个马上要销毁的活动从活动管理器里移除
		ActivityCollector.removeActivity(this);
	}

}
