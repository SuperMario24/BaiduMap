package my.study.baidumap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private MapView mapView;
	//管理地图
	private BaiduMap baiduMap;
	
	//定位
	private LocationClient locationClient;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		initViews();
		
		//让框架中的接口指向实现类，实现实现类
		MyBDLocationListener listener = new MyBDLocationListener();
		//---定位到初始坐标
		locationClient = new LocationClient(this);
		locationClient.registerLocationListener(listener);
		
		
		LocationClientOption option = new LocationClientOption();
		//打开GPS
		option.setOpenGps(true);
		//设置坐标类型
		option.setCoorType("bd09ll");
		//设置每隔2秒得一下坐标，少于1000 只得1次
		option.setScanSpan(1);
		
		locationClient.setLocOption(option);

		
		locationClient.start();
		
		
		
		
	}
	
	/**
	 * 使用框架的人写实现类实现BDLocationListener接口
	 */
	class MyBDLocationListener implements BDLocationListener{

		/**
		 * 接口回调
		 */
		@Override
		public void onReceiveLocation(BDLocation arg0) {
			
			//纬度
			double latitude = arg0.getLatitude();
			//经度
			double longitude = arg0.getLongitude();
			Log.i("info", "纬度="+latitude+",经度="+longitude);

			//移动地图
			//--status状态
			//--坐标点
			LatLng currentLocation = new LatLng(latitude, longitude);
			//地图缩放的级别4-17 17地图显示的详细
			int zoom=17;
			//更新当前坐标点 且以zoom=17显示
			MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(currentLocation,zoom);
			//以动画的方式移动地图
			baiduMap.animateMapStatus(update);
			
			//添加图片			
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(currentLocation);
			//所在当前坐标显示的图片(这里为默认机器人)
			BitmapDescriptor bm = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
			markerOptions.icon(bm);
			baiduMap.addOverlay(markerOptions);
			
			

		}
		
	}
	
	
	
	
	
	/**
	 * 初始化控件
	 */
	private void initViews() {
		mapView = (MapView) findViewById(R.id.mapView);
		//管理地图
		baiduMap = mapView.getMap();
	}

}
