package my.study.baidumap;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		
		
		//��ʼ����ͼ
		SDKInitializer.initialize(this);
	}
	
}
