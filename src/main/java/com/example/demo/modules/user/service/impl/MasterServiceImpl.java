package com.example.demo.modules.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modules.user.common.Constant;
import com.example.demo.modules.user.dao.MasterDao;
import com.example.demo.modules.user.entity.Master;
import com.example.demo.modules.user.entity.Role;
import com.example.demo.modules.user.service.MasterGroupService;
import com.example.demo.modules.user.service.MasterRoleService;
import com.example.demo.modules.user.service.MasterService;
import com.example.demo.modules.user.service.RoleService;
/*@Service
public class MasterServiceImpl implements MasterService{

    @Autowired
    private MasterDao masterDao;
    @Autowired
    private MasterRoleService masterRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MasterGroupService masterGroupService;
    @Override
    public void save(Master master) {
        
        masterDao.save(master);
        checkRole(master);
        if(master.getGroupList() != null) {
            masterGroupService.saveOrUpdate(master.getMasterId(),master.getGroupList());
        }
        if(master.getRoleList() != null) {
            masterRoleService.saveOrUpdate(master.getMasterId(),master.getRoleList());
        }
       
    }

    @Override
    public void delete(Master master) {
        // TODO Auto-generated method stub
        masterDao.delete(master);
    }

    @Override
    public void update(Master master) {
        // TODO Auto-generated method stub
        masterDao.update(master);
    }

    @Override
    public List<Master> select() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Master selectOne(Master master) {
        // TODO Auto-generated method stub
        return null;
    }
   
    private void checkRole(Master master){
        if(master.getRoleList() == null || master.getRoleList().size() == 0){
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(master.getCreateMasterId() == Constant.SUPER_ADMIN){
            return ;
        }
        
        //查询用户创建的角色列表
        List<Role> roleList = roleService.queryRoleList(master.getCreateMasterId());
        List<Integer> roleIds = roleList.stream().map(Role::getRoleId).collect(Collectors.toList());
        //判断是否越权
        if(!roleIds.containsAll(master.getRoleList())){
            System.err.println("error");
        }
    }
}
*/