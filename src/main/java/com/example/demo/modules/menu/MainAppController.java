package com.example.demo.modules.menu;

import java.util.List;
import java.util.Set;
import javax.websocket.server.PathParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.modules.controller.AbstractController;
import com.example.demo.modules.menu.service.MenuService;
import com.example.demo.modules.shiro.service.ShiroService;
import com.example.demo.modules.user.entity.Menu;
import com.example.demo.modules.util.PageResult;
import com.example.demo.modules.util.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import static com.example.demo.common.ResultStatus.*;
/**
 * 菜单管理
 * @author lit
 *
 * @date:  2020年9月4日 下午7:25:15  
 *
 */
@Controller
@RequestMapping("/menu")
public class MainAppController extends AbstractController{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MainAppController.class);
    @Autowired
    private MenuService menuService;
    @Autowired
    private ShiroService shiroService;
    @RequestMapping("/main")
	public String toMain(String token) {
	    //根据accessToken，查询用户信息
		return "menu/main";
	}
    @RequestMapping("/menu")
    public String toMenu(String token) {
        //根据accessToken，查询用户信息
        return "menu/menu";
    }
    @RequestMapping("/index")
    public String index() {
        return "menu/index";
    }
    
    @RequestMapping("/toadd")
    @RequiresPermissions("app:menu:add")
    public String toadd() {
        return "menu/add";
    }
    
    /**
     * 
     * @author lit
     * @desc: 导航栏菜单
     * @date:  2020年9月4日 下午7:31:22  
     *
     * @return
     */
    @GetMapping("/nav")
    @ResponseBody
	public R nav() {
        
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        List<Menu> menuList = menuService.getUserMenuList(getUserId());
        PageInfo<Menu> page = new PageInfo<>(menuList);
        LOGGER.info("page : {}", page);
        LOGGER.info("list : {}", page.getList());
        LOGGER.info("pageNum : {}", page.getPageNum());
        LOGGER.info("pageSize:{}", page.getPageSize());
        LOGGER.info("total:{}", page.getTotal());
        return R.ok().put("menuList", menuList).put("permissions", permissions);
	}
	/**
	 * 
	 * @author lit
	 * @desc: 获取所有菜单
	 * @date:  2020年9月5日 下午8:09:30  
	 *
	 * @return
	 */
    @GetMapping("/list")
    @RequiresPermissions("app:menu:select")
    @ResponseBody
    public R allList(int page ,int size) {
        LOGGER.info("page : {}", page);
        PageHelper.startPage(page,size);  
        List<Menu> menuList = menuService.list(null);
        PageInfo<Menu> pages = new PageInfo<>(menuList);
        LOGGER.info("pages : {}", pages.getList());
        return R.page(PageResult.pageResult(pages)).put("list", menuList);
    }
	/**
	 * 
	 * @author lit
	 * @desc:根据菜单ID删除菜单
	 * @date:  2020年9月6日 下午12:59:26  
	 *
	 * @param id
	 * @return
	 */
    @RequestMapping("/delete")
    @RequiresPermissions("app:menu:delete")
    @ResponseBody
    public R deleteMenuById( Integer id) {
        int userId = getUserId();
        if(userId != 1) {
            return R.error(PERMISSION_ERROR.getMsg());
        }
        List<Menu> list = menuService.getMenuList(id);
        if(list != null && list.size()>0) {
            return R.error("请先删除子菜单");
        }
        boolean flag = menuService.delMenuByPId(id);
        LOGGER.info("删除状态 :{}", flag);
        return R.ok();
    }
    
    @RequestMapping("/change")
    @ResponseBody
    public R changeList(@RequestParam("menuId") Integer id) {
        
        return R.ok();
    }
    
    
}
