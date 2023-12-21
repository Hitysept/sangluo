package cn.sangluo.baseserver.controller;

import cn.sangluo.util.ResultJsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CompanyController
 * @Description 公司或组织操作类
 * @Author 月痕江离
 * @Date 2023/9/23 16:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/company")
public interface CompanyController {
    @RequestMapping("/saveCompany")
    ResultJsonUtil<Object> saveCompany(@RequestParam("companyName") String companyName,
                                    @RequestParam("updateUser") String updateUser);
}
