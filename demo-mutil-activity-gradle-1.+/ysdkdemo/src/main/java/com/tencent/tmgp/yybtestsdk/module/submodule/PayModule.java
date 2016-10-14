package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.MainActivity;
import com.tencent.tmgp.yybtestsdk.PlatformTest;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.SplashActivity;
import com.tencent.tmgp.yybtestsdk.YSDKCallback;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoApi;
import com.tencent.ysdk.module.pay.PayItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by hardyshi on 16/7/25.
 */
public class PayModule extends BaseModule {

    private static final String MIDAS_APPKEY = "i0o7LppGJSpNVGRE";
    public static final String MODULE_NAME = "支付模块";
    public PayModule() {
        this.name = MODULE_NAME;
    }

    @Override
    public void init(LinearLayout parent, MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
        FuncBlockView payView = new FuncBlockView(parent, this);

        // 通用接口
        ArrayList<YSDKDemoApi> aboutPay = new ArrayList<YSDKDemoApi>();
        aboutPay.add(new YSDKDemoApi(
                "callGetPf",
                "getPf & getPfKey",
                "获取pf+pfKey",
                "pf+pfKey支付的时候会用到"));
        payView.addView(mMainActivity, "通用功能", aboutPay);

        // 游戏币相关
        ArrayList<YSDKDemoApi> aboutRecharge = new ArrayList<YSDKDemoApi>();
        aboutRecharge.add(new YSDKDemoApi(
                "callRecharge",
                "recharge",
                "充值游戏币",
                "",
                "大区id 充值数额 充值数额是否可改，三个参数用空格分割",
                "1 1 1"));
        payView.addView(mMainActivity, "游戏币相关", aboutRecharge);
        // 道具直购相关
        ArrayList<YSDKDemoApi> aboutBuyGoods = new ArrayList<YSDKDemoApi>();
        aboutBuyGoods.add(new YSDKDemoApi(
                "callBuyGoodsWithTokenUrl",
                "buyGoods",
                "道具直购-服务器下单",
                "",
                "大区id tokenurl（通过后台获取）",
                "1 "));
        aboutBuyGoods.add(new YSDKDemoApi(
                "callBuyGoodsWithClient",
                "buyGoods",
                "道具直购-客户端下单",
                "",
                "大区id 道具id 道具名称 道具描述 道具单价 道具数量",
                "1 G1 道具名称 道具描述 1 5"));
        payView.addView(mMainActivity, "道具直购相关", aboutBuyGoods);
    }

    public String callGetPf() {
        String result = "";
        String pf = "";
        String pfKey = "";
        if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
            pf = PlatformTest.getPf();
            pfKey = PlatformTest.getPfKey();
        } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
            pf = com.tencent.ysdk.api.YSDKApi.getPf();
            pfKey = com.tencent.ysdk.api.YSDKApi.getPfKey();
        }
        result = "Pf = " + pf;
        result += "\n pfKey = " + pfKey;
        return result;
    }

    public void callRecharge(String input){
        String[] paraArr = input.split(" ");
        if(paraArr.length > 0 && null != paraArr[0]){
            boolean isCanChange = true;
            if(null != paraArr[2]){
                try {
                    int value = Integer.parseInt(paraArr[2]);
                    if(value > 0){
                        isCanChange = false;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Bitmap bmp = BitmapFactory.decodeResource(mMainActivity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
                PlatformTest.recharge(paraArr[0],paraArr[1],isCanChange,appResData,appResData.length,ysdkExt);
            } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
                com.tencent.ysdk.api.YSDKApi.recharge(paraArr[0],paraArr[1],isCanChange,appResData,ysdkExt,new YSDKCallback(mMainActivity));

            }
        }else{
            Log.e(MainActivity.LOG_TAG,"para is bad:"+ input);
        }
    }

    public void callBuyGoodsWithTokenUrl(String input){
        String[] paraArr = input.split(" ");
        if(paraArr.length > 0 && null != paraArr[0]){
            Bitmap bmp = BitmapFactory.decodeResource(mMainActivity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            if (SplashActivity.LANG_CPP.equals(ModuleManager.LANG)) {
                PlatformTest.buyGoods(paraArr[0],paraArr[1],appResData,appResData.length,ysdkExt);
            } else if (SplashActivity.LANG_JAVA.equals(ModuleManager.LANG)) {
                com.tencent.ysdk.api.YSDKApi.buyGoods(paraArr[0],paraArr[1],appResData,ysdkExt,new YSDKCallback(mMainActivity));
            }
        }else{
            Log.e(MainActivity.LOG_TAG,"para is bad:"+ input);
        }
    }

    public void callBuyGoodsWithClient(String input){

        String[] paraArr = input.split(" ");
        if(paraArr.length > 0 && null != paraArr[0]){
            PayItem item = new PayItem();
            item.id = paraArr[1];
            item.name = paraArr[2];
            item.desc = paraArr[3];
            item.price = Integer.parseInt(paraArr[4]);
            item.num = Integer.parseInt(paraArr[5]);
            Bitmap bmp = BitmapFactory.decodeResource(mMainActivity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            String midasExt = "midasExt";
            com.tencent.ysdk.api.YSDKApi.buyGoods(paraArr[0],item,MIDAS_APPKEY,appResData,midasExt,ysdkExt,new YSDKCallback(mMainActivity));
        }else{
            Log.e(MainActivity.LOG_TAG,"para is bad:"+ input);
        }
    }
}
