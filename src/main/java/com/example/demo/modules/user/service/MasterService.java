package com.example.demo.modules.user.service;

import java.util.List;

import com.example.demo.modules.user.entity.Master;

public interface MasterService {
    List<Master> select();
    Master selectOne(Master master);
    void save(Master master);
    void delete(Master master);
    void update(Master master);
}
