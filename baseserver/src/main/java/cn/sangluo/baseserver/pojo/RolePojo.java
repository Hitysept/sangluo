package cn.sangluo.baseserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RolePojo
 * @Description 权限实体类
 * @Author 月痕江离
 * @Date 2023/9/21 16:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePojo {
    private String roleId;
    private String roleName;
    private String companyId;
    private Integer deleteStatus;
    private String creator;
    private String createTime;
    private String updateTime;
    private String updateUser;
}
