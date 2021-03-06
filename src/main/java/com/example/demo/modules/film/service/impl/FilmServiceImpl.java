package com.example.demo.modules.film.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.film.dao.FilmDao;
import com.example.demo.modules.film.entity.Film;
import com.example.demo.modules.film.service.FilmService;
@Service
public class FilmServiceImpl extends ServiceImpl<FilmDao, Film> implements FilmService{

}
