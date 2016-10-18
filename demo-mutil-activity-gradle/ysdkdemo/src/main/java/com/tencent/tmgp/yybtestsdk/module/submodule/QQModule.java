package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.util.Log;
import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.MainActivity;
import com.tencent.tmgp.yybtestsdk.PlatformTest;
import com.tencent.tmgp.yybtestsdk.SplashActivity;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoApi;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.user.UserLoginRet;

import java.util.ArrayList;

public class QQModule extends BaseModule {
	
	public QQModule() {
		this.name = "QQ登录";
	}
	
	public void init(LinearLayout parent, MainActivity mainActivity) {
		this.mMainActivity = mainActivity;
		FuncBlockView qqView = new FuncBlockView(parent, this);
		
		// 登录相关
        ArrayList<YSDKDemoApi> aboutLogin = new ArrayList<YSDKDemoApi>();
        aboutLogin.add(new YSDKDemoApi("letUserLogout", "letUserLogout", "登出", "退出QQ登录"));
        aboutLogin.add(new YSDKDemoApi(
        		"callGetLoginRecord",
        		"getLoginRecord",
        		"登录记录", 
        		"读取本次QQ登录票据"));
        qqView.addView(mMainActivity, "登录相关", aboutLogin);
        
        // 通用接口
        ArrayList<YSDKDemoApi> globalList = new ArrayList<YSDKDemoApi>();
        globalList.add(new YSDKDemoApi(
                "callGetIsPlatformInstalled",
                "isPlatformInstalled",
                "检查手Q是否安装",
                ""
        ));
        globalList.add(new YSDKDemoApi(
                "callGetPlatformAPPVersion",
                "getPlatformAPPVersion",
                "检查手Q版本",
                "检查手Q版本号。部分接口是不支持低版本手Q的，请仔细接入文档的相关说明。"
                ));
        qqView.addView(mMainActivity, "通用", globalList);

        // 关系链功能集合
        ArrayList<YSDKDemoApi> relationList = new ArrayList<YSDKDemoApi>();
        relationList.add(new YSDKDemoApi(
                "callQueryQQUserInfo",
                "queryUserInfo",
                "个人信息", 
                "查询个人基本信息"));
        qqView.addView(mMainActivity, "关系链", relationList);

        // 支付相关
        ArrayList<YSDKDemoApi> payList = new ArrayList<YSDKDemoApi>();
        payList.add(new YSDKDemoApi(
                "callPay",
                "",
                "拉起支付模块",
                ""));
        qqView.addView(mMainActivity, "支付相关", payList);
	}

    public void callPay() {
        mMainActivity.startPayModule();
    }

    // 登出游戏
    public void letUserLogout() {
        mMainActivity.letUserLogout();
        mMainActivity.endModule();
    }

	public String callGetLoginRecord() {
		UserLoginRet ret = new UserLoginRet();
        int platform = 0;
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            platform = PlatformTest.getLoginRecord(ret);
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            platform = com.tencent.ysdk.api.YSDKApi.getLoginRecord(ret);
        }
        Log.d(MainActivity.LOG_TAG,"ret:"+ ret.toString());
        String result = "";
        if (platform == ePlatform.PLATFORM_ID_QQ) {
            result += "platform = " + ret.platform + " QQ登录 \n";
            result += "accessToken = "
                    + ret.getAccessToken() + "\n";
            result += "payToken = "
                    +ret.getPayToken() + "\n";
        }
        result += "openid = " + ret.open_id + "\n";
        result += "flag = " + ret.flag + "\n";
        result += "msg = " + ret.msg + "\n";
        result += "pf = " + ret.pf + "\n";
        result += "pf_key = " + ret.pf_key + "\n";
        return result;
	}

    public String callGetIsPlatformInstalled(){
        boolean isInstall = false;
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            isInstall =  PlatformTest.isPlatformInstalled(ePlatform.QQ.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            isInstall = com.tencent.ysdk.api.YSDKApi.isPlatformInstalled(ePlatform.QQ);
        }

        return String.valueOf(isInstall);
    }
	
	public String callGetPlatformAPPVersion() {
	    String qqVersion = "";

        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            qqVersion =  PlatformTest.getPlatformAppVersion(ePlatform.QQ.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            qqVersion = com.tencent.ysdk.api.YSDKApi.getPlatformAppVersion(ePlatform.QQ);
        }

	    if (qqVersion == null || qqVersion == "") {
	        return "Get mobile QQ version failed!";
	    } else {
	        return qqVersion;
	    }
	}
    
    public void callQueryQQUserInfo() {
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { // 使用C++调用MSDK, 游戏只需要用一种方式即可
            PlatformTest.queryUserInfo(ePlatform.QQ.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) { // 使用Java调用MSDK
            com.tencent.ysdk.api.YSDKApi.queryUserInfo(ePlatform.QQ);
        }
    }
}
