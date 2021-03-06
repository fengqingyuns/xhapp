package com.example.demo.modules.elsticearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.init.Post;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
}
