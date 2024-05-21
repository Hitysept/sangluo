package cn.sangluo.baseserver.controller.impl;

import cn.sangluo.baseserver.common.TreeBuildUtil;
import cn.sangluo.baseserver.controller.RoleController;
import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import cn.sangluo.baseserver.pojo.RolePojo;
import cn.sangluo.baseserver.pojo.UserPojo;
import cn.sangluo.baseserver.service.MenuService;
import cn.sangluo.baseserver.service.RoleService;
import cn.sangluo.baseserver.service.UserService;
import cn.sangluo.constant.ResponseMessageConstant;
import cn.sangluo.util.ResultJsonUtil;
import cn.sangluo.util.SangluoFoxUtil;
import cn.sangluo.util.SnowFlakeUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName RoleControllerImpl
 * @Description 权限操作实现类
 * @Author 月痕江离
 * @Date 2023/9/21 16:51
 * @Version 1.0
 */
@RestController
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;
    private final UserService userService;
    private final MenuService menuService;
    private final ResultJsonUtil<Object> resp = new ResultJsonUtil<>();
    @Autowired
    public RoleControllerImpl(RoleService roleService,UserService userService,MenuService menuService) {
        this.roleService = roleService;
        this.userService = userService;
        this.menuService = menuService;
    }
    /**
       * @MethodName saveRole
       * @Description  新增权限名
       * @param: roleName 权限名
       * @param: companyId 组织id
       * @param: updateUser 创建用户
       * @return cn.sangluo.util.ResultJsonUtil<java.lang.Object>
       * @Author 月痕江离
       * @Date 17:23 2023/10/7
       */
    @Override
    public ResultJsonUtil<Object> saveRole(Map<String,Object> paramInfo) throws DataAccessException{
        RolePojo rolePojo = JSON.parseObject(JSON.toJSONString(paramInfo), RolePojo.class);
        try {
            List<RolePojo> list = roleService.verifyRoleByNameAndCompanyId(rolePojo.getCompanyId(),rolePojo.getRoleName());
            if(SangluoFoxUtil.isEmptyList(list)){
                String time = String.valueOf(System.currentTimeMillis());
                rolePojo.setUpdateTime(time);
                SnowFlakeUtil worker=new SnowFlakeUtil(1,1,3);
                rolePojo.setRoleId(String.valueOf(worker.nextId()));
                rolePojo.setCreateTime(time);
                rolePojo.setCreator(rolePojo.getUpdateUser());
                rolePojo.setDeleteStatus(0);
                roleService.saveRole(rolePojo);
                resp.success(ResponseMessageConstant.REQUEST_SUCCESS);
            }else {
                resp.fail(ResponseMessageConstant.DATA_SAVE_ERROR);
            }
        }catch (DataAccessException e){
            final Throwable cause = e.getCause();
            resp.fail(ResponseMessageConstant.DATA_SAVE_FAIL + cause.getMessage());
        }
        return resp;
    }
    /***
       * @MethodName saveMenu
       * @Description  保存菜单
       * @param: menuName 菜单名
       * @param: menuCode 菜单代号
       * @param: menuUrl 菜单路由
       * @param: menuType 菜单类型 1为菜单 2为按钮
       * @param: menuLevel 菜单等级
       * @param: menuLevelCode 菜单等级代码
       * @param: parentId 父级id
       * @return: cn.sangluo.util.ResultJsonUtil<java.lang.Object>
       * @Author 月痕江离
       * @Date 18:21 2023/10/18
       */
    @Override
    public ResultJsonUtil<Object> saveMenu(Map<String,Object> paramInfo) throws DataAccessException {
        MenuPojo menuPojo = JSON.parseObject(JSON.toJSONString(paramInfo), MenuPojo.class);
        try{
            List<MenuPojo> list = menuService.getMenuCodeByMenuUrl(menuPojo.getPath());
            if(SangluoFoxUtil.isNotEmpty(menuPojo.getMenuId())) {
                menuService.upDataMenu(menuPojo);
            }else if(SangluoFoxUtil.isEmptyList(list)){
                SnowFlakeUtil worker=new SnowFlakeUtil(1,1,4);
                menuPojo.setMenuId(String.valueOf(worker.nextId()));
                menuPojo.setDeleteStatus(0);
                menuService.saveMenu(menuPojo);
                resp.success(ResponseMessageConstant.REQUEST_SUCCESS);
            }else{
                resp.fail(ResponseMessageConstant.DATA_SAVE_ERROR);
            }
        }catch (DataAccessException e){
            final Throwable cause = e.getCause();
            resp.fail(ResponseMessageConstant.DATA_SAVE_FAIL + cause.getMessage());
        }
        resp.success(ResponseMessageConstant.REQUEST_SUCCESS);
        return resp;
    }

    /**
       * verifyRoleByUserId
       * @Description 根据用户id查询该用户的角色集合
       * @param userId 用户id
       * @return cn.sangluo.util.ResultJsonUtil<java.lang.Object>
       * @Author: 月痕江离
       * @Date 17:17 2023/10/7
       */
    @Override
    public ResultJsonUtil<Object> verifyRoleByUserId(String userId) throws NullPointerException{
        List<String> roleList = new ArrayList<>();
        try{
            UserPojo userPojo = userService.getCompanyIdByUserId(userId);
            if(SangluoFoxUtil.isNotEmpty(userPojo)){
                String companyId = userPojo.getCompanyId();
                List<RolePojo> rolePojoList = roleService.verifyRoleBycompanyId(companyId);
                for (RolePojo item: rolePojoList) {
                    roleList.add(item.getRoleName());
                }
            }
            resp.success(roleList);
        }catch (NullPointerException e){
            final Throwable cause = e.getCause();
            resp.fail(ResponseMessageConstant.OAUTH_ACCOUNT_ERROR+","+cause.getMessage());
        }
        return resp;
    }
    /***
       * @MethodName getAllPermissionList
       * @Description  根据用户id查询菜单详细权限
       * @param: userId 用户id
       * @return: cn.sangluo.util.ResultJsonUtil<java.lang.Object>
       * @Author 月痕江离
       * @Date 0:55 2023/10/18
       */
    @Override
    public ResultJsonUtil<Object> getAllPermissionList(String userId) {
        return null;
    }

    @Override
    public ResultJsonUtil<Object> getDynamicMenus(String token) {
        UserPojo userPojo = userService.getUsersByToken(token);
        List<MenuRolePojo> menuList = menuService.getDynamicMenus(userPojo.getCompanyId());
        resp.success(menuList);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> getDynamicRouters(String token) {
        UserPojo userPojo = userService.getUsersByToken(token);
        List<MenuRolePojo> menuList = menuService.getDynamicRoutes(userPojo.getCompanyId());
        resp.success(menuList);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> getMenusList(int currPage, int pageSize) {
        List<MenuRolePojo> menuList = menuService.getMenusList(currPage, pageSize);
        Integer total = menuService.getMenusListTotal();
        Map<String,Object> reMap = new HashMap<>();
        reMap.put("List",menuList);
        reMap.put("total",total);
        reMap.put("currPage",currPage);
        reMap.put("pageSize",pageSize);
        resp.success(reMap);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> getMenusTreeList() {
        //通过用户id获取到role角色id
        List<MenuRolePojo> menuList = menuService.getMenusList();
        List<MenuRolePojo> reMenuList;
        // 创建树形结构（数据集合作为参数）
        TreeBuildUtil treeBuild = new TreeBuildUtil(menuList);
        // 原查询结果转换树形结构
        reMenuList = treeBuild.buildTree();

        resp.success(reMenuList);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> getRoleMenusMap(String roleId) {
        //通过用户id获取到role角色id
        List<MenuRolePojo> menuList = menuService.getRoleMenusMap(roleId);
        List<MenuRolePojo> reMenuList;
        // 创建树形结构（数据集合作为参数）
        TreeBuildUtil treeBuild = new TreeBuildUtil(menuList);
        // 原查询结果转换树形结构
        reMenuList = treeBuild.buildTree();

        resp.success(reMenuList);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> getRoleList(int currPage, int pageSize) {
        List<RolePojo> rolePojoList = roleService.getRoleList(currPage, pageSize);
        resp.success(rolePojoList);
        return resp;
    }

    @Override
    public ResultJsonUtil<Object> saveRoleMenu(Map<String, Object>[] paramInfo) {
//        List paramList = Arrays.asList(paramInfo);
        List<Map<String,Object>> paramList = Arrays.stream(paramInfo).toList();
        int res = menuService.saveRoleMenu(paramList);
        resp.success(res);
        return resp;
    }

}
