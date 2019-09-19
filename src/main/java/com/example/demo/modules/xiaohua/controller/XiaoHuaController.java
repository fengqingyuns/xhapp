package com.example.demo.modules.xiaohua.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.modules.util.AJAXResult;
import com.example.demo.modules.xiaohua.entity.Joke;

@Controller
public class XiaoHuaController {
	private static final Logger LOGGER = LoggerFactory.getLogger(XiaoHuaController.class);
	
	@Autowired
	private JdbcTemplate jdbc;
	/** 连接超时时间，毫秒 */
	protected static final int HTTP_CONN_TIME_OUT = 10000;
	/** 读取超时时间，毫秒 */
	protected static final int HTTP_SOCKET_TIME_OUT = 20000;
	
	@RequestMapping("/xiaohua")
	public String toXiaoHua() {
		return "/xiaohua/xiaohua";
	}
	
	@RequestMapping("/getJokes")
	@ResponseBody
	public AJAXResult getJokes(String pagesize,String pageno) {
		AJAXResult result = new AJAXResult();
		try {
			String sql = "select * from t_joke limit ?,? ";
			Object[] obj = new Object[2];
			 int[] argTypes = new int[]{java.sql.Types.INTEGER,java.sql.Types.INTEGER} ; 
			obj[0] = pageno;obj[1] = pagesize;
			RowMapper<Joke> rowMapper = new BeanPropertyRowMapper<Joke>(Joke.class);
			List<Joke> list = jdbc.query(sql, obj, argTypes, rowMapper);
			LOGGER.info("joke: {}", list);
			result.setData(list);
			result.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("error :{}",e);
			result.setMsg("error");
			result.setSuccess(false);
		}
		
		return result;
	}
	
}
