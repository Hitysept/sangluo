package cn.sangluo.constant;

/**
 * @ClassName ResponseStatusCodeConstant
 * @Description 响应状态常量定义
 * @Author 月痕江离
 * @Date 2023/5/26 17:28
 * @Version 1.0
 */
public class ResponseStatusCodeConstant {
    private ResponseStatusCodeConstant() {}

    public static final int SUCCESSFUL_CODE = 200;
    public static final int FAIL_CODE = 500; // 服务器内部逻辑出错
    public static final int BUSINESS_FAIL_CODE = 1000; // 业务不符合
    public static final int OAUTH_TOKEN_FAILURE = 2001; // token 失效
    public static final int OAUTH_TOKEN_MISSING = 2008; // token 缺失
    public static final int OAUTH_TOKEN_DENIED  = 2009; // token 权限不足
    public static final int PWD_ERROR = 4001; // 密码错误
    public static final int USER_LOCK = 4002; // 账号锁定
}
