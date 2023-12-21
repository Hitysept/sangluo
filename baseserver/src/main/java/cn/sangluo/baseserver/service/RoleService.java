package cn.sangluo.baseserver.service;

import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.RolePojo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description 权限服务接口类
 * @Author 月痕江离
 * @Date 2023/9/21 17:22
 * @Version 1.0
 */
@Service
public interface RoleService {
    void saveRole(RolePojo rolePojo) throws DataAccessException;
    List<RolePojo> verifyRoleByNameAndCompanyId(String companyId,String roleName);

    List<RolePojo> verifyRoleBycompanyId(String companyId);
    void updateRole(RolePojo rolePojo) throws DataAccessException;
    List<RolePojo> getRoleList(int currPage, int pageSize);
}
