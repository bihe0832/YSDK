## 代码混淆规范

代码混淆规范

建议开发者优先使用版本包内提供的代码混淆规则！

	-keep public class * extends android.app.Activity
	-keep public class * extends android.app.Application
	-keep public class * extends android.app.Service
	-keep public class * extends android.content.BroadcastReceiver
	-keep public class * extends android.content.ContentProvider
	-keep public class * extends android.app.backup.BackupAgentHelper
	-keep public class * extends android.preference.Preference
	-keep public class com.android.vending.licensing.ILicensingService
	
	-keepclasseswithmembernames class * {
	    native <methods>;
	}
	
	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet);
	}
	
	-keepclasseswithmembernames class * {
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	}
	
	
	-keepclassmembers enum * {
	    public static **[] values();
	    public static ** valueOf(java.lang.String);
	}
	
	-keep class * implements android.os.Parcelable {
	  public static final android.os.Parcelable$Creator *;
	}
	
	-keepattributes InnerClasses
	
	-keep class com.tencent.bugly.**{*;}
	-keep class com.tencent.stat.**{*;}
	-keep class com.tencent.smtt.**{*;}
	-keep class com.tencent.beacon.**{*;}
	-keep class com.tencent.mm.**{*;}
	-keep class com.tencent.apkupdate.**{*;}
	-keep class com.tencent.tmassistantsdk.**{*;}
	-keep class org.apache.http.** { * ;}
	-keep class com.qq.jce.**{*;}
	-keep class com.qq.taf.**{*;}
	-keep class com.tencent.connect.**{*;}
	-keep class com.tencent.map.**{*;}
	-keep class com.tencent.open.**{*;}
	-keep class com.tencent.qqconnect.**{*;}
	-keep class com.tencent.tauth.**{*;}
	-keep class com.tencent.feedback.**{*;}
	-keep class common.**{*;}
	-keep class exceptionupload.**{*;}
	-keep class mqq.**{*;}
	-keep class qimei.**{*;}
	-keep class strategy.**{*;}
	-keep class userinfo.**{*;}
	-keep class com.pay.**{*;}
	-keep class com.demon.plugin.**{*;}
	-keep class com.tencent.midas.**{*;}
	-keep class oicq.wlogin_sdk.**{*;}
	-keep class com.tencent.ysdk.**{*;}
	-keepclasseswithmembernames class * {
	    native <methods>;
	}
	
	-keep class com.tencent.ysdk.**{*;}		