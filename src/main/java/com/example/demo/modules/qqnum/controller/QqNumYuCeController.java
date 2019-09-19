package com.example.demo.modules.qqnum.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.config.KeyConfig;
import com.example.demo.config.UrlConfig;
import com.example.demo.http.HttpClient;
import com.example.demo.http.HttpClientResponse;
import com.example.demo.modules.qqnum.entity.QqForecast;
import com.example.demo.modules.util.AJAXResult;

import net.sf.json.JSONObject;

@Controller
public class QqNumYuCeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(QqNumYuCeController.class);
	@Autowired
	private JdbcTemplate jdbc;
	/** 连接超时时间，毫秒 */
	protected static final int HTTP_CONN_TIME_OUT = 10000;
	/** 读取超时时间，毫秒 */
	protected static final int HTTP_SOCKET_TIME_OUT = 20000;

	@RequestMapping("/qqforecast")
	public String toXiaoHua() {
		return "/qqforecast/qqforecast";
	}

	 
	@RequestMapping("/getqqforecast")
	@ResponseBody
	public AJAXResult qqforecast(String qqNum) {
		AJAXResult result = new AJAXResult();
		String key = KeyConfig.QQ_NUM_FORECAST_KEY;
		String url = UrlConfig.QQ_FORECAST_URL;
		QqForecast qq = null;
		if (StringUtils.isEmpty(qqNum)) {
			result.setSuccess(false);
			result.setMsg("qqNum为空");
			return result;
		}
		try {
			qq = getDbQqForecast(qqNum);
			if (qq == null) {
				result = getQqForecast(url, qqNum, key);
			}else {
				result.setMsg("获取数据成功");
				result.setSuccess(true);
				result.setData(qq);
			}
		} catch (Exception e) {
			LOGGER.error("66行获取数据失败:{}", e);
			result.setSuccess(false);
			result.setMsg("获取数据失败");
		}
		
		return result;
	}

	public QqForecast getDbQqForecast(String qqNum) {
		QqForecast qq = null;
		try {
			String sql = "select * from t_qq where qq_id=?";
			Object[] obj = new Object[1];
			obj[0] = qqNum;
			int[] argTypes = new int[] { java.sql.Types.VARCHAR };
			RowMapper<QqForecast> rowMapper = new BeanPropertyRowMapper<QqForecast>(QqForecast.class);
			qq = jdbc.queryForObject(sql, obj, argTypes, rowMapper);
			LOGGER.info("数据库有数据:{}", qq);
		
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("不做处理因为数据库数据为空");
		}
		
		return qq;
	}
	
	public AJAXResult getQqForecast(String url, String qqNum, String key) {
		AJAXResult result = new AJAXResult();
		QqForecast qq = new QqForecast();
		NameValuePair[] data = { //
				new BasicNameValuePair("qq", qqNum), new BasicNameValuePair("key", key) };
		try {
			HttpClientResponse response = doRequest(url, data);
			LOGGER.info("response:{}", response);
			String body = response.getBody();
			setQq(qq, body, qqNum);
			insertQq(qq);
			result.setMsg("获取数据成功");
			result.setSuccess(true);
			result.setData(qq);
		} catch (Exception e) {
			LOGGER.error("获取数据失败:{}", e);
			result.setMsg("获取数据失败");
			result.setSuccess(false);
		}
		return result;
	}
	public void insertQq(QqForecast qq) {//\\
		String sql = "insert into t_qq(qq_id,conclusion,analysis) values(?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = qq.getQq_id();
		obj[1] = qq.getConclusion();
		obj[2] = qq.getAnalysis();
		int[] argTypes = new int[] { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR };
		jdbc.update(sql, obj, argTypes);
	}
	public void setQq(QqForecast qq, String body, String qqNum) {
		JSONObject object = JSONObject.fromObject(body);
		JSONObject jo = object.getJSONObject("result");
		JSONObject ob = jo.getJSONObject("data");

		qq.setQq_id(qqNum);
		qq.setErrorCode(Integer.parseInt(object.get("error_code").toString()));
		qq.setReason(object.getString("reason"));
		qq.setConclusion(ob.getString("conclusion"));
		qq.setAnalysis(ob.getString("analysis"));
	}

	protected HttpClientResponse doRequest(String url, NameValuePair[] data) {
		HttpClientResponse hcres = null;
		try {

			LOGGER.info("url:{}", url);
			//http client time out conn socket time out url socket time
			HttpClient httpClient = HttpClient.post(url);
			httpClient.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);

			httpClient.addParameters(data);
			hcres = httpClient.action();
		} catch (Exception e) {
			LOGGER.error("error", e);

		}
		return hcres;
	}
}
