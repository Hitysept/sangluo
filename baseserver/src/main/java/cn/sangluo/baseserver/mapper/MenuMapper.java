package cn.sangluo.baseserver.mapper;

import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName MenuMapper
 * @Description 菜单映射类
 * @Author 月痕江离
 * @Date 2023/10/17 20:12
 * @Version 1.0
 */
@Repository
public interface MenuMapper {
    void saveMenu(MenuPojo menuPojo) throws DataAccessException;
    int upDataMenu(MenuPojo menuPojo) throws  DataAccessException;
    List<MenuPojo> getMenuCodeByMenuUrl(String menuUrl);
}
