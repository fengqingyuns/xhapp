package com.example.demo.modules.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.modules.permission.entity.Permission;
import com.example.demo.modules.permission.service.PermissionService;
import com.example.demo.modules.util.R;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping("/index")
    public String index() {
        return "permission/index";
    }
    
    @RequestMapping("/permissionlist")
    @ResponseBody
    public R permissionlist() {
        
        /*
         * 先将所有permission放入到map. 
         * kay:id      ;   value:permission
         */
        //先将数据全部查出来
        List<Permission> listPermissions = permissionService.list();
        //定义一个roots集合【】
        List<Permission> roots = new ArrayList<Permission>();
        //定义一个map来装permission
        Map<Integer,Permission> map = new HashMap<Integer,Permission>();
        //把数据放入map
        for (Permission permission : listPermissions) {
            map.put(permission.getId(), permission);
        }
        //寻找父子关系
        for (Permission permission : listPermissions) {
            //子菜单
            Permission child = permission;
            //如果子菜单的pid==0则说明是第一个菜单
            if(child.getPid()==0){
                roots.add(child);
                //否则继续寻找child的父菜单
            }else{
                Permission parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return R.ok().put("data",roots );
    }
    @ResponseBody
    @RequestMapping("/loadAJAXDatas")
    public R loadAJAXDatas() {
        
        
        List<Permission> permissions = permissionService.list();
        List<Permission> roots = new ArrayList<Permission>();
        Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
        
        for ( Permission permission : permissions ) {
            permissionMap.put(permission.getId(), permission);
        }
        
        for ( Permission permission : permissions ) {
            Permission child = permission;
            if ( child.getPid() == 0 ) {
                roots.add(permission);
            } else {
                Permission parent = permissionMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return R.ok().put("data", roots);
    }
    
}
