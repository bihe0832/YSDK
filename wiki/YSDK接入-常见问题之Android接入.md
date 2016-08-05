## YSDK接入常见问题之Android 接入

## 1. 登录相关

### 1.1. YSDK登录失败，提示client request's app is not existed

游戏登录的时候能成功授权，但是授权回来不能收到成功回调，log日志显示{"ret":-2,"msg":"client request's app is not existed"}。该问题为游戏没有YSDK对应环境权限，权限开通请参考：[http://wiki.open.qq.com/wiki/YSDK%E4%BB%8B%E7%BB%8D#4._YSDK.E7.8E.AF.E5.A2.83.E6.9D.83.E9.99.90](http://wiki.open.qq.com/wiki/YSDK%E4%BB%8B%E7%BB%8D#4._YSDK.E7.8E.AF.E5.A2.83.E6.9D.83.E9.99.90)
		
### 1.2. YSDK登录拉起手Q以后弹框100044、110404或者110406，尤其MSDK切换游戏比较容易遇到

游戏在拉起手Q授权的界面弹框，多是因为流程或者权限原因引起。该问题可以参照YSDK wiki中关于手Q登录异常检查步骤的第一步中给出的方法去定位解决。具体地址为：[http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E6.89.8BQ.E7.95.8C.E9.9D.A2.E5.BC.B9.E6.A1.86.E6.8A.A5.E9.94.99](http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E6.89.8BQ.E7.95.8C.E9.9D.A2.E5.BC.B9.E6.A1.86.E6.8A.A5.E9.94.99)

### 1.3. YSDK登录微信拉不起来微信授权

游戏在登录时拉不起来微信一般是因为签名问题引起，可以参照YSDK wiki中关于微信登录不了检查步骤的顺序逐步检查。具体地址为：
[http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.2_.E5.BE.AE.E4.BF.A1.E7.99.BB.E5.BD.95.E4.B8.8D.E4.BA.86.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4](http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.2_.E5.BE.AE.E4.BF.A1.E7.99.BB.E5.BD.95.E4.B8.8D.E4.BA.86.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4)

### 1.4. 手Q登录异常检查步骤（手Q界面报错、没有回调等）

游戏可以按照下面的检查步骤检查关于YSDK的手Q登录相关的内容是否正确，生命周期重点检查onCreate和onActivityResult的调用。

[http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.1_.E6.89.8BQ.E7.99.BB.E5.BD.95.E5.BC.82.E5.B8.B8.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4.EF.BC.88.E6.89.8BQ.E7.95.8C.E9.9D.A2.E6.8A.A5.E9.94.99.E3.80.81.E6.B2.A1.E6.9C.89.E5.9B.9E.E8.B0.83.E7.AD.89.EF.BC.89](http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.1_.E6.89.8BQ.E7.99.BB.E5.BD.95.E5.BC.82.E5.B8.B8.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4.EF.BC.88.E6.89.8BQ.E7.95.8C.E9.9D.A2.E6.8A.A5.E9.94.99.E3.80.81.E6.B2.A1.E6.9C.89.E5.9B.9E.E8.B0.83.E7.AD.89.EF.BC.89)

### 1.5. 微信登录不了检查步骤（拉不起微信、微信无回调等）

游戏可以按照下面的检查步骤检查关于YSDK的手Q登录相关的内容是否正确，生命周期重点检查handleIntent的调用。

[http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.2_.E5.BE.AE.E4.BF.A1.E7.99.BB.E5.BD.95.E4.B8.8D.E4.BA.86.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4](http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#7.2_.E5.BE.AE.E4.BF.A1.E7.99.BB.E5.BD.95.E4.B8.8D.E4.BA.86.E6.A3.80.E6.9F.A5.E6.AD.A5.E9.AA.A4)

### 1.6. YSDK自动登录

为了保证提供给游戏的票据的有效性，YSDK会在游戏启动或者运行期间定时触发自动登录，具体关于自动登录相关的说明可以参考YSDK Wiki中登录模块关于登陆模块概述中的内容，对应地址为：[http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#4.1_.E6.A6.82.E8.BF.B0](http://wiki.open.qq.com/wiki/%E5%BE%AE%E4%BF%A1%E4%B8%8E%E6%89%8BQ%E6%8E%A5%E5%85%A5#4.1_.E6.A6.82.E8.BF.B0)

## 支付相关

### 2.1 点击支付无法成功唤起支付界面

目前YSDK集成米大师的插件版，支付需要同时依赖腾讯充值的APP，游戏可以参照YSDK的wiki中关于支付时无法唤起支付界面的方法定位，具体地址为：[http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.A1.AE.E8.AE.A4.E6.98.AF.E5.90.A6.E8.83.BD.E5.A4.9F.E6.88.90.E5.8A.9F.E6.8B.89.E8.B5.B7.E6.94.AF.E4.BB.98.E7.95.8C.E9.9D.A2](http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.A1.AE.E8.AE.A4.E6.98.AF.E5.90.A6.E8.83.BD.E5.A4.9F.E6.88.90.E5.8A.9F.E6.8B.89.E8.B5.B7.E6.94.AF.E4.BB.98.E7.95.8C.E9.9D.A2)

### 2.2 点击支付报错1001-1007-0

出现该错误一般是因为游戏沙箱环境或者大区id传递错误，具体可以参考YSDK的wiki中关于支付相关错误码的说明。具体地址为：[http://wiki.open.qq.com/wiki/Android%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF%E7%A0%81%E6%9F%A5%E8%AF%A2#4.3_.E9.94.99.E8.AF.AF.E7.A0.811001-1007-0](http://wiki.open.qq.com/wiki/Android%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF%E7%A0%81%E6%9F%A5%E8%AF%A2#4.3_.E9.94.99.E8.AF.AF.E7.A0.811001-1007-0)

其中游戏可以参照以下YSDK的wiki中关于支付时参数错误定位的方法来验证对应参数是否正确。具体地址为：[http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E6.A3.80.E6.9F.A5.E6.B8.B8.E6.88.8F.E6.94.AF.E4.BB.98.E7.9B.B8.E5.85.B3.E9.85.8D.E7.BD.AE.E6.98.AF.E5.90.A6.E6.AD.A3.E7.A1.AE](http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E6.A3.80.E6.9F.A5.E6.B8.B8.E6.88.8F.E6.94.AF.E4.BB.98.E7.9B.B8.E5.85.B3.E9.85.8D.E7.BD.AE.E6.98.AF.E5.90.A6.E6.AD.A3.E7.A1.AE)

### 2.3 点击支付时报错1016

出现该错误一般是因为游戏支付相关配置没有同步或者权限没有开通，具体可以参考YSDK的wiki中关于支付相关错误码的说明。具体地址为：[http://wiki.open.qq.com/wiki/Android%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF%E7%A0%81%E6%9F%A5%E8%AF%A2#1._.E9.94.99.E8.AF.AF.E7.A0.81.EF.BC.9A1016-1016-0](http://wiki.open.qq.com/wiki/Android%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF%E7%A0%81%E6%9F%A5%E8%AF%A2#1._.E9.94.99.E8.AF.AF.E7.A0.81.EF.BC.9A1016-1016-0)

其中游戏可以参照以下YSDK的wiki中关于支付时参数错误定位的方法来验证对应参数是否正确。具体地址为：[http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E6.A3.80.E6.9F.A5.E6.B8.B8.E6.88.8F.E6.94.AF.E4.BB.98.E7.9B.B8.E5.85.B3.E9.85.8D.E7.BD.AE.E6.98.AF.E5.90.A6.E6.AD.A3.E7.A1.AE](http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E6.A3.80.E6.9F.A5.E6.B8.B8.E6.88.8F.E6.94.AF.E4.BB.98.E7.9B.B8.E5.85.B3.E9.85.8D.E7.BD.AE.E6.98.AF.E5.90.A6.E6.AD.A3.E7.A1.AE)


### 2.4 充值时或者游戏启动时提示腾讯充值已停止运行

目前YSDK集成米大师的插件版，支付需要同时依赖腾讯充值的APP，目前腾讯充值存在部分兼容性问题，游戏可以根据wiki看是否已知问题，具体地址为：[http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.A1.AE.E8.AE.A4.E6.98.AF.E5.90.A6.E8.83.BD.E5.A4.9F.E6.88.90.E5.8A.9F.E6.8B.89.E8.B5.B7.E6.94.AF.E4.BB.98.E7.95.8C.E9.9D.A2](http://wiki.open.qq.com/wiki/%E6%94%AF%E4%BB%98%EF%BC%88Midas%EF%BC%89%E6%8E%A5%E5%85%A5#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.A1.AE.E8.AE.A4.E6.98.AF.E5.90.A6.E8.83.BD.E5.A4.9F.E6.88.90.E5.8A.9F.E6.8B.89.E8.B5.B7.E6.94.AF.E4.BB.98.E7.95.8C.E9.9D.A2)

