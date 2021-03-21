package com.example.demo.modules.user.controller;

import static com.example.demo.common.ResultStatus.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.modules.controller.AbstractController;
import com.example.demo.modules.shiro.service.ShiroService;
import com.example.demo.modules.user.dao.UserDao;
import com.example.demo.modules.user.entity.Role;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.service.RoleService;
import com.example.demo.modules.user.service.UserService;
import com.example.demo.modules.util.MD5Util;
import com.example.demo.modules.util.PageResult;
import com.example.demo.modules.util.R;
import com.example.demo.util.DataReq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import static com.example.demo.common.ResultStatus.*;
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ShiroService shiroService;
    @RequestMapping("/index")
    public String userIndex() {
        return "user/index";
    }
    
    @RequestMapping("/userList")
    @RequiresPermissions("app:user:select")
    @ResponseBody
    public R getUserList(Integer page,Integer size,String queryText) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if( StringUtils.isNoneBlank(queryText) ) {
            wrapper.eq("loginacct", queryText);
        }
        PageHelper.startPage(page,size);
        List<User> list = userService.list(wrapper);
        PageInfo<User> pages = new PageInfo<>(list);
       /* PageHelper.startPage(page,size);  
        List<User> kindList = userService.list();
        PageInfo<User> pages = new PageInfo<>(kindList);*/
        LOGGER.info("page : {}", pages);
        LOGGER.info("list : {}", pages.getList());
        LOGGER.info("pageNum : {}", pages.getPageNum());
        LOGGER.info("pageSize:{}", pages.getPageSize());
        LOGGER.info("total:{}", pages.getTotal());
        PageResult pageResult = PageResult.pageResult(pages);
        return R.page(pageResult).put("list", list);
      //  return R.ok().put("arr", arr).put("kindList", kindList);
    }
    
    
    
    @RequestMapping("/assign")
    public String assign(Integer id,String token,Model model) {
        model.addAttribute("userid", id);
        return "user/assign";
    }
    
    @RequestMapping("/assignList")
    @ResponseBody
    @RequiresPermissions("app:user:select")
    public R assignList(Integer userid) {
        //查询所有角色数据
        List<Role> roles = roleService.list();
        //未分配集合
        List<Role> unAssignList = new ArrayList<>();
        //已分配
        List<Role> assignList = new ArrayList<>();
        //查询已分配角色
        List<Integer> ids = userService.roleIds(userid);
        for (Role role : roles) {
            if(ids.contains(role.getRoleId())) {
                assignList.add(role);
            }else {
                unAssignList.add(role);
            }
        }
        LOGGER.info("roles:{}", roles);
        LOGGER.info("ids:{}", ids);
        LOGGER.info("unAssignList:{}", unAssignList);
        LOGGER.info("assignList:{}", assignList);
        
        return R.ok().put("assignList", assignList).put("unAssignList", unAssignList);
    }
    
    @RequestMapping("/del")
    @ResponseBody
    @RequiresPermissions("app:user:delete")
    public R delUser(Integer id) {
        if(id == 1){
            return R.error(PERMISSION_ERROR.getMsg());
        }
        User byId = userService.getById(id);
        if(!byId.getType().equals("会员") && byId.getCreateUserId() != getUserId()) {
            return R.error(NOT_CREATE_USER.getMsg());
        }
        boolean removeById = userService.removeById(id);
        userDao.delUserRole(id);
        if(removeById) {
            return R.ok(SUCCESS.getMsg());
        }else {
            return R.error(FAILD.getMsg());
        }
    }
    /**
     * 
     * @author lit
     * @desc: 分配角色
     * @date:  2021年1月8日 下午6:08:58  
     *
     * @param req
     * @return
     */
    @RequestMapping("/assignRole")
    @ResponseBody
    @RequiresPermissions("app:user:update")
    public R assignRole(DataReq req) {
        
        if(req != null && req.getIds().length>0) {
            boolean assignRole = userService.assignRole(req.getIds(),req.getPuserid(),req.getUserid());
            if(!assignRole) {
                return R.error(PERMISSION_ERROR.getMsg());
        }}
       return R.ok();
    }
    
    @RequestMapping("/unassignRole")
    @ResponseBody
    @RequiresPermissions("app:user:update")
    public R unassignRole(DataReq req) {
        boolean unAssignRole = userService.unAssignRole(req.getIds(),req.getPuserid(),req.getUserid());
        if(!unAssignRole) {
            return R.error(PERMISSION_ERROR.getMsg());
        }else {
            return R.ok();
        }
    }
    @RequestMapping("/toadd")
    public String toadd() {
        Integer id = getUserId();
        Set<String> perms = shiroService.getUserPermissions(id);
        if(perms != null && perms.size() >0 && perms.contains("app:user:add")) {
            return "user/add";
        }
        return "error";
    }
    
    @RequestMapping("/add")
    @ResponseBody
    public R add(User user) {
        if(user == null) {
            return R.error(USER_NOT_EXIST.getMsg());
        }
        int createuserId = getUserId();
        user.setCreatetime(new Date());
        user.setCreateUserId(createuserId);
        user.setStatus(1);
        user.setUserpswd(MD5Util.digest("123456"));
        boolean save = userService.save(user);
        if(!save) {
            return R.error(SYSTEM_DB_ERROR.getMsg());
        }
        return R.ok();
    }
    
}
