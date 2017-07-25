## 3. YSDK 接入

YSDK 接入模块主要介绍YSDK的接入、更新相关内容

### 3.1 快速接入

这部分引导开发者快速将YSDK继承到自己的工程中。

#### Step1. 引入YSDK库

这里根据开发者使用的IDE工具来分别介绍引入方法。

##### 注意事项:

1. 引入YSDK以后编译可能会发生包冲突(重复)，因为YSDK已经包含了灯塔SDK(beacon-xxx.jar)，buglySDK(bugly-XX.jar)。且上述sdk均是其最新稳定版，游戏如果以前有单独集成这些SDK，请删除之前集成的jar包。
2. **版本包中自带的`YSDKRes/TencentUnipay.apk`不一定为腾讯充值APP的最新版，建议开发者在封版前更新到最新版，（应用宝下载地址为:[http://android.myapp.com/myapp/detail.htm?apkName=com.tencent.unipay](http://android.myapp.com/myapp/detail.htm?apkName=com.tencent.unipay)），非最新版本并不影响用户的支付行为。**

##### Eclipse 工程接入

YSDK 推荐游戏使用Android Library方式接入YSDK，既可以隔绝SDK与游戏代码的耦合，而且方便后续的SDK版本更新。YSDK从1.2.0版本开始支持使用Android Library方式引入YSDK。

- Java接入

	1. 复制YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
	- 在Eclipse中引入YSDKLibrary项目
	- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
	
- C++ 工程接入

	1. 复制YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
	- 在Eclipse中引入YSDKLibrary项目
	- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
	- 复制YSDKLibrary下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录，并添加到 Android.mk。

##### Android Studio工程接入

- Java接入

	1. 复制YSDK-Android-*.aar目录到游戏工程的libs下
	- 在游戏项目的build.gradle中添加对YSDK-Android-*.aar的依赖。
	
- C++ 工程接入

	1. 复制YSDK-Android-*.aar目录到游戏工程的libs下
	- 在游戏项目的build.gradle中中添加对YSDK-Android-*.aar的依赖。
	- 复制YSDKDemo下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录。

#### Step2. YSDK配置修改

##### 配置文件修改

在`YSDKRes/ysdkconf.ini`中有游戏相关的各种配置，游戏需要根据自身情况调整为对应的配置。具体配置内容如下：

- 游戏相关配置，游戏需要把下面的三个配置项改为游戏自己的配置

		;**************游戏相关配置, 游戏需要根据各自情况修改 START **********
		;游戏的QQAPPID
		QQ_APP_ID=1104936***

		;游戏的微信APPID
		WX_APP_ID=wxfcefed3f366f***

		;游戏的OFFER_ID
		OFFER_ID=110493***
		;***************游戏配置项, 游戏需要根据各自情况修改 END **************

- YSDK相关配置项，游戏需要根据各自情况修改

	**YSDK域名**：ysdktest.qq.com为测试环境域名, ysdk.qq.com为正式环境域名；联调阶段游戏需要使用测试环境，发布时需要切换到正式环境

		;************* YSDK相关配置项,游戏需要根据各自情况修改 START **********
		;联调环境
		YSDK_URL=https://ysdktest.qq.com
		;正式环境
		;YSDK_URL=https://ysdk.qq.com
		;************** YSDK相关配置项,游戏需要根据各自情况修改 END **************

**备注：**

1. **为了防止游戏用非正式环境上线, SDK内检测到游戏使用非正式环境时, 会Toast出类似: “You are using http://ysdk****.qq.com” 这样的提示, 游戏切换成正式环境域名以后此提示自动消失。**
	
2. **游戏修改域名的时候只需要将计划使用的域名前的分号删除，注释不需要使用的域名即可。**
##### AndroidMainfest修改

**以下权限是通用的权限声明，需要在application标签外添加（建议参考下载包的Demo修改）**

 	<!-- TODO SDK接入必须权限模块 START -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.RESTART_PACKAGES" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

#### Step3. YSDK初始化

YSDK初始化是使用SDK所提供功能可以执行的前提。游戏在应用启动时LauncherActivity的`onCreate`方法中调用YSDK初始化函数`onCreate(Activity activity)`设置。

**C++类游戏除了在MainActivity的`onCreate`方法中初始化SDK，还要在JNI_OnLoad初始化SDK相关的内容。**

##### 接口声明：

	/**
	 * YSDK的onCreate方法，游戏在主activity的onCreate调用
	 * 
	 * @param activity
	 *            游戏的主activity的Context
	 */
	void onCreate(Activity activity);

##### 接口调用：

- 通用调用

		YSDKApi.onCreate(this);

- C++类游戏JNI_OnLoad的写法
	
		JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
			//TODO GAME C++层初始化, 必须在游戏主Activity的onCreate之前被调用
			YSDKApi::getInstance()->init(vm);
			return JNI_VERSION_1_4;
		}

