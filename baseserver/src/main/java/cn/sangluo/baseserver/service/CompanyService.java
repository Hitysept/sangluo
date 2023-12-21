package cn.sangluo.baseserver.service;

import cn.sangluo.baseserver.pojo.CompanyPojo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CompanyService
 * @Description 公司服务接口类
 * @Author 月痕江离
 * @Date 2023/9/23 16:08
 * @Version 1.0
 */
@Service
public interface CompanyService {
    void saveCompany(CompanyPojo companyPojo) throws DataAccessException;
    List<CompanyPojo> verifyCompanyByName(String companyName);
    void updateCompany(CompanyPojo companyPojo) throws DataAccessException;
}
