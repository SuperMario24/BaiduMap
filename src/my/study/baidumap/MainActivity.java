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
	//�����ͼ
	private BaiduMap baiduMap;
	
	//��λ
	private LocationClient locationClient;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		initViews();
		
		//�ÿ���еĽӿ�ָ��ʵ���࣬ʵ��ʵ����
		MyBDLocationListener listener = new MyBDLocationListener();
		//---��λ����ʼ����
		locationClient = new LocationClient(this);
		locationClient.registerLocationListener(listener);
		
		
		LocationClientOption option = new LocationClientOption();
		//��GPS
		option.setOpenGps(true);
		//������������
		option.setCoorType("bd09ll");
		//����ÿ��2���һ�����꣬����1000 ֻ��1��
		option.setScanSpan(1);
		
		locationClient.setLocOption(option);

		
		locationClient.start();
		
		
		
		
	}
	
	/**
	 * ʹ�ÿ�ܵ���дʵ����ʵ��BDLocationListener�ӿ�
	 */
	class MyBDLocationListener implements BDLocationListener{

		/**
		 * �ӿڻص�
		 */
		@Override
		public void onReceiveLocation(BDLocation arg0) {
			
			//γ��
			double latitude = arg0.getLatitude();
			//����
			double longitude = arg0.getLongitude();
			Log.i("info", "γ��="+latitude+",����="+longitude);

			//�ƶ���ͼ
			//--status״̬
			//--�����
			LatLng currentLocation = new LatLng(latitude, longitude);
			//��ͼ���ŵļ���4-17 17��ͼ��ʾ����ϸ
			int zoom=17;
			//���µ�ǰ����� ����zoom=17��ʾ
			MapStatusUpdate update = MapStatusUpdateFactory.newLatLngZoom(currentLocation,zoom);
			//�Զ����ķ�ʽ�ƶ���ͼ
			baiduMap.animateMapStatus(update);
			
			//���ͼƬ			
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(currentLocation);
			//���ڵ�ǰ������ʾ��ͼƬ(����ΪĬ�ϻ�����)
			BitmapDescriptor bm = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
			markerOptions.icon(bm);
			baiduMap.addOverlay(markerOptions);
			
			

		}
		
	}
	
	
	
	
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initViews() {
		mapView = (MapView) findViewById(R.id.mapView);
		//�����ͼ
		baiduMap = mapView.getMap();
	}

}
