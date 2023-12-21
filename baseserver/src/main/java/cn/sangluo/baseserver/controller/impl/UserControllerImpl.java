package cn.sangluo.baseserver.controller.impl;

import cn.sangluo.baseserver.common.TreeBuildUtil;
import cn.sangluo.baseserver.pojo.MenuPojo;
import cn.sangluo.baseserver.pojo.MenuRolePojo;
import cn.sangluo.baseserver.service.MenuService;
import cn.sangluo.constant.ResponseMessageConstant;
import cn.sangluo.constant.ResponseStatusCodeConstant;
import cn.sangluo.baseserver.controller.UserController;
import cn.sangluo.baseserver.pojo.UserPojo;
import cn.sangluo.baseserver.service.UserService;
import cn.sangluo.util.ResultJsonUtil;
import cn.sangluo.util.SangluoFoxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final MenuService menuService;
    @Autowired
    public UserControllerImpl(UserService userService,MenuService menuService){
        this.menuService = menuService;
        this.userService = userService;
    }

    /**
     * 获取用户列表
     * @return 返回用户列表数据
     */
    @Override
    public ResultJsonUtil<Object> getUsersList() {
        List<UserPojo> list = userService.getUsersList();
        return new ResultJsonUtil<>().success(list);
    }

    /**
     * 用户注册
     * @param paramInfo 接收邮箱密码
     * @return 返回响应值
     * @throws DataAccessException 数据库异常
     */
    @Override
    public ResultJsonUtil<Object> userRegister(Map<String,Object> paramInfo) throws DataAccessException {
        ResultJsonUtil<Object> resp = new ResultJsonUtil<>();
        try{
            List<UserPojo> list = userService.getUsersByEmail(paramInfo.get("email").toString());
            if(SangluoFoxUtil.isEmptyList(list)){
                userService.userRegister(paramInfo.get("email").toString(),paramInfo.get("password").toString());
                resp.customized(ResponseStatusCodeConstant.SUCCESSFUL_CODE,ResponseMessageConstant.ACCOUNT_REGISTER_SUCCESS,"");
            }else{
                resp.customized(ResponseStatusCodeConstant.BUSINESS_FAIL_CODE,ResponseMessageConstant.ACCOUNT_REGISTER_FAIL,"");
            }
        }catch (DataAccessException e) {
            final Throwable cause = e.getCause();
            resp.customized(ResponseStatusCodeConstant.FAIL_CODE,ResponseMessageConstant.ACCOUNT_REGISTER_ERROR,"");
        }
        return resp;
    }

    /**
     * 用户登录校验
     * @param userIfo 用户信息
     * @return 返回用户数据
     * @throws NullPointerException 空异常
     */
    @Override
    public ResultJsonUtil<Object> verifyUserByName(Map<String,Object> userIfo) throws NullPointerException{
        ResultJsonUtil<Object> resp = new ResultJsonUtil<>();
        try {
            UserPojo userPojo = userService.verifyUserByName(userIfo.get("username").toString(),
                                                                userIfo.get("password").toString());
            if(userPojo == null){
                resp.fail(ResponseMessageConstant.OAUTH_PASSWORD_ERROR);
            }else {
                resp.success(userPojo);
            }
        }catch (NullPointerException e){
            final Throwable cause = e.getCause();
            System.out.println(cause);
            resp.fail(ResponseMessageConstant.OAUTH_ACCOUNT_ERROR);
        }
        return resp;
    }

    /**
     * 保存用户token
     * @param userSatokenInfo 用户token
     * @return 返回用户信息
     * @throws DataAccessException 数据库异常
     */
    @Override
    public ResultJsonUtil<Object> setUserToken(Map<String,Object> userSatokenInfo) throws DataAccessException{
        ResultJsonUtil<Object> resp = new ResultJsonUtil<>();
        UserPojo userPojo = new UserPojo();
        userPojo.setUserId(userSatokenInfo.get("userId").toString());
        userPojo.setToken(userSatokenInfo.get("token").toString());
        userPojo.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        try {
            Map respMap = new HashMap();
            UserPojo respUserPojo = userService.setUserToken(userPojo);
            //通过用户id获取到role角色id
            List<MenuRolePojo> menuList = menuService.getDynamicRoutes(respUserPojo.getCompanyId());
            respMap.put("menuInfo",menuList);
            respMap.put("userInfo",respUserPojo);
            resp.success(respMap);
        }catch (DataAccessException e){
            final Throwable cause = e.getCause();
            System.out.println(cause);
            resp.fail(ResponseMessageConstant.DATA_SAVE_FAIL);
        }
        return resp;
    }

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 返回用户信息
     */
    @Override
    public ResultJsonUtil<Object> getUserByUserId(String userId) {
        UserPojo userPojo = userService.getUserByUserId(userId);
        return new ResultJsonUtil<>().success(userPojo);
    }
}
