package cn.sangluo.oauthserver.service;

import cn.sangluo.util.ResultJsonUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    ResultJsonUtil<Object>  verifyUserByName(Map<String,Object> userInfo);
    ResultJsonUtil<Object>  setUserToken(Map<String,Object> setTokenInfo);
    ResultJsonUtil<Object>  Test(Map<String,Object> setTokenInfo);
}
