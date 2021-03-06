package com.example.demo.modules.shiro.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.menu.MainAppController;
import com.example.demo.modules.menu.dao.MenuDao;
import com.example.demo.modules.shiro.dao.UserTokenDao;
import com.example.demo.modules.shiro.service.ShiroService;
import com.example.demo.modules.user.common.Constant;
import com.example.demo.modules.user.dao.MasterDao;
import com.example.demo.modules.user.entity.Menu;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.entity.UserToken;
@Service
public class ShiroServiceImpl implements ShiroService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroServiceImpl.class);
    @Autowired
    private UserTokenDao UserTokenDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MasterDao masterDao;
    @Override
    public Set<String> getUserPermissions(Integer userId) {
            List<String> permsList;
        //判断是否是系统管理员
        if(userId == Constant.SUPER_ADMIN) {
            List<Menu> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            permsList.addAll(menuList.stream().map(Menu::getPerms).collect(Collectors.toList()));
        }else{
            permsList = masterDao.queryAllPerms(userId);
            LOGGER.info("permsList", permsList);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if(StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public UserToken queryByToken(String accessToken) {
        // TODO Auto-generated method stub
        UserToken userToken = UserTokenDao.queryByToken(accessToken);
        return userToken;
    }

    @Override
    public User queryUser(Integer userId) {
        // TODO Auto-generated method stub
        User user = UserTokenDao.queryById(userId);
        return user;
    }

}
