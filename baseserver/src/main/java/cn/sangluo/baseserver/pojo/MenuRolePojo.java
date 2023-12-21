package cn.sangluo.baseserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRolePojo extends MenuPojo{
    private String roleId;
    private String roleName;
    private String companyId;
    private Integer deleteStatus;
    private Integer status;
    public ArrayList<MenuRolePojo> children;
}
