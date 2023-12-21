package cn.sangluo.baseserver.controller.impl;

import cn.sangluo.baseserver.controller.CompanyController;
import cn.sangluo.baseserver.pojo.CompanyPojo;
import cn.sangluo.baseserver.service.CompanyService;
import cn.sangluo.constant.ResponseMessageConstant;
import cn.sangluo.util.ResultJsonUtil;
import cn.sangluo.util.SangluoFoxUtil;
import cn.sangluo.util.SnowFlakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CompanyControllerImpl
 * @Description 公司或组织操作实现类
 * @Author 月痕江离
 * @Date 2023/9/23 16:19
 * @Version 1.0
 */
@RestController
public class CompanyControllerImpl implements CompanyController {
    private CompanyService companyService;
    @Autowired
    private CompanyControllerImpl(CompanyService companyService){this.companyService = companyService;}
    /**
       * @MethodName saveCompany
       * @Description  新增组织
       * @param companyName 组织名字
       * @param updateUser 创建者或者更新者
       * @return cn.sangluo.util.ResultJsonUtil<java.lang.Object>
       * @Author 月痕江离
       * @Date 17:25 2023/10/7
       */
    @Override
    public ResultJsonUtil<Object> saveCompany(String companyName, String updateUser) {
        ResultJsonUtil<Object> resp = new ResultJsonUtil<>();
        try {
            List<CompanyPojo> list = companyService.verifyCompanyByName(companyName);
            CompanyPojo companyPojo = new CompanyPojo();
            companyPojo.setCompanyName(companyName);
            companyPojo.setUpdateTime(String.valueOf(System.currentTimeMillis()));
            companyPojo.setUpdateUser(updateUser);
            if(SangluoFoxUtil.isEmptyList(list)){
                SnowFlakeUtil worker=new SnowFlakeUtil(1,1,2);
                companyPojo.setCompanyId(String.valueOf(worker.nextId()));
                companyPojo.setCreateTime(String.valueOf(System.currentTimeMillis()));
                companyPojo.setCreator(updateUser);
                //插入公司或组织数据
                companyService.saveCompany(companyPojo);
            }else{
                //更新公司或组织数据
                companyService.updateCompany(companyPojo);
            }
            resp.success(ResponseMessageConstant.REQUEST_SUCCESS);
        }catch (DataAccessException e){
            resp.fail(ResponseMessageConstant.DATA_SAVE_FAIL);
        }
        return resp;
    }
}
