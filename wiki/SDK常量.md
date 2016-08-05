# YSDK Android 常量


## 1. 平台类型ePlatform
	typedef enum {
	    ePlatform_None = 0,
	    ePlatform_Weixin =2,
	    ePlatform_QQ = 1
	}ePlatform;



## 2. UserToken

UserToken结构定义说明如下：

	typedef struct {
 		int type; //票据类型
 		std::string value; //票据value
 		long long expiration; //票据过期时间（游戏不需要关心）
	}TokenRet;


其中token type有对应的票据类型，含义及对应的定义如下：

| 平台 | token类型 | 有效期  | C++ 定义 | Java定义 |
|:-------------:|:-------------:|:-------------:| :-------:|:-------:|:-------:|
|手Q	|	accesstoken|	 	90天|	`eToken_QQ_Access`| `UserToken.TOKEN_TYPE_QQ_ACCESS`|
|手Q	|	paytoken	|		6天|	`eToken_QQ_Pay`|  `UserToken.TOKEN_TYPE_QQ_PAY`|
|微信	|	accesstoken|		2小时|	`eToken_WX_Access`| `UserToken.TOKEN_TYPE_WX_ACCESS`|
|微信	|	refreshtoken|		30天|	`eToken_WX_Refresh`| `UserToken.TOKEN_TYPE_WX_REFRESH`|

## 3. LoginRet

LoginRet 保存用户登录相关的票据信息，其定义如下：

	typedef struct {
	    int ret;
	    int flag;               //返回标记，标识成功和失败类型
	    std::string msg;       //返回描述
	    int platform;           //当前登录的平台
	    std::string open_id;
	    int user_type;
	    std::vector<UserToken> token;
	    std::string pf;
	    std::string pfKey;
	}UserLoginRet;


## 4. WakeupRet

游戏被拉起时平台或者拉起时透传的相关信息保存在此结构中，WakeupRet 结构定义如下：

		typedef struct{
		    int flag;               //返回标记，标识成功和失败类型
		    std::string msg;       //返回描述
		    int platform;           //当前登录的平台
		    std::string media_tag_name; //wx回传得meidaTagName
		    std::string open_id;        //qq传递的openid
		    std::string desc;           //描述
		    std::string lang;          //语言     目前只有微信5.1以上用，手Q不用
		    std::string country;       //国家     目前只有微信5.1以上用，手Q不用
		    std::string messageExt;    //游戏分享传入自定义字符串，平台拉起游戏不做任何处理返回         目前只有微信5.1以上用，手Q不用
		    std::vector<KVPair> extInfo;  //游戏－平台携带的自定义参数手Q专用
		}WakeupRet;

## 5. UserRelationRet

RelationRet保存查询用户个人或者好友信息的内容，RelationRet定义如下：

		typedef struct {
		    int ret;
		    int flag;               //返回标记，标识成功和失败类型
		    std::string msg;       //返回描述
		    std::vector<PersonInfo> persons;//保存好友或个人信息
		    std::string extInfo; //游戏查询是传入的自定义字段，用来标示一次查询
		}UserRelationRet;

其中具体个人的信息的结构体PersonInfo的字段含义如下：


| 字段说明 | 字段名称 | 备注 |
|:--:|:--|:--:|
| 昵称|nickname| 如果有备注昵称，拉取到为备注昵称|
|用户ID|openId| 用户在该appid下的唯一性标示，appid内唯一|
|账号ID|userId| 用户在开发者的多个appid下的唯一性标示，可用于多应用打通|
|性别|gender|微信默认为男|
|头像（小）| pictureSmall）|手Q为中文30 * 30，微信为132 * 132 |
|头像（中）|pictureMiddle|手Q为中文50 * 50，微信为 * 96|
|头像（大）|pictureLarge|手Q为中文100 * 100，微信为46 * 46|
|国家|country|手Q为中文，微信为代码|
|省份|province|手Q为中文，微信为代码|
|城市|city|手Q为中文，微信为代码|
|是否为黄钻|`is_yellow_vip`|手Q专用，是否为黄钻用户（0：不是； 1：是）|
|是否为年费黄钻|`is_yellow_year_vip`|手Q专用，是否为年费黄钻用户（0：不是； 1：是）|
|手Q专用|`yellow_vip_level`|手Q专用，黄钻等级，目前最高级别为黄钻8级|
|是否为豪华版黄钻|`is_yellow_high_vip`|手Q专用，是否为豪华版黄钻用户（0：不是； 1：是）|