#### Step4. YSDK全局回调设置

游戏如果没有设置回调会导致游戏无法接收到YSDK的各种回调通知。游戏调用`setUserListener`、`setBuglyListener`方法，可实现全局回调。

**java类游戏在在应用启动时的MainActivity的`onCreate`方法中设置即可，C++类游戏需要在JNI_OnLoad中设置。如果游戏同时设置了C++和java层回调，会优先使用java层的回调，具体回调设置相关的代码，请参考YSDK demo**

##### 接口声明：

	/**
 	 * 设置bugly异常上报相关回调
  	 */
	void setBuglyListener(YSDKBuglyListener* pListener);

	/**
     * 设置用户登录相关回调
     */
	void setUserListener(YSDKUserListener* pListener);

##### java层回调设置：

 	YSDKApi.setUserListener(new YSDKUserListener());
 	YSDKApi.setBuglyListener(new YSDKBuglyListener());

##### C++层回调设置


	JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
		//TODO GAME C++层初始化, 必须在游戏主Activity的onCreate之前被调用
		YSDKApi::getInstance()->init(vm);
		YSDKApi::getInstance()->setBuglyListener(&gTestBuglyListener);
		YSDKApi::getInstance()->setUserListener(&gTestUserListener);
		return JNI_VERSION_1_4;
	}
	
#### Step5. YSDK生命周期设定

**游戏需要在游戏的Launcher Activity和Main Activity的部分声明周期函数中同步调用YSDK相关的声明周期函数，YSDK的部分功能依赖声明周期函数，所以游戏需要要同步调用。缺少调用或者调用错误可能会引起登陆收不到回调，登陆中Crash等问题**

