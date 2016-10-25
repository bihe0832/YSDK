# YSDK

## 简介

主要开源一些YSDK的最新文档、YSDK非主线的demo包等内容具体如下：

## YSDK Demo

### 运行方法：

- 运行方式	
	
	- 使用Android Studio 导入对应工程
	
- 准备工作

	运行之前要先根据自己的网络环境以及对应的gradle插件版本修改下面几个文件。
	
	- 修改根目录下local.properies中的ndk.dir和sdk.dir的环境配置
	- 修改根目录下gradle/wrapper/gradle-wrapper.properties 关于使用的gradle版本的地址的修改
	- 修改根目录下build.gradle中对于使用的maven库的声明
	- 标准版本需要修改根目录下gradle.properties文件，添加参数：android.useDeprecatedNdk=true

### 特别说明：

 **每个demo的项目的README文件中有对应Demo运行时的一些依赖说明，请运行时格外关注。**
 
### 工程介绍

#### demo-mutil-activity-gradle（推荐）：
	
- 简介：YSDK的多Activity的SDK Demo
- SDK版本：1.2.2
- 运行IED：Android Studio 2.1 & com.android.tools.build:gradle:1.+ & gradle 2.10

#### demo-mutil-activity-gradle-experimental：
	
- 简介：YSDK的多Activity的SDK Demo
- SDK版本：1.2.2
- 运行IED：Android Studio 2.1 & com.android.tools.build:gradle-experimental:0.2.+ & gradle 2.5

#### demo-single-activity-gradle（推荐）：

- 简介：YSDK的单Activity的SDK Demo
- SDK版本：1.1.1
- 运行IED：Android Studio 2.1 & com.android.tools.build:gradle:1.+ & gradle 2.10
	 
#### demo-single-activity-gradle-experimental

- 简介：YSDK的单Activity的SDK Demo
- SDK版本：1.1.1
- 运行IED： Android Studio 2.1 & com.android.tools.build:gradle-experimental:0.2.+ & gradle 2.5

## wiki

- 简介：YSDK的wiki的详细内容，部分版本灰度期间官网wiki不会添加内容，仅此处更新文档

## gist

- 部分YSDK相关的，游戏可能会用到的代码片段