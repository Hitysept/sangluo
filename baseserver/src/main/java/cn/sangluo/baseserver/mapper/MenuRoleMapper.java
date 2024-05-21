package cn.sangluo.baseserver.mapper;

import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRoleMapper {

    List<MenuRolePojo> getDynamicRoutes(String companyId);
    List<MenuRolePojo> getDynamicMenus(String companyId);
    List<MenuRolePojo> getMenusList(@Param("currPage") int currPage,@Param("pageSize") int pageSize);
    List<MenuRolePojo> getMenusList();
    Integer getMenusListTotal();
    List<MenuRolePojo> getRoleMenusMap(String roleId);
}
