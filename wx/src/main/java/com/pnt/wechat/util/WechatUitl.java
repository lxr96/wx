package com.pnt.wechat.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
 
 

/** 
* @ClassName: WechatUitl 
* @Description: 微信公众帮助工具 
* @author lxr 
* @date 2020年4月24日 上午11:39:01 
*  
*/
public class WechatUitl {
	
	private static final String TOKEN = "lixiangrong";
	//个人公众号
    public static final String APPID = "wxabc7047404b43806";
    public static final String APP_SECRET = "1aee87c46773d7d7d543e033cd1e185b";

    private static final Map<String, Object> map = new HashMap<String, Object>();
	
	 public static void main(String[] args) {
	
	    };
	/** 
		* @content token验证密钥的sha1算法
		* @author LXR
		* @data 2020年4月24日 上午11:38:29
		* @param str
		* @return
	*/
	public  static String sha1(String str) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("sha1");
			
			byte[] digest = md.digest(str.getBytes());
			char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			StringBuilder sb = new StringBuilder();
			
			for(byte b:digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			}
			return sb.toString();
 		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/** 
		* @content 验证是否微信发来的请求
		* @author LXR
		* @data 2020年4月24日 上午11:40:13
		* @param timestamp
		* @param nonce
		* @param signature
		* @return
	*/
	public static boolean check(String timestamp,String nonce,String signature) {
		String[] strs = new String[] {TOKEN,timestamp,nonce};
		Arrays.sort(strs);
		String str = strs[0]+strs[1]+strs[2];
		String mysign = sha1(str);
		System.out.println(mysign);
		System.out.println(signature);
		if(signature!=null&signature.equals(mysign)) {
			return true;
		}
		return false;
	}

    /**
      * 获取accessToken
      * @return accessToken
      */
    public static String getAccess_token(String wxAppId, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ wxAppId +"&secret=" + secret;
        //String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxabc7047404b43806&secret=1aee87c46773d7d7d543e033cd1e185b";
        JSONObject jsonobject = HttpClientUtil.sendGet(url, null);
        System.out.println("access_token:"+jsonobject.getString("access_token"));
        return jsonobject.getString("access_token");
     }

    /**
      * 获取jsapi_ticket
      * @author WFJ
      * @time 2017年4月4日21:50:52
      * @return
      */
    public static String getJsapiTicket(){
        String accessToken = "";
        accessToken = getAccess_token(APPID, APP_SECRET);
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
        //String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+accessToken+"&type=jsapi";
        JSONObject jsonobject = HttpClientUtil.sendGet(url, null);
        System.out.println("ticket:"+jsonobject.getString("ticket"));
        return jsonobject.getString("ticket");
    }
    
    /**
     * 若大于7200s后才获取jsapi_ticket
     * @author LXR
     * @time 2020年04月24日
     * @return
     */
    public static String getJsapiTicketCheckExist(){
    	
    	 String jsapi_ticket = "";
         Long timeJt = (Long) map.get("timeJt");
         jsapi_ticket = (String) map.get("jsapi_ticket");
         Long nowDateJt = new Date().getTime();
         if (jsapi_ticket != null && timeJt != null && ((nowDateJt - timeJt) < 7200 * 1000L)) {
             System.out.println("jsapi_ticket 存在"+jsapi_ticket+" "+timeJt);
             System.out.println("nowDateJt - timeJt=="+(nowDateJt - timeJt));
         } else {
             jsapi_ticket = getJsapiTicket();
             System.out.println("jsapi_ticket 获取"+" "+jsapi_ticket+" "+nowDateJt);
             map.put("jsapi_ticket",jsapi_ticket);
             map.put("timeJt",nowDateJt);
         }
         return jsapi_ticket;
    }
}
