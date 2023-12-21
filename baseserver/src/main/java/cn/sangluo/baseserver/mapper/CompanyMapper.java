package cn.sangluo.baseserver.mapper;

import cn.sangluo.baseserver.pojo.CompanyPojo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CompanyMapper
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/9/22 23:47
 * @Version 1.0
 */
@Repository
public interface CompanyMapper {
    void saveCompany(CompanyPojo companyPojo) throws DataAccessException;
    List<CompanyPojo> verifyCompanyByName(String companyName);
    void updateCompany(CompanyPojo companyPojo) throws DataAccessException;
}
