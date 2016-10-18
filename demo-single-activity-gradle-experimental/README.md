# demo-single-activity-gradle-experimental

- 简介：

	YSDK的单Activity的SDK Demo

- 对应YSDK版本：

	1.1.1

- 运行环境：

	Android Studio 2.1 & com.android.tools.build:gradle-experimental:0.2.+ & gradle 2.5

- 运行方式	
	
	- 使用Android Studio 导入对应工程
	
- 准备工作

	运行之前要先根据自己的网络环境以及对应的gradle插件版本修改下面几个文件。
	
	- 修改根目录下local.properies中的ndk.dir和sdk.dir的环境配置
	- 修改根目录下gradle/wrapper/gradle-wrapper.properties 关于使用的gradle版本的地址的修改
	- 修改根目录下build.gradle中对于使用的maven库的声明
	- 标准版本需要修改根目录下gradle.properties文件，添加参数：android.useDeprecatedNdk=true

- 特别说明：
	
	- **如果开发者使用的Android Studio版本为2.2时，请务必确认gradle插件版本为gradle-experimental:0.2；gradle的版本为2.5；Android Studio 默认的2.2.0和2.14.1存在兼容性问题会导致Demo无法运行**

