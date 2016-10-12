package com.tencent.tmgp.yybtestsdk.module;

import com.tencent.tmgp.yybtestsdk.module.submodule.GuestModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.OthersFunction;
import com.tencent.tmgp.yybtestsdk.module.submodule.PayModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.QQModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.WXModule;

import java.util.ArrayList;

public class ModuleManager {
	private volatile static ModuleManager instance;
	public static String LANG;
	public ArrayList<BaseModule> modulesList = new ArrayList<BaseModule>();

	public static ModuleManager getInstance() {
		if(instance == null) {
			instance = new ModuleManager();
		}
		return instance;
	}

	private ModuleManager() {
		// 添加模块
		modulesList.add(new QQModule());
		modulesList.add(new WXModule());
		modulesList.add(new GuestModule());
		modulesList.add(new OthersFunction());
	}

	public BaseModule getPayModule(){
		return new PayModule();
	}

}
