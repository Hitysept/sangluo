package cn.sangluo.baseserver.service.impl;

import cn.sangluo.constant.SangluoConfigConstant;
import cn.sangluo.baseserver.mapper.UserMapper;
import cn.sangluo.baseserver.pojo.UserPojo;
import cn.sangluo.baseserver.service.UserService;
import cn.sangluo.util.SangluoSecureUtil;
import cn.sangluo.util.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final String publicKey = SangluoConfigConstant.PublicKey;
    private final String privateKey = SangluoConfigConstant.PrivateKey;
    @Autowired
    private UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
       * @MethodName getUsersList
       * @Description  获取用户列表
       * @Author 月痕江离
       * @Date 15:53 2023/10/5
       */
    @Override
    public List<UserPojo> getUsersList(){return userMapper.getUsersList();}

    /**
       * @MethodName userRegister
       * @Description 用户注册
       * @param: email
       * @param: password
       * @Author 月痕江离
       * @Date 20:33 2023/9/12
       */
    @Override
    public void userRegister(String email,String password) throws DataAccessException {
        SnowFlakeUtil worker=new SnowFlakeUtil(1,1,1);
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId(String.valueOf(worker.nextId()));
        userPojo.setEmail(email);
        userPojo.setUserName("user"+worker.nextId());
        userPojo.setNickName("小桑落");
        userPojo.setPassword(SangluoSecureUtil.aesEncrypt(publicKey+privateKey,password));
        userPojo.setCreateTime(String.valueOf(System.currentTimeMillis()));
        userPojo.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        userMapper.userRegister(userPojo);
    }
    /**
       * @MethodName verifyUserByName
       * @Description  校验用户账户密码是否正确
       * @param: nameStr 账户名
       * @param: password 密码
       * @return: cn.sangluo.baseserver.pojo.UserPojo
       * @Author 月痕江离
       * @Date 20:32 2023/9/12
       */
    @Override
    public UserPojo verifyUserByName(String nameStr,String password){
        String aesEncryptPassword = SangluoSecureUtil.aesEncrypt(publicKey+privateKey,password);
        return userMapper.verifyUserByName(nameStr,aesEncryptPassword);
    }
    /**
       * @MethodName setUserToken
       * @Description  保存用户token
       * @param: userPojo 用户实体
       * @return: cn.sangluo.baseserver.pojo.UserPojo 用户实体
       * @Author 月痕江离
       * @Date 20:32 2023/9/12
       */
    @Override
    public UserPojo setUserToken(UserPojo userPojo) throws DataAccessException {
        Long code = userMapper.setUserToken(userPojo);
        if(code == 1){
            return userMapper.getUserByUserId(userPojo.getUserId());
        }else{
            return new UserPojo();
        }
    }
    /**
       * @MethodName getUserByUserId
       * @Description 通过用户id获取用户信息
       * @param: userId 用户id
       * @return: cn.sangluo.baseserver.pojo.UserPojo 用户实体
       * @Author 月痕江离
       * @Date 20:29 2023/9/12
       */
    @Override
    public UserPojo getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }
    /***
       * @MethodName getCompanyIdByUserId
       * @Description  通过userId获取用户所属组织
       * @param: userId
       * @return: cn.sangluo.baseserver.pojo.UserPojo
       * @Author 月痕江离
       * @Date 22:53 2023/10/6
       */
    @Override
    public UserPojo getCompanyIdByUserId(String userId) { return userMapper.getCompanyIdByUserId(userId); }
    /**
       * @MethodName getUsersByEmail
       * @Description  通过用户邮箱获取用户信息
       * @param: email 用户邮箱
       * @return: java.util.List<cn.sangluo.baseserver.pojo.UserPojo>
       * @Author 月痕江离
       * @Date 20:37 2023/9/12
       */
    @Override
    public List<UserPojo> getUsersByEmail(String email) {
        return userMapper.getUsersByEmail(email);
    }

    @Override
    public UserPojo getUsersByToken(String token) {
        return userMapper.getUsersByToken(token);
    }
}
