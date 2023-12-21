package cn.sangluo.baseserver.controller;

import cn.sangluo.util.ResultJsonUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/userInfo")
public interface UserController {
    @RequestMapping("/admin/getUsersList")
    ResultJsonUtil<Object> getUsersList();
    @RequestMapping("/userRegister")
    ResultJsonUtil<Object> userRegister(@RequestBody Map<String,Object> paramInfo);
    @PostMapping("/verifyUserByName")
    ResultJsonUtil<Object> verifyUserByName(@RequestBody Map<String,Object> userInfo);
    @PostMapping("/setUserToken")
    ResultJsonUtil<Object> setUserToken(@RequestBody Map<String,Object> userSatokenInfo);
    @GetMapping("/getUserByUserId")
    ResultJsonUtil<Object> getUserByUserId(@RequestParam("userId") String userId);
}
