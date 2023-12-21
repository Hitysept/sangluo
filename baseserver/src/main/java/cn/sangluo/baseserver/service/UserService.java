package cn.sangluo.baseserver.service;

import cn.sangluo.baseserver.pojo.UserPojo;
import org.apache.catalina.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserPojo> getUsersList();
    void userRegister(String email,String password) throws DataAccessException;
    UserPojo verifyUserByName(String nameStr,String password);
    UserPojo setUserToken(UserPojo userPojo) throws DataAccessException;
    UserPojo getUserByUserId(String userId);
    UserPojo getCompanyIdByUserId(String userId);
    List<UserPojo> getUsersByEmail(String email);
    UserPojo getUsersByToken(String token);
}
