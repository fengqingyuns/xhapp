package com.example.demo.modules.xiaohua.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="zgdream",type="dream",indexStoreType="fs",shards=2,replicas=1,refreshInterval="-1")
public class Dream {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String title;
	@Field(type=FieldType.Text)
	private String des;
	@Field(type=FieldType.Text)
	private String content;
	@Field(type=FieldType.Date)
	private Date time;
	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Dream [title=" + title + ", des=" + des + ", content=" + content + ", time=" + time + "]";
	}


	
	
	
}
