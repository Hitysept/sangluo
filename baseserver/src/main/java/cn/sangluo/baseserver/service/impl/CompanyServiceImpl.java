package cn.sangluo.baseserver.service.impl;

import cn.sangluo.baseserver.mapper.CompanyMapper;
import cn.sangluo.baseserver.pojo.CompanyPojo;
import cn.sangluo.baseserver.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CompanyServiceImpl
 * @Description 公司或组织服务实现类
 * @Author 月痕江离
 * @Date 2023/9/23 16:09
 * @Version 1.0
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    @Autowired
    private CompanyServiceImpl (CompanyMapper companyMapper){this.companyMapper = companyMapper;}
    @Override
    public void saveCompany(CompanyPojo companyPojo) throws DataAccessException {
        companyMapper.saveCompany(companyPojo);
    }

    @Override
    public List<CompanyPojo> verifyCompanyByName(String companyName) {
        return companyMapper.verifyCompanyByName(companyName);
    }

    @Override
    public void updateCompany(CompanyPojo companyPojo) throws DataAccessException {
        companyMapper.updateCompany(companyPojo);
    }
}
