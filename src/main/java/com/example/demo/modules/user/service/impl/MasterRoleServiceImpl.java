package com.example.demo.modules.user.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.user.dao.MasterRoleDao;
import com.example.demo.modules.user.entity.MasterRole;
import com.example.demo.modules.user.entity.Role;
import com.example.demo.modules.user.service.MasterRoleService;

/*@Service
public class MasterRoleServiceImpl implements MasterRoleService {

    @Autowired
    private MasterRoleDao masterRoleDao;

    @Override
    public void saveOrUpdate(int masterId, List<Role> roleList) {
        masterRoleDao.deleteByMasterId(masterId);
        List<MasterRole> masterRoleList = new ArrayList<>();
        MasterRole temp = new MasterRole();
        for (Role role : roleList) {
            temp.setRoleId(role.getRoleId());
            temp.setMasterId(masterId);
            masterRoleList.add(temp);
        }
        masterRoleDao.saveOrUpdate(masterRoleList);
    }

}
*/