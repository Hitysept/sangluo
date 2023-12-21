package cn.sangluo.baseserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName CompanyPojo
 * @Description 公司或组织实体类
 * @Author 月痕江离
 * @Date 2023/9/21 16:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPojo implements Serializable {
    private String companyId;
    private String companyName;
    private String createTime;
    private String updateTime;
    private String deleteStatus;
    private String creator;
    private String updateUser;
}
