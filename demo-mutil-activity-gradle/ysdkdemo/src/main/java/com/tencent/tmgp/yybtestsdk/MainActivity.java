package com.tencent.tmgp.yybtestsdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tmgp.yybtestsdk.appearance.ResultView;
import com.tencent.tmgp.yybtestsdk.appearance.Util;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.BaseRet;
import com.tencent.ysdk.framework.common.eFlag;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.user.UserLoginRet;

import java.util.ArrayList;

/**
 * 说明:
 * 每个模块相关的接口调用示例在 .module.submodule 中;
 * 每个接口的详细使用说明在 jni/CommonFiles/YSDKApi.h 中;
 * PlatformTest是 YSDK c++ 接口调用示例;
 * 标签 TODO GAME 标识之处是游戏需要关注并处理的!!
 */
public class MainActivity extends Activity {



    private ArrayList<BaseModule> nameList;
    private BaseModule seletedModule;

    public static ProgressDialog mAutoLoginWaitingDlg;
    public static GridView mModuleListView;
    public static LinearLayout mModuleView;
    public static LinearLayout mResultView;
    public LocalBroadcastManager lbm;
    public BroadcastReceiver mReceiver;
    public static String title = "";
    public static String callAPI = "";
    public static String desripton = "";

    public static final String LOG_TAG = "YSDK DEMO";
    public static final String LOCAL_ACTION = "com.tencent.ysdkdemo";

