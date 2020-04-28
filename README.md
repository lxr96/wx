# wx
微信公众号，使用JSSDK开发
## 发微信公众号平台，页面调用JSSDK扫一扫功能
#使用springboot启动项目
#使用STS开发工具

## 如何获取jsapi_ticket

 1. https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
 2. https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET

## 调用JSSDK

 1. 在html引用http://res.wx.qq.com/open/js/jweixin-1.6.0.js
 2. 配置wx.config 方法的参数 appId,timestamp,nonceStr,signature,jsApiList
 
timestamp、noceStr是系统生成的随机参数,
signature这个值是由微信算法生成的,
算法的参数由jsapi_ticket（通过access_token微信api获取的凭证，有效期2个小时），https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi
url（当前页面的地址，如http://baidu.com/wx）
  
