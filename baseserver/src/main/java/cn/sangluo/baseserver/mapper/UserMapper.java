package cn.sangluo.baseserver.mapper;

import cn.sangluo.baseserver.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<UserPojo> getUsersList();
    void userRegister(UserPojo userPojo) throws DataAccessException;
    List<UserPojo> getUsersByEmail(String email);
    UserPojo verifyUserByName(@Param("nameStr") String nameStr,@Param("password") String password);
    Long setUserToken(UserPojo userPojo) throws DataAccessException;
    UserPojo getUserByUserId(String userId);
    UserPojo getCompanyIdByUserId(String userId);
    UserPojo getUsersByToken(String token);
}