    protected static int platform = ePlatform.None.val();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);

        // TODO GAME YSDK初始化
        YSDKApi.onCreate(this);
        // TODO GAME 设置java层或c++层回调,如果两层都设置了则会只回调到java层
        if (SplashActivity.LANG.equals(SplashActivity.LANG_JAVA)) {
            // 全局回调类，游戏自行实现
            YSDKApi.setUserListener(new YSDKCallback(this));
            YSDKApi.setBuglyListener(new YSDKCallback(this));
        } else {
            PlatformTest.setActivity(this);
        }

        // YSDKDemo 界面实现
        initView();
    }

    static{
        System.loadLibrary("YSDKDemo"); // 游戏不需要这个
    }


    // TODO GAME 在onActivityResult中需要调用YSDKApi.onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(LOG_TAG,"onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        YSDKApi.onActivityResult(requestCode, resultCode, data);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onRestart()
    @Override
    protected void onRestart() {
        super.onRestart();
        YSDKApi.onRestart(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onResume()
    @Override
    protected void onResume() {
        super.onResume();
        YSDKApi.onResume(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onPause()
    @Override
    protected void onPause() {
        super.onPause();
        YSDKApi.onPause(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onStop()
    @Override
    protected void onStop() {
        super.onStop();
        YSDKApi.onStop(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onDestory()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        YSDKApi.onDestroy(this);
        Log.d(LOG_TAG, "onDestroy");

        if (null != lbm) {
            lbm.unregisterReceiver(mReceiver);
        }

        if(null != mAutoLoginWaitingDlg){
            mAutoLoginWaitingDlg.cancel();
        }
    }

    // TODO GAME 在异账号时，模拟游戏弹框提示用户选择登陆账号
    public void showDiffLogin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("异账号提示");
                builder.setMessage("你当前拉起的账号与你本地的账号不一致，请选择使用哪个账号登陆：");
                builder.setPositiveButton("本地账号",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                showToastTips("选择使用本地账号");
                                if (SplashActivity.LANG.equals(SplashActivity.LANG_JAVA)) {
                                    if (!YSDKApi.switchUser(false)) {
                                        letUserLogout();
                                    }
                                } else {
                                    if(!PlatformTest.switchUser(false)){
                                        letUserLogout();
                                    }
                                }
                            }
                        });
                builder.setNeutralButton("拉起账号",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                showToastTips("选择使用拉起账号");
                                if (SplashActivity.LANG.equals(SplashActivity.LANG_JAVA)) {
                                    if (!YSDKApi.switchUser(true)) {
                                        letUserLogout();
                                    }
                                } else {
                                    if(!PlatformTest.switchUser(true)){
                                        letUserLogout();
                                    }
                                }
                            }
                        });
                builder.show();
            }
        });

    }

    // 平台授权成功,让用户进入游戏. 由游戏自己实现登录的逻辑
    public void letUserLogin() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        Log.d(LOG_TAG,"flag: " + ret.flag);
        Log.d(LOG_TAG,"platform: " + ret.platform);
        if (ret.ret != BaseRet.RET_SUCC) {
            showToastTips( "UserLogin error!!!");
            Log.d(LOG_TAG,"UserLogin error!!!");
            letUserLogout();
            return;
        }
        if (ret.platform == ePlatform.PLATFORM_ID_QQ) {
            for (int i = 0; i < nameList.size(); i++) {
                if ("QQ登录".equals(nameList.get(i).name)) {
                    seletedModule = nameList.get(i);
                    startModule();
                    break;
                }
            }
        } else if (ret.platform == ePlatform.PLATFORM_ID_WX) {
            for (int i = 0; i < nameList.size(); i++) {
                if ("微信登录".equals(nameList.get(i).name)) {
                    seletedModule = nameList.get(i);
                    startModule();
                    break;
                }
            }
        }else if (ret.platform == ePlatform.PLATFORM_ID_GUEST) {
            for (int i = 0; i < nameList.size(); i++) {
                if ("游客登录".equals(nameList.get(i).name)) {
                    seletedModule = nameList.get(i);
                    startModule();
                    break;
                }
            }
        }
    }

    // 登出后, 更新view. 由游戏自己实现登出的逻辑
    public void letUserLogout() {
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { // 使用C++调用YSDK, 游戏只需要用一种方式即可
            PlatformTest.logout();
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) { // 使用Java调用YSDK
            YSDKApi.logout();
        }
    }


    public void startWaiting() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG,"startWaiting");
                mAutoLoginWaitingDlg = new ProgressDialog(MainActivity.this);
                stopWaiting();
                mAutoLoginWaitingDlg.setTitle("YSDK DEMO登录中...");
                mAutoLoginWaitingDlg.show();
            }
        });

    }

    public void stopWaiting() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG,"stopWaiting");
                if (mAutoLoginWaitingDlg != null && mAutoLoginWaitingDlg.isShowing()) {
                    mAutoLoginWaitingDlg.dismiss();
                }
            }
        });

    }

    public void showToastTips(String tips) {
        Toast.makeText(this,tips,Toast.LENGTH_LONG).show();
    }

    // 获取当前登录平台
    public ePlatform getPlatform() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if (ret.flag == eFlag.Succ) {
            return ePlatform.getEnum(ret.platform);
        }
        return ePlatform.None;
    }

    // ***********************界面布局相关*************************
    // 初始化界面
    private void initView() {
        Util.init(getApplicationContext()); //初始化Demo需要的工具类

        nameList = ModuleManager.getInstance().modulesList;

        lbm = LocalBroadcastManager.getInstance(getApplicationContext());

        mModuleListView = (GridView) findViewById(R.id.gridview);
        mModuleView = (LinearLayout) findViewById(R.id.module);
        mResultView = (LinearLayout) findViewById(R.id.result);

        //设置actionbar
        //隐藏后退按钮，并设置为不可点击
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setFocusable(false);
        llayout.setClickable(false);
        llayout.setVisibility(View.GONE);

        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        LayoutParams textParams = (LayoutParams) title.getLayoutParams();
        textParams.leftMargin = Util.dp(9);
        title.setLayoutParams(textParams);
        title.setText("YSDKDemo 未登录");

        // 设置局部广播，处理回调信息
        lbm = LocalBroadcastManager.getInstance(this.getApplicationContext());
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String result = intent.getExtras().getString("Result");
                Log.d(LOG_TAG,result);
                displayResult(result);
            }

        };
        lbm.registerReceiver(mReceiver, new IntentFilter(LOCAL_ACTION));

        resetMainView();

        mModuleListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                seletedModule = nameList.get(position);
                ePlatform platform = getPlatform();
                if ("QQ登录".equals(seletedModule.name)) {
                    if (ePlatform.QQ == platform) {
                        // 如已登录直接进入相应模块视图
                        startModule();
                    } else if (ePlatform.None == platform) {
                        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { // 使用C++调用YSDK, 游戏只需要用一种方式即可
                            PlatformTest.login(ePlatform.QQ.val());
                        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) { // 使用Java调用YSDK
                            YSDKApi.login(ePlatform.QQ);
                        }
                        startWaiting();
                    }else{
                        Log.d(LOG_TAG,"QQ登录中~~~");
                    }
                } else if ("微信登录".equals(seletedModule.name)) {
                    if (ePlatform.WX == platform) {
                        // 如已登录直接进入相应模块视图
                        startModule();
                    } else if (ePlatform.None == platform) {
                        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { // 使用C++调用YSDK, 游戏只需要用一种方式即可
                            PlatformTest.login(ePlatform.WX.val());
                        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) { // 使用Java调用YSDK
                            YSDKApi.login(ePlatform.WX);
                        }
                        startWaiting();
                    }else{
                        Log.d(LOG_TAG,"微信登录中~~~");
                    }
                } else if ("游客登录".equals(seletedModule.name)) {
                    if (ePlatform.Guest == platform) {
                        // 如已登录直接进入相应模块视图
                        startModule();
                    } else if (ePlatform.None == platform) {
                        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) { // 使用C++调用YSDK, 游戏只需要用一种方式即可
                            PlatformTest.login(ePlatform.Guest.val());
                        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) { // 使用Java调用YSDK
                            YSDKApi.login(ePlatform.Guest);
                        }
                        startWaiting();
                    }else{
                        Log.d(LOG_TAG,"游客登录中~~~");
                    }
                }else if ("支付模块".equals(seletedModule.name)) {
                    startPayModule();
                } else {
                    // 进行其它功能模块
                    startModule();
                }

            }
        });
    }

    public void startPayModule(){
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if(eFlag.Succ == ret.flag){
            seletedModule  = ModuleManager.getInstance().getPayModule();
            startModule();
        }else{
            Log.e(LOG_TAG,"please login first："+ret.toString());
            showToastTips("请登录后重试~");
        }
    }
    @SuppressLint("NewApi")
    public void resetMainView() {
        mModuleListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mModuleListView.setAdapter(new ArrayAdapter<BaseModule>(
                MainActivity.this, R.layout.gridview_item, nameList) {

            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) MainActivity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.gridview_item, null);
                }
                TextView itemText = (TextView) view.findViewById(R.id.item_txt);
                String item = getItem(position).name;
                ePlatform tempPlat = getPlatform();
                if (item != null) {
                    if (ePlatform.QQ == tempPlat) {
                        if(item.equals("微信登录") || item.equals("游客登录")){
                            view.getBackground().setAlpha(60);
                            itemText.setTextColor(0x60000000);
                        }
                    } else if ( ePlatform.WX == tempPlat) {
                        if(item.equals("QQ登录") || item.equals("游客登录")){
                            view.getBackground().setAlpha(60);
                            itemText.setTextColor(0x60000000);
                        }
                    }else if (ePlatform.Guest == tempPlat) {
                        if(item.equals("QQ登录") || item.equals("微信登录")){
                            view.getBackground().setAlpha(60);
                            itemText.setTextColor(0x60000000);
                        }
                    } else {
                        if(item.equals("支付模块")){
                            view.getBackground().setAlpha(60);
                            itemText.setTextColor(0x60000000);
                        }else{
                            view.getBackground().setAlpha(255);
                            itemText.setTextColor(0xff000000);
                        }
                    }
                    TextView itemView = (TextView) view
                            .findViewById(R.id.item_txt);
                    if (itemView != null) {
                        itemView.setText(item);
                    }
                }
                return view;
            }
        });
    }


    // 展示相应的功能模块
    public void startModule() {
        mModuleListView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mModuleView.removeAllViews();
        seletedModule.init(mModuleView, this);
        mModuleView.setVisibility(View.VISIBLE);

        //设置actionbar、模块通用布局
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        title.setTextColor(Color.WHITE);
        title.setText(seletedModule.name);
        llayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                endModule();
            }

        });
    }

    // 退出功能模块
    public void endModule() {
        mModuleView.removeAllViews();
        mModuleView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mModuleListView.setVisibility(View.VISIBLE);
        resetMainView();
        //设置actionbar
        //隐藏后退按钮，并设置为不可点击
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setFocusable(false);
        llayout.setClickable(false);
        llayout.setVisibility(View.GONE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        ePlatform platform = getPlatform();
        title.setTextColor(Color.RED);
        if (platform == ePlatform.QQ) {
            title.setText("YSDKDemo QQ登录中");
        } else if (platform == ePlatform.WX) {
            title.setText("YSDKDemo 微信登录中");
        }  else if (platform == ePlatform.Guest) {
            title.setText("YSDKDemo 游客登录中");
        } else {
            title.setText("YSDKDemo 未登录");
            title.setTextColor(Color.WHITE);
        }
    }

    public void displayResult(String result) {
        mModuleView.setVisibility(View.GONE);
        mModuleListView.setVisibility(View.GONE);
        mResultView.removeAllViews();

        ResultView block = new ResultView(this, mResultView);
        block.addView("CallAPI", callAPI);
        block.addView("Desripton", desripton);
        block.addView("Result", result);

        //设置actionbar、模块通用布局
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        title.setText(this.title);
        llayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                endResult();
            }

        });
        mResultView.setVisibility(View.VISIBLE);
    }

    public void endResult() {
        mModuleView.setVisibility(View.VISIBLE);
        mResultView.removeAllViews();
        mResultView.setVisibility(View.GONE);
        mModuleListView.setVisibility(View.GONE);

        //设置actionbar、模块通用布局
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        title.setText(seletedModule.name);
        llayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                endModule();
            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stopWaiting();
            if (mModuleListView.getVisibility() == View.VISIBLE) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("退出YSDKDemo");
                        builder.setMessage("你确定退出YSDK Demo么？");
                        builder.setPositiveButton("确定退出",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        finish();
                                        System.exit(0);
                                    }
                                });
                        builder.setNeutralButton("暂不退出",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                    }
                                });
                        builder.show();
                    }
                });
            } else if (mModuleView.getVisibility() == View.VISIBLE) {
                endModule();
            } else if (mResultView.getVisibility() == View.VISIBLE) {
                endResult();
            }
        }
        return false;
    }

    public void sendResult(String result) {
        if(lbm != null) {
            Intent sendResult = new Intent(LOCAL_ACTION);
            sendResult.putExtra("Result", result);
            Log.d(LOG_TAG,"send: "+ result);
            lbm.sendBroadcast(sendResult);
        }
    }
}
