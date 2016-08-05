## YSDK 引擎相关问题

这部分内容主要介绍YSDK接入过程中，游戏引擎相关的几个问题的解决方案。

### 1. Unity 接入

#### 1.1 部分Unity游戏启动时界面假死

##### 问题现象:

部分Unity的游戏使用YSDK 1.1.1版本接入时会出现登陆过程中，界面忽然假死停止，但是观察日志游戏逻辑正常。出错原因多出在加载YSDK的so。分析发现集中在部分Unity版本（目前已知有：4.7.1、5.2.2）

##### 解决方案:

YSDK在1.2.0 已经用提前加载so的方式规避了这个问题，具体的问题原因还在定位中，使用YSDK 1.1.1版本的游戏可以在游戏的launcherActivity中加入下面的代码解决该问题。

	try{
      System.loadLibrary("YSDK");
    }catch (Exception localException){
      localException.printStackTrace();
    }
        
#### 1.2 部分Unity游戏未装手Q时使用手Q登陆，跳转到提示下载手Q的界面返回游戏时Crash

##### 问题现象:

部分Unity游戏在部分机型上会遇到以下场景：游戏未装手Q时使用手Q登陆，跳转到提示下载手Q的界面点击返回游戏时有可能会引起Crash。

##### 解决方案:

目前初步定位应该是Unity在资源打包时引起的，游戏可以直接把YSDK的jar包解压，之后将其assets中的内容再次解压放入Android/assets中。

### 2. Cocos接入

#### 2.1 部分Cocos游戏无法登陆或者登陆时Crash

##### 问题现象:

部分游戏在集成YSDK时使用非Android Library的方式集成，而是直接拷贝资源的方式集成，并将libYSDK.so添加到prebuild同时加入游戏的Android.mk中。问题现象表现为游戏登陆时Crash，反编译包以后发现YSDK的so大小异常，仅有10多K。

##### 解决方案:

目前确认是因为使用prebuild模式集成以后，NDK在编译时会进行strip优化。导致so不完整引起。游戏可以通过改用YSDK集成方式的办法解决。游戏不直接集成YSDK，也不把YSDK的so放到prebuild里面，而是通过Android Library的方式集成YSDK，YSDK从1.2.0版本开始已经提供了Eclipse开发者专用的版本及Demo。

#### 2.1 部分Cocos游戏使用Eclipse出包，提示YSDK相关API找不到

##### 问题现象:

部分游戏在集成YSDK时使用Eclipse出包，然后编译没有问题，可以正常出包，但是运行时Crash，Crash原因一般为java.lang.NoClassDefFoundError，也即某些YSDK的类缺失，反编译apk以后发现对应类确实缺失。

##### 解决方案:

目前发现该问题仅在特定ADT版本出现（ADT的版本都是 v22.2.1.v201309180102-833290），更新ADT版本以后即可解决，或者使用ant直接打包也可以解决。详细内容请参考：[http://mp.weixin.qq.com/s?__biz=MjM5NTQ4Mjk4MA==&mid=2656579258&idx=1&sn=6cf43a7192fe04bd50c4a342f4c6daeb#rd](http://mp.weixin.qq.com/s?__biz=MjM5NTQ4Mjk4MA==&mid=2656579258&idx=1&sn=6cf43a7192fe04bd50c4a342f4c6daeb#rd)

### 3. Ane接入

#### 3.1 Ane游戏无法接收到onActivityResult导致手Q无法登陆

##### 问题现象:

目前YSDK的QQ登陆通过onActivityResult的方式实现，但是ANE的Activity无法接收onActivityResult的内容导致登陆异常。

##### 解决方案:

游戏可以添加一个Android原生的Activity作为登陆的代理Activity，只要确保最后一次YSDK的onCreate是在该activity调用，同时onActivityResult也在该Activity调用，就可以正常收到回调。