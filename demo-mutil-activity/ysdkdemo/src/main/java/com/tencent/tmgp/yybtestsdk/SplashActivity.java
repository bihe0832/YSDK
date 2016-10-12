package com.tencent.tmgp.yybtestsdk;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.ysdk.api.YSDKApi;


public class SplashActivity extends Activity {

    //TODO 选择java还是cpp
    public static final String LANG_CPP = "cpp";
    public static final String LANG_JAVA = "java";

    public static String LANG = LANG_JAVA;// 开发语言 java cpp

	private static String LOG_TAG = "YSDKDemo SplashActivity";
    private static Activity mActivity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(LOG_TAG,"onCreate");
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);

        // TODO GAME 游戏需自行检测自身是否重复, 检测到重复的Activity则要把自己finish掉
        // 注意：游戏需要加上去重判断以及finish重复的实例的逻辑，否则可能发生重复拉起游戏的问题。
        if (null != mActivity && !mActivity.equals(this)) {
            Log.d(LOG_TAG,"Warning!Reduplicate game activity was detected.Activity will finish immediately.");
            // TODO GAME 处理游戏被拉起的情况
            YSDKApi.handleIntent(this.getIntent());
            this.finish();
            return;
        }else{

            // TODO GAME YSDK初始化
            YSDKApi.onCreate(this);

            // TODO GAME 处理游戏被拉起的情况
            YSDKApi.handleIntent(this.getIntent());
        }

        mActivity = this;

        ModuleManager.LANG = LANG;

        final Intent intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setClass(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 3000);
	}

    // TODO GAME 在onNewIntent中需要调用handleIntent将平台带来的数据交给YSDK处理
    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(LOG_TAG,"onNewIntent");
        super.onNewIntent(intent);
        YSDKApi.handleIntent(intent);
    }

}

