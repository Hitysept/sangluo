package cn.sangluo.constant;

/**
 * @ClassName ResponseMessageConstant
 * @Description 响应信息
 * @Author 月痕江离
 * @Date 2023/5/26 17:27
 * @Version 1.0
 */
public class ResponseMessageConstant {
    private ResponseMessageConstant() {}

    public static final String NO_SESSION                 = "当前会话未登录";
    public static final String REQUEST_SUCCESS            = "请求成功";
    public static final String REQUEST_FAILED             = "请求失败";
    public static final String SYSTEM_ERROR               = "系统错误";
    public static final String APP_EXCEPTION              = "应用程序异常";
    public static final String OAUTH_TOKEN_MISSING        = "token 缺失";
    public static final String OAUTH_TOKEN_ILLEGAL        = "验证过期，请重新登录";
    public static final String OAUTH_TOKEN_DENIED         = "权限不足";
    public static final String OAUTH_TOKEN_CHECK_ERROR    = "token 验证失败";
    public static final String SIGN_CHECK_ERROR           = "签名验证失败";
    public static final String KICK_OUT                   = "已被系统强制下线";
    public static final String BE_REPLACED                = "您的账户已在另一台设备上登录，如非本人操作，请立即修改密码";
    public static final String ACCOUNT_REGISTER_SUCCESS   = "账号注册成功";
    public static final String ACCOUNT_REGISTER_ERROR     = "账号注册失败";
    public static final String ACCOUNT_REGISTER_FAIL      = "账号已被注册";
    public static final String OAUTH_PASSWORD_ERROR       = "账号或密码有误";
    public static final String OAUTH_ACCOUNT_ERROR        = "账号为空";
    public static final String DATA_SAVE_FAIL             = "数据插入异常";
    public static final String DATA_SAVE_ERROR             = "数据插入失败";
}
