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


public class WXModule extends BaseModule {

    public WXModule() {
        this.name = "微信登录";
    }

    @Override
    public void init(LinearLayout parent, MainActivity activity) {
        this.mMainActivity = activity;
        FuncBlockView weixinView = new FuncBlockView(parent, this);

        // 登录相关
        ArrayList<YSDKDemoApi> aboutLogin = new ArrayList<YSDKDemoApi>();
        aboutLogin.add(new YSDKDemoApi("letUserLogout", "letUserLogout", "登出", "退出微信登录"));
        aboutLogin.add(new YSDKDemoApi(
                "callgetLoginRecord",
                "getLoginRecord",
                "登录记录",
                "读取本次微信登录票据"));
        weixinView.addView(mMainActivity, "登录相关", aboutLogin);

        // 通用接口
        ArrayList<YSDKDemoApi> globalList = new ArrayList<YSDKDemoApi>();
        globalList.add(new YSDKDemoApi(
                "callGetIsPlatformInstalled",
                "isPlatformInstalled",
                "检查微信是否安装",
                ""
        ));
        globalList.add(new YSDKDemoApi(
                "callgetPlatformAPPVersion",
                "getPlatformAPPVersion",
                "检查微信版本",
                "检查微信版本号。部分接口是不支持低版本微信，请仔细接入文档的相关说明。"
        ));
        weixinView.addView(mMainActivity, "通用", globalList);

        // 关系链功能集合
        ArrayList<YSDKDemoApi> relationList = new ArrayList<YSDKDemoApi>();
        relationList.add(new YSDKDemoApi(
                "callQueryWXUserInfo",
                "queryWXUserInfo",
                "个人信息",
                "查询个人基本信息"));
        weixinView.addView(mMainActivity, "关系链", relationList);

        // 支付相关
        ArrayList<YSDKDemoApi> payList = new ArrayList<YSDKDemoApi>();
        payList.add(new YSDKDemoApi(
                "callPay",
                "",
                "拉起支付模块",
                ""));
        weixinView.addView(mMainActivity, "支付相关", payList);
    }

    public void callPay() {
        mMainActivity.startPayModule();
    }

    // 登出游戏
    public void letUserLogout() {
        mMainActivity.letUserLogout();
        mMainActivity.endModule();
    }

    /**
     * ************MSDK API调用示例*************
     */

    public String callgetLoginRecord() {
        UserLoginRet ret = new UserLoginRet();
        int platform = 0;
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            platform = PlatformTest.getLoginRecord(ret);
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            platform = com.tencent.ysdk.api.YSDKApi.getLoginRecord(ret);
        }
        Log.d(MainActivity.LOG_TAG,"ret:"+ ret.toString());
        String result = "";
        if (platform == ePlatform.PLATFORM_ID_WX) {
            result += "platform = " + ret.platform + " 微信登录 \n";
            result += "accessToken = "
                    + ret.getAccessToken() + "\n";
            result += "refreshToken = "
                    + ret.getRefreshToken() + "\n";
        }
        result += "openid = " + ret.open_id + "\n";
        result += "flag = " + ret.flag + "\n";
        //result += "desc = " + ret.desc + "\n";
        result += "pf = " + ret.pf + "\n";
        result += "pf_key = " + ret.pf_key + "\n";
        return result;
    }

    public String callGetIsPlatformInstalled(){
        boolean isInstall = false;
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            isInstall =  PlatformTest.isPlatformInstalled(ePlatform.WX.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            isInstall = com.tencent.ysdk.api.YSDKApi.isPlatformInstalled(ePlatform.WX);
        }

        return String.valueOf(isInstall);
    }

    public String callgetPlatformAPPVersion() {
        String wxVersion = "";

        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            wxVersion =  PlatformTest.getPlatformAppVersion(ePlatform.WX.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            wxVersion = com.tencent.ysdk.api.YSDKApi.getPlatformAppVersion(ePlatform.WX);
        }
        if (wxVersion == null || wxVersion == "") {
            return "Get mobile Weixin version failed!";
        } else {
            return wxVersion;
        }
    }

    public void callQueryWXUserInfo() {
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { 
            PlatformTest.queryUserInfo(ePlatform.WX.val());
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            com.tencent.ysdk.api.YSDKApi.queryUserInfo(ePlatform.WX);
        }
    }
}
