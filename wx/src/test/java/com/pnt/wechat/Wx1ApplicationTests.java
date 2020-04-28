package com.pnt.wechat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.pnt.wechat.util.WechatUitl;


@SpringBootTest
class Wx1ApplicationTests {
	
	private Logger logger = LoggerFactory.getLogger(Wx1ApplicationTests.class);

	@Test
	void contextLoads() {
		//String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxabc7047404b43806&secret=1aee87c46773d7d7d543e033cd1e185b";
		//String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=32_3bK9cUEzPuHSb9LPXQuG-xBFA67Arv15qU4dN8_oZZHKhIs9lPkQtP1hb9Aryb7soMWF6MjtjYjBldKBzhFpzPVYw3oxfJWtGJj5RdkSUNjTKIsmNTiPA8BFEu4wYlhLdroVJJKaKODchggjRPTjAJALJN&type=jsapi";
		//System.out.println(HttpClientUtil.sendGet(url, null).toString());
		//System.out.println("test:"+WechatUitl.getJsapiTicket());
		//System.out.println("test:"+WechatUitl.getJsapiTicketCheckExist());
	}
	
	@Test
	public void testLog() {
		
	logger.trace("这是 trace 级别");
	logger.debug("这是 debug 级别");
	logger.info("这是 info 级别");
	logger.warn("这是 warn 级别");
	logger.error("这是 error 级别");
	}

}
