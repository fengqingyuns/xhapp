package com.example.demo.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.user.dao.MasterGroupDao;
import com.example.demo.modules.user.entity.Group;
import com.example.demo.modules.user.entity.MasterGroup;
import com.example.demo.modules.user.service.MasterGroupService;
/*@Service
public class MasterGroupServiceImpl implements MasterGroupService{

    @Autowired
    private MasterGroupDao masterGroupDao;
   
    @Override
    public void saveOrUpdate(int masterId, List<Group> groupList) {
        masterGroupDao.deleteByMasterId(masterId);
        List<MasterGroup> masterGroupList = new ArrayList<>();
        MasterGroup temp = new MasterGroup();
        for (MasterGroup masterGroup : masterGroupList) {
            temp.setGroupId(masterGroup.getGroupId());
            temp.setMasterId(masterId);
            masterGroupList.add(temp);
        }
        masterGroupDao.save(masterGroupList);
    }

}
*/