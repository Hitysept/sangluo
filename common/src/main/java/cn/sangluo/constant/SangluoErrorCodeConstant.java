package cn.sangluo.constant;

/**
 * @ClassName SangluoErrorCodeConstant
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/5/28 20:08
 * @Version 1.0
 */
public class SangluoErrorCodeConstant {
    public static final int CODE_UNDEFINED = -1;
    /** Base64 编码异常 */
    public static final int CODE_12101 = 12101;

    /** Base64 解码异常 */
    public static final int CODE_12102 = 12102;

    /** URL 编码异常 */
    public static final int CODE_12103 = 12103;

    /** URL 解码异常 */
    public static final int CODE_12104 = 12104;

    /** md5 加密异常 */
    public static final int CODE_12111 = 12111;

    /** sha1 加密异常 */
    public static final int CODE_12112 = 12112;

    /** sha256 加密异常 */
    public static final int CODE_12113 = 12113;

    /** AES 加密异常 */
    public static final int CODE_12114 = 12114;

    /** AES 解密异常 */
    public static final int CODE_12115 = 12115;

    /** RSA 公钥加密异常 */
    public static final int CODE_12116 = 12116;

    /** RSA 私钥加密异常 */
    public static final int CODE_12117 = 12117;

    /** RSA 公钥解密异常 */
    public static final int CODE_12118 = 12118;

    /** RSA 私钥解密异常 */
    public static final int CODE_12119 = 12119;

    // ------------

    /** 参与参数签名的秘钥不可为空 */
    public static final int CODE_12201 = 12201;

    /** 给定的签名无效 */
    public static final int CODE_12202 = 12202;

    /** timestamp 超出允许的范围 */
    public static final int CODE_12203 = 12203;
}
