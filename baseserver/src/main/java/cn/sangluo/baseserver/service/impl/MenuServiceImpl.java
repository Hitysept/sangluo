package cn.sangluo.baseserver.service.impl;

import cn.sangluo.baseserver.common.TreeBuildUtil;
import cn.sangluo.baseserver.mapper.CommonMapper;
import cn.sangluo.baseserver.mapper.MenuRoleMapper;
import cn.sangluo.baseserver.mapper.MenuMapper;
import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import cn.sangluo.baseserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuServiceImpl
 * @Description 菜单服务
 * @Author 月痕江离
 * @Date 2023/10/18 0:59
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuMapper menuMapper;
    private final MenuRoleMapper MenuRoleMapper;
    private final CommonMapper commonMapper;
    @Autowired
    private MenuServiceImpl(MenuMapper menuMapper, MenuRoleMapper MenuRoleMapper,CommonMapper commonMapper){
        this.menuMapper = menuMapper;
        this.MenuRoleMapper = MenuRoleMapper;
        this.commonMapper = commonMapper;
    }
    @Override
    public void saveMenu(MenuPojo menuPojo) throws DataAccessException {
        menuMapper.saveMenu(menuPojo);
    }

    @Override
    public int upDataMenu(MenuPojo menuPojo) throws DataAccessException {
        return menuMapper.upDataMenu(menuPojo);
    }

    @Override
    public List<MenuPojo> getMenuCodeByMenuUrl(String menuUrl){
        return menuMapper.getMenuCodeByMenuUrl(menuUrl);
    }

    @Override
    public List<MenuRolePojo> getDynamicRoutes(String companyId) {
        List<MenuRolePojo> menuRolePojoList = MenuRoleMapper.getDynamicRoutes(companyId);
        List<MenuRolePojo> reMenuList;
        // 创建树形结构（数据集合作为参数）
        TreeBuildUtil treeBuild = new TreeBuildUtil(menuRolePojoList);
        // 原查询结果转换树形结构
        reMenuList = treeBuild.buildTree();
        return reMenuList;
    }
    @Override
    public List<String> getDynamicRoutesList(String companyId) {
        List<MenuRolePojo> menuList = MenuRoleMapper.getDynamicRoutes(companyId);
        List<String> roleList = new ArrayList<>();
        for (MenuRolePojo menuRolePojo: menuList){
            roleList.add(menuRolePojo.getRequestUrl());
        }
        return roleList;
    }
    @Override
    public List<MenuRolePojo> getDynamicMenus(String companyId) {
        List<MenuRolePojo> menuRolePojoList = MenuRoleMapper.getDynamicMenus(companyId);
        List<MenuRolePojo> reMenuList;
        // 创建树形结构（数据集合作为参数）
        TreeBuildUtil treeBuild = new TreeBuildUtil(menuRolePojoList);
        // 原查询结果转换树形结构
        reMenuList = treeBuild.buildTree();
        return reMenuList;
    }
    @Override
    public List<MenuRolePojo> getMenusList(int currPage,int pageSize) {
        int currIndex = (currPage-1)*pageSize;
        return MenuRoleMapper.getMenusList(currIndex, pageSize);
    }

    @Override
    public List<MenuRolePojo> getMenusList() {
        return MenuRoleMapper.getMenusList();
    }

    @Override
    public Integer getMenusListTotal() {
        return MenuRoleMapper.getMenusListTotal();
    }

    @Override
    public List<MenuRolePojo> getRoleMenusMap(String roleId) {
        return MenuRoleMapper.getRoleMenusMap(roleId);
    }

    public int saveRoleMenu(List<Map<String, Object>> menuRoleList) {
        return commonMapper.batchSaveDynamic("sys_role_menu",menuRoleList);
    }
}
