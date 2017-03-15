# YSDK

## 写在前面

YSDK是腾讯应用宝开发团队为移动游戏开发者提供的公共组件和服务库，旨在帮助开发者快速接入腾讯平台，提升游戏接入和上线效率。为了方便开发者接入，YSDK提供了wiki、Demo、接入联调企业QQ接入协助。具体如下：

- **YSDK Wiki：[http://wiki.open.qq.com/wiki/YSDK%E4%BB%8B%E7%BB%8D](http://wiki.open.qq.com/wiki/YSDK%E4%BB%8B%E7%BB%8D)**
- **企业QQ：800013811**

开发者可以通过以上方式获取YSDK相关内容和寻求联调协助。本项目主要是方便开发者了解一些无法实时放在wiki或者YSDK的SDK包里面的内容。

## 项目简介

本项目主要是方便开发者了解一些无法实时放在wiki或者YSDK的SDK包里面的内容。具体包括：

- YSDK Demo

	ysdk的SDK包仅提供基于gradle-experimental的多Activity的游戏demo，为了方便使用不同版本、不同类型的游戏，这里提供了各种版本下的SDK的Demo

- YSDK Wiki

	ysdk的wiki上提供了所有YSDK相关的正式对外的文档内容，这里会在新版本灰度期间优先提供最新版本的文档给开发商

- YSDK gist

	ysdk的集成过程中，游戏在使用一些SDK的特别功能时需要用的工具类或者代码片段
	
	
## YSDK Demo

### 运行方法

如何修改配置及运行工程，请参考本人博客：[终端基于gradle的开源项目运行环境配置指引](
http://blog.bihe0832.com/android-as-gradle-config.html)

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

## YSDK wiki

- 简介：YSDK的wiki的详细内容，部分版本灰度期间官网wiki不会添加内容，仅此处更新文档

## YSDK gist

- 部分YSDK相关的，游戏可能会用到的代码片段