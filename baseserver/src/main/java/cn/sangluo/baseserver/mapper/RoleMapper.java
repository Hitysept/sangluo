package cn.sangluo.baseserver.mapper;

import cn.sangluo.baseserver.pojo.RolePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Description 角色权限映射类
 * @Author 月痕江离
 * @Date 2023/9/21 16:43
 * @Version 1.0
 */
@Repository
public interface RoleMapper {
    void saveRole(RolePojo rolePojo) throws DataAccessException;
    List<RolePojo> verifyRoleByName(String roleName);
    List<RolePojo> verifyRoleByNameAndCompanyId(@Param("companyId") String companyId, @Param("roleName") String roleName);
    List<RolePojo> verifyRoleBycompanyId(String companyId);
    void updateRole(RolePojo rolePojo) throws DataAccessException;
    List<RolePojo> getRoleList(@Param("currIndex") int currIndex,@Param("pageSize") int pageSize);
}