## 7. eFlag

eFlag为YSDK的各种错误码，YSDK从1.2.0开始重新优化了错误码，优化后的错误码如下：


### 通用错误码：
	
| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
|0		|`eFlag_Succ`	|eFlag.Succ|成功| 结合具体场景处理|
|-1		|`eFlag_Error`|eFlag.Error|失败| 结合具体场景处理|


### 手Q相关错误码：
	
| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 1001		|`eFlag_QQ_UserCancel `	|`eFlag.QQ_UserCancel`| 用户取消| 引导用户重新授权或者分享|
| 1002		|`eFlag_QQ_LoginFail `|`eFlag.QQ_LoginFail`| 手Q返回登陆失败| 引导用户重新授权|
| 1003		|`eFlag_QQ_NetworkErr `|`eFlag.QQ_NetworkErr`| 手Q提示登录时网路异常| 引导用户检查网络后重试|
| 1004		|`eFlag_QQ_NotInstall `|`eFlag.QQ_NotInstall`| 用户手机没有安装手Q| 引导用户安装手Q后重试|
| 1005		|`eFlag_QQ_NotSupportApi `|`eFlag.QQ_NotSupportApi`| 用户手机手Q版本太低| 引导用户升级手Q后重试|

### 微信相关错误码

| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 2000		|`eFlag_WX_NotInstall `|`eFlag.WX_NotInstall`| 用户手机没有安装微信| 引导用户安装微信后重试|
| 2001		|`eFlag_WX_NotSupportApi `|`eFlag.WX_NotSupportApi`| 用户手机微信版本太低| 引导用户升级微信后重试|
| 2002		|`eFlag_WX_UserCancel `|`eFlag.WX_UserCancel`| 用户取消| 引导用户重新授权或者分享|
| 2003		|`eFlag_WX_UserDeny `|`eFlag.WX_UserCancel`| 用户拒绝授权| 引导用户重新授权|
| 2004		|`eFlag_WX_LoginFail `|`eFlag.QQ_LoginFail`| 微信返回登陆失败| 引导用户重新授权|

### 其余登陆相关错误码

| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 3100		|`eFlag_LocalTokenInvalid `|`eFlag.Login_TokenInvalid`| 本地票据不可用| 引导用户重新授权|
| 3101		|`eFlag_Login_NotRegisterRealName `|`eFlag.Login_NotRegisterRealName`| 用户账号没有实名认证| 引导用户重新授权并认证|
| 3102		|`eFlag_CheckingToken `|`eFlag.Login_CheckingToken`| YSDK 自动登录中| 无需关注|

### 获取个人信息相关错误码

| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 3201		|`eFlag_Relation_RelationNoPerson `|`eFlag.Relation_RelationNoPerson`| 没有查询到信息| 重试|

### 应用拉起相关错误码

| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 3301		|`eFlag_Wakeup_NeedUserLogin `|`eFlag.Wakeup_NeedUserLogin`| 拉起时无登陆 | 引导用户授权|
| 3302		|`eFlag_Wakeup_YSDKLogining `|`eFlag.Wakeup_YSDKLogining`| 拉起时可以使用历史或者拉起票据登陆，YSDK自动登录中| 无需关注（登陆结束会有对应回调）|
| 3303		|`eFlag_Wakeup_NeedUserSelectAccount `|`eFlag.Wakeup_NeedUserSelectAccount`| 拉起是存在异账号| 游戏需要弹框让用户选择登入游戏的账号|


### 支付相关错误码

| 返回码 | C++ 定义 | Java 定义 | 描述 | 推荐处理 |
|:-------:|:-------:|:-------:|:-------:|:-------:|
| 4001		|`eFlag_Pay_User_Cancle `|`eFlag.Pay_User_Cancle`| 用户取消支付 | 引导用户重试|
| 4002		|`eFlag_Pay_Param_Error `|`eFlag.Pay_Param_Error`| 支付相关参数错误| 引导用户重试 |

**注意事项：支付相关的错误码仅仅代表支付流程是否正常，游戏在收到支付成功的回调以后还要参照midas的回调来进一步确认支付结果，具体可以参考YSDK Demo中 的实现。**