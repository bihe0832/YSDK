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

public class GuestModule extends BaseModule {

	public GuestModule() {
		this.name = "游客登录";
	}
	
	public void init(LinearLayout parent, MainActivity mainActivity) {
		this.mMainActivity = mainActivity;
		FuncBlockView guestView = new FuncBlockView(parent, this);
		
		// 登录相关
        ArrayList<YSDKDemoApi> aboutLogin = new ArrayList<YSDKDemoApi>();
        aboutLogin.add(new YSDKDemoApi("letUserLogout", "letUserLogout", "登出", "退出游客登录"));
        aboutLogin.add(new YSDKDemoApi(
        		"callGetLoginRecord",
        		"getLoginRecord",
        		"登录记录", 
        		"读取本次游客登录票据"));
        guestView.addView(mMainActivity, "登录相关", aboutLogin);

        // 支付相关
        ArrayList<YSDKDemoApi> payList = new ArrayList<YSDKDemoApi>();
        payList.add(new YSDKDemoApi(
                "callPay",
                "",
                "拉起支付模块",
                ""));
        guestView.addView(mMainActivity, "支付相关", payList);
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
}
