## SDK更新

### 1. Eclipse 工程更新

YSDK 推荐游戏使用Android Library方式接入YSDK，既可以隔绝SDK与游戏代码的耦合，而且方便后续的SDK版本更新。YSDK从1.2.0版本开始支持使用Android Library方式引入YSDK。

### 1.1 Java接入

1. 删除游戏目录下的原有YSDKLibrary目录
2. 复制新版本包中的YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
- 在Eclipse中重新引入YSDKLibrary项目
- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置
	
### 1.2 C++ 工程接入

1. 删除游戏目录下的原有YSDKLibrary目录
2. 复制新版本包中的YSDK-Eclipse下的YSDKLibrary目录到游戏工程相应目录
- 在Eclipse中重新引入YSDKLibrary项目
- 右击游戏项目->属性->Android->添加(库)->选择YSDKLibrary，完成对YSDKLibrary的引用
- 再次复制YSDKLibrary下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录，并添加到 Android.mk。
- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置


### 2. Android Studio工程更新

### 2.1  Java接入

1. 复制YSDK-Android-*.aar目录到游戏工程的libs下，删除原有版本
- 在游戏项目的build.gradle中更新对YSDK-Android-*.aar的依赖。
- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置
	
### 2.2  C++ 工程接入

1. 复制YSDK-Android-*.aar目录到游戏工程的libs下
- 在游戏项目的build.gradle中中添加对YSDK-Android-*.aar的依赖。
- 复制YSDKDemo下的jni目录下 .cpp 和 .h 文件到游戏工程相应目录。
- 根据版本调整说明中关于Androidmainfest的说明，根据游戏需求，调整对应的权限
- 根据版本调整说明中关于ysdkconf.ini的说明，根据游戏需求，调整对应配置
