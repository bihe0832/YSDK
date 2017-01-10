## 1. 模块介绍

1. YSDK 从 1.3.1 版本开始支持接入YSDK接入时配置检查，该模块接入开发者无需任何工作。

## 2. 模块说明

该模块旨在帮助开发者快速完成SDK的接入，**当开发者使用YSDK的测试环境时，该功能自动生效，它会读取游戏关于YSDK的相关配置并结合YSDK的配置要求检查配置是否正确。**

对于不符合YSDK接入规范的的接入配置，YSDK配置检查模块检查确认以后会**通过直接toast信息和logcat日志中error级别日志提示两种方式来提醒开发者**。开发者可以根据SDK的提示信息修改相关配置。以下为基于YSDK的demo的错误事例。

- Toast

![配置检查结果展示](./ysdk_check.png '配置检查结果展示')

- logcat日志

		YSDK_CHECK: ******************* 游戏接入过程中请重点关注  *********************
		YSDK_CHECK: *   SDK自检模块检测开始,游戏可以结合日志信息确认对应配置是否正确   *
		YSDK_CHECK: YSDK Error: AndroidMainfest: the screenOrientation of com.tencent.midas.proxyactivity.APMidasPayProxyActivity must be same as LauncherActivity
		YSDK_CHECK: *   SDK自检模块检测结束,游戏可以结合检测过程日志确认配置是否正确   *
		YSDK_CHECK: ***************************************************************