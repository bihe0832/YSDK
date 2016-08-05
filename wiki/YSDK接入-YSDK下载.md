### 1. 下载YSDK

当SDK相关的权限都已经OK了以后，应用开发者可以在此处下载SDK进行集成。

| SDK名称 | 更新日期 | 下载地址 | 版本主要更新 | 
| ------------- |-------------|-------------|-------------|
| `YSDK_Android_1.2.2（开发版）` | 2016-06-23 | [点击下载 YSDK 1.2.2]()| - **切换米大师到集成版，支付不再依赖腾讯充值APP，这部分内容变更较多，需重点关注，建议游戏重新按照[米大师接入配置修改]()修改游戏对应配置。**<BR>- 优化登录回调以及Demo登录回调处理<BR>- 修复手Q登录在某些机型某些场景偶先的crash<BR><BR>**点击[版本历史]()了解更多内容**|
| `YSDK_Android_1.1.1（稳定版）` | 2016-04-08 | [点击下载 YSDK 1.1.1]()| - 添加个人信息，登陆信息柔性策略<BR>- 个人信息接口增加userId字段（参考获取个人信息内容）<BR><BR>**点击[版本历史]()了解更多内容**|


### 2. 包内容说明

YSDK的发布包(zip)主要包含两个重要部分, SDK库和YSDKDemo。前者为YSDK库，后者YSDK接口的使用示例。具体内容如下：

	YSDK_Android_*
	    │
	    ├─── README.html ：关于YSDK的简单介绍说明
	    |
	    ├─── YSDK-AS ：YSDK基于Android Studio的版本库及对应demo
	    │		│
	    │		├─── YSDK_Android_*.aar ：YSDK在基于Android Studio的项目中使用的SDK库
	    │		│
	    │		└─── YSDKDemo ： YSDK基于Android Studio的Demo实现
	    │
	    ├─── YSDK-Eclipse ：YSDK基于ADT(Eclipse)通过Android Library实现的版本库及对应demo
	    │		│
	    │		├─── YSDKLibrary ：YSDK版本库，是一个Android Library项目，游戏可以直接以Android Library的方式引用YSDK。
	    │		│
	    │		└─── YSDKDemo ： YSDK基于ADT(Eclipse)的Demo实现
	    │
	    ├─── YSDKRes ：YSDK相关的配置等文件集合
	    │		│
	    │		├─── debug.keystore ：YSDK的Demo使用的debug模式下的keystore
	    │		│
	    │		├─── proguard-rules.pro ：YSDK的代码混淆文件
	    │		│
	    │		├─── MidasPay.zip ：midas相关资源，用于游戏集成米大师，放在游戏的assets目录下。
	    │		│
	    │		├─── ysdk.keystore ：YSDK的Demo使用的release模式下的keystore
	    │		│
	    │		└─── ysdkconf.ini ：YSDK相关的配置文件，放在游戏的assets目录下。
	    │
	    └─── YSDK_Android_*-debug-ysdktest.apk ：YSDK可运行Demo包

#### 注意事项

1. 虽然版本包里面提供了YSDK的debug和release的keystore，但是目前YSDK的Demo仅使用了debug.keystore，因此游戏在运行YSDK的demo时请使用debug.keystore。
- 使用ADT(Eclipse)进行开发的项目，建议使用Android Library的方式引用YSDK。
- YSDK 从1.2.0版本开始引入资源文件、同时调整优化了错误码，建议使用1.2.0以下版本的游戏在升级SDK版本时要重点关注。
- YSDK 从1.2.2版本开始使用Midas集成版，该版本不再需要腾讯充值APK，不过继承中增加了资源文件，游戏升级时需要注意。
