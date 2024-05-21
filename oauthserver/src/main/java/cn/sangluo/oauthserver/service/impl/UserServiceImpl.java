package cn.sangluo.oauthserver.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.sangluo.oauthserver.feign.UserFeignService;
import cn.sangluo.oauthserver.service.UserService;
import cn.sangluo.util.ResultJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserFeignService userFeignService;
    @Autowired
    public UserServiceImpl(UserFeignService userFeignService) {this.userFeignService = userFeignService;}

    @Override
    public ResultJsonUtil<Object> verifyUserByName(Map<String, Object> userInfo) {
       return userFeignService.verifyUserByName(userInfo);
    }

    @Override
    public ResultJsonUtil<Object> setUserToken(Map<String, Object> setTokenInfo) {
        return userFeignService.setUserToken(setTokenInfo);
    }

    @Override
    public ResultJsonUtil<Object> Test(Map<String, Object> setTokenInfo) {
        return userFeignService.Test(setTokenInfo);
    }
}
