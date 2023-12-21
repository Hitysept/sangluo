package cn.sangluo.constant;

/**
 * @ClassName ResponseCodeConstant
 * @Description 响应 Code 常量定义类
 * @Author 月痕江离
 * @Date 2023/5/26 17:26
 * @Version 1.0
 */
public class ResponseCodeConstant {
    private ResponseCodeConstant() {}

    public static final int REQUEST_SUCCESS = 1; // 请求成功
    public static final int REQUEST_FAILED  = 0; // 请求失败
    public static final int SYSTEM_ERROR    = -1; // 系统错误
    public static final int OAUTH_TOKEN_FAILURE = 2001; // token 无效
    public static final int OAUTH_TOKEN_MISSING = 2008; // token 缺失
    public static final int OAUTH_TOKEN_DENIED  = 2009; // token 权限不足
    public static final int OAUTH_TOKEN_TIMEOUT  = 2010; // token 过期
    public static final int PWD_ERROR = 4001; // 密码错误
    public static final int USER_LOCK = 4002; // 账号锁定
    public static final int BE_REPLACED  = 4003; // 账户已在另一台设备上登录
    public static final int KICK_OUT = 4004; //系统强制下线
}
