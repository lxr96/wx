package com.pnt.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pnt.wechat.entity.ProcedureDocument;
import com.pnt.wechat.util.JsApiSignUtil;
import com.pnt.wechat.util.WechatUitl;


import net.sf.json.JSONObject;



/** 
* @ClassName: WeChatController 
* @Description: 微信公众平台开发模式 
* @author lxr 
* @date 2020年4月26日 下午3:00:28 
*  
*/
@Controller
public class WeChatController {
	
	@RequestMapping(value = "/wx",method = RequestMethod.GET)
	@ResponseBody
	public String wx(HttpServletRequest request,HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		System.out.println(signature+" "+timestamp+" "+nonce+" "+echostr);
		
		if(WechatUitl.check(timestamp, nonce, signature)) {
			System.out.println("接口配置成功！");
			return echostr;
		}else
			System.out.println("接口配置失败！");
			return null;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}

	/** 
		* @content 验证访问地址与微信公众号绑定的地址是否一致
		* @author LXR
		* @data 2020年4月26日 下午2:59:29
		* @param request
		* @param url
		* @return
		* @throws UnsupportedEncodingException
	*/
	@RequestMapping("/weixinScan.do")
	@ResponseBody
	public JSONObject getJsApi(HttpServletRequest request,@RequestParam String url) throws UnsupportedEncodingException {
		System.out.println("weixinScan.do");
		System.out.println("url:"+url);
		Map<String, String> map =new HashMap<String, String>();
		
		String jsapi_ticket = WechatUitl.getJsapiTicketCheckExist();
		System.out.println(jsapi_ticket+" "+url);
		//String jsapi_ticket = "";
		map = JsApiSignUtil.sign(jsapi_ticket, url);
		map.put("wxAppId", WechatUitl.APPID);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
		return json;
	}
	
	
	@RequestMapping("/success")
	@ResponseBody
	public String success(@RequestParam String name) {
		return "success:"+name;
		
	}
	
	@RequestMapping("/qrr")
	public String qrr(HttpServletRequest request,HttpServletResponse response,@RequestParam String name) {
		
		System.out.println("param:"+name);
		
		ProcedureDocument pd =new ProcedureDocument();//模拟返回数据
		pd.setDocumentCode("T-OP-PF-0-HED-002");
		pd.setVersionState("流通");
		pd.setVersion("B");
		pd.setVersionDate("2020-04-23");
		request.setAttribute("data", pd);
		request.setAttribute("hello", "success value");
		return "result";
		
	}
}
