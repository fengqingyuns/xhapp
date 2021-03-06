package com.example.demo.modules.menu.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.modules.user.entity.Menu;

public interface MenuService extends IService<Menu>{

    /**
     * 
     * @author lit
     * @desc:  获取用户菜单列表
     * @date:  2020年9月4日 下午7:40:51  
     *
     * @param id
     * @return
     */
   List<Menu> getUserMenuList(int id);
   
   boolean delMenuByPId(int id);
   
   List<Menu> getMenuList(int pid);
   
}
