package com.example.demo.modules.user.service;

import java.util.List;

import com.example.demo.modules.user.entity.Group;

public interface MasterGroupService {

    void saveOrUpdate(int masterId,List<Group> groupList);
}
