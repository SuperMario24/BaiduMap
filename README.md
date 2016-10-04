# BaiduMap

[显示地图开发步骤]：

1.通过eclipse-->window-->preferences-->android-->build-->SHA1 申请百度地图的key
2.拷贝百度地图sdk  armeabi文件夹和BaiduLBS_Android.jar包
3.一定要在Application里，初始化地图
		SDKInitializer.initialize(this);
4.菜单文件：一推权限
        <meta-data
            android:name="com.baidu.lbsapi.API_KEY"
            android:value="AwdHubn7K5ui8H6RYvDEnHTZ8YyY3g5E" />//申请到的key

        <service
            android:name="com.baidu.location.f"
            android:enabled="true"
            android:process=":remote" >
        </service>
