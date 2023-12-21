package cn.sangluo.oauthserver.controller;

import cn.sangluo.util.ResultJsonUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName UserLoginController
 * @Description 认证校验
 * @Author 月痕江离
 * @Date 2023/5/27 17:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/oauth")
public interface UserLoginController {
    /**
     * 登录校验
     */
    @PostMapping ("/login")
    ResultJsonUtil<Object> login(@RequestBody Map<String,Object> userInfo) throws ExecutionException, InterruptedException, TimeoutException;

    /**
     * 登出
     */
    @RequestMapping("/logout")
    ResultJsonUtil<Object> logout(@RequestParam String id);

}
