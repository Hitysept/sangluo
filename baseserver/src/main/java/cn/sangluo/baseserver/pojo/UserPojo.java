package cn.sangluo.baseserver.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo implements Serializable {
    private String userId;
    private String headImg;
    private String userName;
    private String nickName;
    private String token;
    private String idNumber;
    private String mobile;
    private String email;
    private String password;
    private String companyId;
    private Integer deleteStatus;
    private String creator;
    private String createTime;
    private String updateUser;
    private String updateTime;
}
