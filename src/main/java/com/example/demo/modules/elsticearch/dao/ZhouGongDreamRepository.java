package com.example.demo.modules.elsticearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modules.xiaohua.entity.Dream;
public interface ZhouGongDreamRepository extends ElasticsearchRepository<Dream,String>{

}
