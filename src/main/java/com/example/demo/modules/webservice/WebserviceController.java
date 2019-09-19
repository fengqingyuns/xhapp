package com.example.demo.modules.webservice;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.example.demo.http.HttpClient;
import com.example.demo.http.HttpClientResponse;

@Controller
public class WebserviceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebserviceController.class);
	/** 连接超时时间，毫秒 */
	protected static final int HTTP_CONN_TIME_OUT = 10000;
	/** 读取超时时间，毫秒 */
	protected static final int HTTP_SOCKET_TIME_OUT = 20000;
	
	
	
	protected HttpClientResponse doRequest(String url, NameValuePair[] data) {
		HttpClientResponse hcres = null;
		try {
			LOGGER.info("url:{}", url);

			HttpClient httpClient = HttpClient.post(url);
			httpClient.setHeader("Content-Type","text/xml");
		
			httpClient.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);
			httpClient.addParameters(data);
			hcres = httpClient.action();
		} catch (Exception e) {
			LOGGER.error("error", e);
		}
		return hcres;
	}
}
