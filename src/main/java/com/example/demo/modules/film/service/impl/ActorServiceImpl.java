package com.example.demo.modules.film.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.film.dao.ActorDao;
import com.example.demo.modules.film.entity.Actor;
import com.example.demo.modules.film.service.ActorService;
@Service
public class ActorServiceImpl extends ServiceImpl<ActorDao, Actor> implements ActorService{

}
