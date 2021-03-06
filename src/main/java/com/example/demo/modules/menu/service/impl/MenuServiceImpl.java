package com.example.demo.modules.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.modules.menu.MainAppController;
import com.example.demo.modules.menu.dao.MenuDao;
import com.example.demo.modules.menu.service.MenuService;
import com.example.demo.modules.user.common.Constant;
import com.example.demo.modules.user.dao.MasterDao;
import com.example.demo.modules.user.entity.Menu;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,Menu> implements MenuService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MasterDao masterDao;
    @Override
    public List<Menu> getUserMenuList(int id) {
        // TODO Auto-generated method stub
    //    if(id == Constant.SUPER_ADMIN) {
            return getAllMenuList(false,id);
     //   }
    }
    /**
     * 
     * @author lit
     * @desc:   根据用户id获取所有菜单
     * @date:  2021年1月5日 下午8:58:30  
     *
     * @param userId
     * @return
     */
    
    /**
     * 
     * @author lit
     * @desc: 获取菜单并组合
     * @date:  2020年9月4日 下午8:27:54  
     *
     * @return
     */
    public List<Menu> getAllMenuList(boolean all,int id){
        
        List<Menu> roots = new ArrayList<>();
        List<Menu> menuList = null;
        QueryWrapper<Menu> wrapper = new QueryWrapper<Menu>();
        LOGGER.info("用户ID:{}", id);
        if(id != Constant.SUPER_ADMIN) {
            
            List<Integer> menuIdsByUserId = masterDao.queryMenuIdsByUserId(id);
            if(menuIdsByUserId == null || menuIdsByUserId.size() == 0) {
                return roots;
            }
            LOGGER.info("menuIdsByUserId:{}", menuIdsByUserId);
            wrapper.in("menu_id", menuIdsByUserId);
        }
        menuList  = menuDao.selectList(wrapper);
        Map<Integer,Menu> map = new HashMap<Integer,Menu>();
        
        if(menuList != null && menuList.size()>0) {
            
            for (Menu menu : menuList) {
                map.put(menu.getMenuId(), menu);
            }
            for (Menu menu : menuList) {
                if( !all && menu.getType() == 2) {
                   continue;
                }
                //判断是否是顶级节点
                if(menu.getpId() == 0) {
                    roots.add(menu);
                }else {
                    Menu pMeun = map.get(menu.getpId());
                    pMeun.getList().add(menu);
                }
            }
        }
        
        return roots;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean delMenuByPId(int id) {
        boolean f = false;
        Map map = new HashMap<>();
        map.put("menu_id", id);
       int byMap = menuDao.deleteByMap(map);
       
        if(byMap ==1) {
            f = true;
        }
        return f;
    }

    @Override
    public List<Menu> getMenuList(int pid) {
        List<Menu> list = menuDao.queryMenuList(pid);
        return list;
    }
}
