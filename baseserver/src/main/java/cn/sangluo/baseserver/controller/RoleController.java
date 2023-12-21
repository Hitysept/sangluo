package cn.sangluo.baseserver.controller;

import cn.sangluo.util.ResultJsonUtil;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Description 权限操作接口类
 * @Author 月痕江离
 * @Date 2023/9/21 16:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public interface RoleController {
    @PostMapping("/saveRole")
    ResultJsonUtil<Object> saveRole(@RequestBody Map<String,Object> paramInfo);
    @PostMapping("/saveMenu")
    ResultJsonUtil<Object> saveMenu(@RequestBody Map<String,Object> paramInfo);
    @RequestMapping ("/verifyRoleByUserId")
    ResultJsonUtil<Object> verifyRoleByUserId(@RequestParam("userId") String userId);
    @RequestMapping("/getAllPermissionList")
    ResultJsonUtil<Object> getAllPermissionList(@RequestParam("userId") String userId);
    @RequestMapping("/getDynamicMenus")
    ResultJsonUtil<Object> getDynamicMenus(@RequestHeader("satoken") String token);
    @RequestMapping("/getDynamicRouters")
    ResultJsonUtil<Object> getDynamicRouters(@RequestHeader("satoken") String token);
    @RequestMapping("/getMenusList")
    ResultJsonUtil<Object> getMenusList(@RequestParam("currPage") int currPage,@RequestParam("pageSize") int pageSize);
    @RequestMapping("/getMenusTreeList")
    ResultJsonUtil<Object> getMenusTreeList();
    @RequestMapping("/getRoleMenusMap")
    ResultJsonUtil<Object> getRoleMenusMap(@RequestParam("roleId") String roleId);
    @RequestMapping("/getRoleList")
    ResultJsonUtil<Object> getRoleList(@RequestParam("currPage") int currPage,@RequestParam("pageSize") int pageSize);
    @RequestMapping("/saveRoleMenu")
    ResultJsonUtil<Object> saveRoleMenu(@RequestBody Object[] paramInfo);
}