**目前YSDK从1.2.4版本开始Demo为使用多个Activity，游戏可以参照YSDK的Demo代码查看生命周期如何设置。单Activity的游戏可以[点击查看基于YSDK 1.1.1的单Activity的Demo代码](https://github.com/bihe0832/YSDK)**

##### onCreate

游戏需要在**Launcher Activity和Main Activity**的onCreate方法中调用此接口，以实现对单机SDK进行初始化。

- 接口声明：

		void onCreate(Activity activity);

- 接口调用：

		YSDKApi.onCreate(this);

##### onResume

游戏需要在**Main Activity**的onResume方法中调用此接口。

- 接口声明：

		void onResume(Activity activity);

- 接口调用：

		YSDKApi.onResume(this);

##### onPause

游戏需要在**Main Activity**y的onPause方法中调用此接口。

- 接口声明：

		void onPause(Activity activity);

- 接口调用：

		YSDKApi.onPause(this);

##### onStop

游戏需要在**Main Activity**的onStop方法中调用此接口。

- 接口声明：

		void onStop(Activity activity);

- 接口调用：

		YSDKApi.onStop(this);

##### onDestroy

游戏需要在**Main Activity**的onDestroy方法中调用此接口。

- 接口声明：

		void onDestroy(Activity activity);

- 接口调用：

		YSDKApi.onDestroy(this);


##### onRestart

游戏需要在**Main Activity**的onRestart方法中调用此接口。

- 接口声明：

		void onRestart(Activity activity);
		
- 接口调用：

		YSDKApi.onRestart(this);

##### handleIntent

游戏需要在**Launcher Activity**的onNewIntent和onCreate方法中调用此接口。

- 接口声明：

		void handleIntent(Intent intent)

- 接口调用：

	- onCreate:
	
			YSDKApi.handleIntent(this.getIntent());
			
	- onNewIntent

			YSDKApi.handleIntent(intent);	##### onActivityResult游戏需要在**Main Activity**的onActivityResult方法中调用此接口。
- 接口声明：

	    public static void onActivityResult (int requestCode, int resultCode, Intent data)
	    
- 接口调用：

		YSDKApi. onActivityResult(requestCode, resultCode,data);### 3.2 SDK更新

#### Eclipse 工程更新

YSDK 推荐游戏使用Android Library方式接入YSDK，既可以隔绝SDK与游戏代码的耦合，而且方便后续的SDK版本更新。YSDK从1.2.0版本开始支持使用Android Library方式引入YSDK。

- Java接入

	1. 删除游戏目录下的原有YSDKLibrary目录
	2. 复制新版本包中的YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
	- 在Eclipse中重新引入YSDKLibrary项目
	- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
	- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
	- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置
	
- C++ 工程接入

	1. 删除游戏目录下的原有YSDKLibrary目录
	2. 复制新版本包中的YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
	- 在Eclipse中重新引入YSDKLibrary项目
	- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
	- 再次复制YSDKLibrary下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录，并添加到 Android.mk。
	- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
	- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置


##### Android Studio工程更新

- Java接入

	1. 复制YSDK-Android-*.aar目录到游戏工程的libs下，删除原有版本
	- 在游戏项目的build.gradle中更新对YSDK-Android-*.aar的依赖。
	- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
	- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置
	
- C++ 工程接入

	1. 复制YSDK-Android-*.aar目录到游戏工程的libs下
	- 在游戏项目的build.gradle中中添加对YSDK-Android-*.aar的依赖。
	- 复制YSDKDemo下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录。
	- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
	- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置


### 3.3 代码混淆规范

	-keep public class * extends android.app.Activity
	-keep public class * extends android.app.Application
	-keep public class * extends android.app.Service
	-keep public class * extends android.content.BroadcastReceiver
	-keep public class * extends android.content.ContentProvider
	-keep public class * extends android.app.backup.BackupAgentHelper
	-keep public class * extends android.preference.Preference
	-keep public class com.android.vending.licensing.ILicensingService
	
	-keepclasseswithmembernames class * {
	    native <methods>;
	}
	
	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet);
	}
	
	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	}
	
	
	-keepclassmembers enum * {
	    public static **[] values();
	    public static ** valueOf(java.lang.String);
	}
	
	-keep class * implements android.os.Parcelable {
	  public static final android.os.Parcelable$Creator *;
	}
	
	-keepattributes InnerClasses
	
	-keep class com.tencent.bugly.**{*;}
	-keep class com.tencent.stat.**{*;}
	-keep class com.tencent.smtt.**{*;}
	-keep class com.tencent.beacon.**{*;}
	-keep class com.tencent.mm.**{*;}
	-keep class com.tencent.apkupdate.**{*;}
	-keep class com.tencent.tmassistantsdk.**{*;}
	-keep class org.apache.http.** { * ;}
	-keep class com.qq.jce.**{*;}
	-keep class com.qq.taf.**{*;}
	-keep class com.tencent.connect.**{*;}
	-keep class com.tencent.map.**{*;}
	-keep class com.tencent.open.**{*;}
	-keep class com.tencent.qqconnect.**{*;}
	-keep class com.tencent.tauth.**{*;}
	-keep class com.tencent.feedback.**{*;}
	-keep class common.**{*;}
	-keep class exceptionupload.**{*;}
	-keep class mqq.**{*;}
	-keep class qimei.**{*;}
	-keep class strategy.**{*;}
	-keep class userinfo.**{*;}
	-keep class com.pay.**{*;}
	-keep class com.demon.plugin.**{*;}
	-keep class com.tencent.midas.**{*;}
	-keep class oicq.wlogin_sdk.**{*;}
	-keep class com.tencent.ysdk.**{*;}
	-keepclasseswithmembernames class * {
	    native <methods>;
	}
	
	-keep class com.tencent.ysdk.**{*;}		
## YSDK接入验证测试用例

