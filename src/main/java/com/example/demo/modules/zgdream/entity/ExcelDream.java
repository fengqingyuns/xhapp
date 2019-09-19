package com.example.demo.modules.zgdream.entity;

import java.util.Date;

public class ExcelDream {

	private String title;

	private String des;

	private Date time;

	
	public ExcelDream() {
		super();
	}

	public ExcelDream(String title, String des, Date time) {
		super();
		this.title = title;
		this.des = des;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}



	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ExcelDream [title=" + title + ", des=" + des + ", time=" + time + "]";
	}
	
	
}
