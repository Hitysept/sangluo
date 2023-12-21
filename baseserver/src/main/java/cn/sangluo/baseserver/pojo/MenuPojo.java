package cn.sangluo.baseserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @ClassName MenuPojo
 * @Description 菜单实体类
 * @Author 月痕江离
 * @Date 2023/10/17 18:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuPojo {
    private String menuId;
    private String name;
    private String parentId;
    private String menuType;
    private String path;
    private String component;
    private String requestUrl;
    private String menuCode;
    private String menuLevel;
    private String menuLevelCode;
    private Integer deleteStatus;


}
