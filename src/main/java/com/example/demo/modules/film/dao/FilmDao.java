package com.example.demo.modules.film.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.modules.film.entity.Film;

@Mapper
public interface FilmDao extends BaseMapper<Film>{

}
