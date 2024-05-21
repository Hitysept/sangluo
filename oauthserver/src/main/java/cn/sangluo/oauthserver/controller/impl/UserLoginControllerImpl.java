package cn.sangluo.oauthserver.controller.impl;

import cn.sangluo.constant.ResponseStatusCodeConstant;
import cn.sangluo.constant.SangluoConfigConstant;
import cn.sangluo.oauthserver.controller.UserLoginController;
import cn.sangluo.oauthserver.feign.UserFeignService;
import cn.sangluo.oauthserver.service.UserService;
import cn.sangluo.util.ResultJsonUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.sangluo.util.SangluoFoxUtil;
import cn.sangluo.util.SangluoSecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName UserLoginControllerImpl
 * @Description 认证校验
 * @Author 月痕江离
 * @Date 2023/5/29 3:09
 * @Version 1.0
 */
@RestController
public class UserLoginControllerImpl implements UserLoginController {

    private final UserService userService;

    private final String publicKey = SangluoConfigConstant.PublicKey;
    private final String privateKey = SangluoConfigConstant.PrivateKey;
    @Autowired
    public UserLoginControllerImpl(UserService userService) {this.userService = userService;}
    @Override
    public ResultJsonUtil<Object> login(Map<String,Object> userInfo) throws Exception {
        ResultJsonUtil<Object> resultJsonUtil = null;
        ResultJsonUtil<Object> responseJsonUtil = new ResultJsonUtil<>();
        resultJsonUtil = userService.verifyUserByName(userInfo);

        if(resultJsonUtil.getCode() == 1 && resultJsonUtil.getData() instanceof LinkedHashMap userMap){
            String userId = userMap.get("userId").toString();
            //注销当前所需要登录id（注销token）
            StpUtil.logout(userId, "PC");
            StpUtil.login(userId,"PC");
            String token = StpUtil.getTokenValue();
            Map<String,Object> setTokenInfo = new HashMap<>();
            setTokenInfo.put("userId",userId);
            setTokenInfo.put("token",token);
            responseJsonUtil = userService.setUserToken(setTokenInfo);
        }else{
            responseJsonUtil.fail(resultJsonUtil.getMsg());
        }
        return responseJsonUtil;
    }

    @Override
    public ResultJsonUtil<Object> logout(String id) {
        StpUtil.logout(id);
        return new ResultJsonUtil<>().customized(ResponseStatusCodeConstant.SUCCESSFUL_CODE,"你已下线","");
    }

    @Override
    public ResultJsonUtil<Object> testPost(Map<String, Object> Info) {
        ResultJsonUtil<Object> resultJsonUtil = null;
        resultJsonUtil = userService.Test(Info);
        return resultJsonUtil;
    }
    @Override
    public ResultJsonUtil<Object> testGet(String id) {
        return new ResultJsonUtil<>().
                customized(
                        ResponseStatusCodeConstant.SUCCESSFUL_CODE,
                        SangluoSecureUtil.aesEncrypt(publicKey+privateKey,id),id);
    }
}