|用例名称|用例描述|前提条件|操作步骤|期待结果|
|:--:|:--|:--|:--|:--|
|未安装微信登录游戏|未安装微信时选择用微信登录游戏|未安装微信客户端|1.启动游戏<BR>2.通过微信登录方式|微信登录入口屏蔽或给出提示|
|微信未登录登陆游戏|微信没有帐号登录时选择用微信登录游戏|1.安装微信后，尚未登录过|1.启动游戏，通过微信方式登录<BR>2.在弹出的微信界面输入微信账号密码<BR>3.在授权页面同意授权|微信登录成功、通过授权，进入游戏|
|微信登录游戏授权时取消|用微信登录游戏在授权时取消授权|无|1.启动游戏，通过微信方式登录<BR>2.在弹出的授权页面点击左上角取消授权|跳转到登录界面，游戏无异常|
|未安装手Q登录游戏|未安装手Q客户端时选择用手Q登录游戏|无|1.启动游戏<BR>2.通过手Q登录方式|1.跳转到web登录页面，提示下载QQ<BR>2.点击返回键，回到游戏登陆界面|
|通过手Q登录游戏|手Q帐号未登录时选择用手Q登录游戏|1.安装手机Q后，尚未登录过|1.启动游戏，通过手Q方式登录<BR>2.在弹出的手Q界面输入QQ账号密码<BR>3.在授权页面同意授权|手Q登录成功、通过授权，进入游戏|
|通过手Q登录游戏|用手Q帐号登录游戏在授权时取消授权|无|1.启动游戏，通过手Q方式登录<BR>2.在弹出的授权页面点击左上角取消授权|跳转到登录界面，游戏无异常|
|自动登录游戏|已经登录的账号再次登录游戏无需重新授权|游戏已登录|1.启动游戏，通过任意登录方式登录<BR>2.直接退出游戏或者从后台杀掉进程<BR>3.再次启动游戏|无需再次登录，直接进入游戏|
|注销游戏登录|用户可以退出当前登录的账号|游戏已登录|1.启动游戏，通过任意登录方式登录<BR>2.点击游戏内注销登录的按钮|用户退出当前登录，回到授权页面|
|账号实名认证|未实名认证的用户登录游戏提示实名认证|游戏未登录，账号未实名|1.启动游戏，使用新申请号码通过任意登录方式登录|登录成功或者弹出实名认证的窗口|
|android5.x系统支持|游戏在android5.x的运行情况|成功安装游戏|点击游戏图标|游戏正常运行，无Crash现象|
|android6.x系统支持|游戏在android6.x的运行情况|成功安装游戏|点击游戏图标|游戏正常运行，无Crash现象|
|64位CPU机型支持|游戏在64位CPU的运行情况|成功安装游戏|点击游戏图标|游戏正常运行，无Crash现象|
|微信/QQ帐号下支付|使用QQ钱包支付|未绑定银行卡|1.购买最低充值额钻石<BR>2.选择银行卡支付<BR>3.输入开通网银功能的银行卡号、密码|1.拉起支付界面QQ钱包后面有原快捷、财付通字样，如果没有联系企业QQ 800013811。<BR>2.支付成功<BR>3.系统发货对应钻石数量。|
|微信/QQ帐号下支付|充值Q点并支付|无|1.选择充值金额<BR>2.选择Q点支付<BR>3.输入QQ号码<BR>4.输入QQ密码|1.支付成功<BR>2.返还正确的钻石数。<BR>3.余额扣除正确||微信/QQ帐号下支付|QQ卡支付|无	|1.选择充值金额<BR>2.选择QQ卡支付<BR>3.输入QQ号码，密码<BR>4.输入正确的卡号密码|1.支付成功<BR>2.返还正确的钻石数。<BR>3.余额扣除正确||微信/QQ帐号下支付|微信支付|已绑定银行卡帐号|1.选择充值金额<BR>2.选择微信支付<BR>3.输入正确支付密码|1.支付成功<BR>2.返还正确的钻石数。<BR>3.余额扣除正确|

备注：目前YSDK关于游戏对于android6.x和64位CPU机型的支持还不是必须支持，但是目前这两类机型目前占比正在不断增加，因此建议游戏开发者尽快支持，后续YSDK会将这两项也纳入审核标准。