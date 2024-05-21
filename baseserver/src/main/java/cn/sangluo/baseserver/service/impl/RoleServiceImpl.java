package cn.sangluo.baseserver.service.impl;

import cn.sangluo.baseserver.mapper.RoleMapper;
import cn.sangluo.baseserver.pojo.RolePojo;
import cn.sangluo.baseserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description 权限服务实现类
 * @Author 月痕江离
 * @Date 2023/9/21 17:23
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    @Autowired
    private RoleServiceImpl (RoleMapper roleMapper){ this.roleMapper = roleMapper; }
    @Override
    public void saveRole(RolePojo rolePojo) throws DataAccessException {
        roleMapper.saveRole(rolePojo);
    }

    @Override
    public List<RolePojo> verifyRoleByNameAndCompanyId(String companyId,String roleName) {
        return roleMapper.verifyRoleByNameAndCompanyId(companyId,roleName);
    }

    @Override
    public List<RolePojo> verifyRoleBycompanyId(String companyId) {return roleMapper.verifyRoleBycompanyId(companyId);}

    @Override
    public void updateRole(RolePojo rolePojo) throws DataAccessException {
        roleMapper.updateRole(rolePojo);
    }

    @Override
    public List<RolePojo> getRoleList(int currPage, int pageSize) {
        int currIndex = (currPage-1)*pageSize;
        return roleMapper.getRoleList(currIndex,pageSize);
    }


}
