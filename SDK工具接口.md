# YSDK 工具接口

## 1. 检查手Q/微信是否安装

调用isPlatformInstalled接口会返回检测的平台是否安装。接口详细说明如下:

### 接口声明

	/**
	 * @param platformType 游戏传入的平台类型, 可能值为: ePlatform_QQ, ePlatform_Weixin
	 * @return 平台的支持情况, false表示平台未安装, true则表示已经安装
	 */
	boolean isPlatformInstalled(ePlatform platform);

### 接口调用

	boolean isInstall = YSDKApi.isPlatformInstalled(ePlatform.QQ);

## 2. 获取手Q/微信版本

调用getPlatformAPPVersion接口会返回当前安装的平台对应的版本。接口详细说明如下:

### 接口声明

	/**
	 * @return APP版本号
	 */
	String getPlatformAppVersion(ePlatform platform);
	
### 接口调用

	YSDKApi.getPlatformAppVersion(ePlatform.QQ);

## 3. 获取YSDK版本

调用getVersion接口会返回YSDK的版本号。接口详细说明如下:

### 接口声明

	/**
	 * 返回YSDK版本号
	 * @return YSDK版本号
	 */
	 String getVersion();

### 接口调用

	String version = YSDKApi.getVersion();
	
## 4. 获取安装渠道

调用getChannelId会返回游戏的安装渠道。接口详细说明如下：

### 接口声明

	/**
	 * @return 安装渠道
	 */
	String getChannelId();
	
### 接口调用

	String channel = YSDKApi.getChannelId();

## 5. 获取注册渠道

调用getRegisterChannelId会返回用户的注册渠道。接口详细说明如下：

### 接口声明
	/**
	 * @return 注册渠道
	 */
	String getRegisterChannelId();

### 接口调用
	/**
	 * @return 注册渠道
	 */
	String channel = YSDKApi.getRegisterChannelId()
	
## 6. 获取pf

调用getPf会返回当前登录用户的pf。接口详细说明如下：

### 接口声明

	String getPf();

### 接口调用
	
	String pf = YSDKApi.getPf();
	
## 7. 获取pfkey

调用getPfKey会返回当前登录用户的pfkey。接口详细说明如下：

### 接口声明

	String getPfKey();

### 接口调用
	
	String pfkey = YSDKApi.getPfKey();