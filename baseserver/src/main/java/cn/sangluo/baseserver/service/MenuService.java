package cn.sangluo.baseserver.service;

import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import cn.sangluo.baseserver.pojo.RolePojo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuService
 * @Description 菜单服务接口类
 * @Author 月痕江离
 * @Date 2023/10/18 0:50
 * @Version 1.0
 */
@Service
public interface MenuService {
    void saveMenu(MenuPojo menuPojo) throws DataAccessException;
    int upDataMenu(MenuPojo menuPojo) throws DataAccessException;
    List<MenuPojo> getMenuCodeByMenuUrl(String menuUrl);
    List<MenuRolePojo> getDynamicRoutes(String companyId);
    List<String> getDynamicRoutesList(String companyId);
    List<MenuRolePojo> getDynamicMenus(String companyId);
    List<MenuRolePojo> getMenusList(int currPage,int pageSize);
    List<MenuRolePojo> getMenusList();
    Integer getMenusListTotal();
    List<MenuRolePojo> getRoleMenusMap(String roleId);
    int saveRoleMenu(List<Map<String, Object>> menuRoleList);
}
