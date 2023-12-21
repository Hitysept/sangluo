package cn.sangluo.util;

import cn.sangluo.constant.SangluoErrorCodeConstant;

import java.io.Serial;

/**
 * @ClassName SangluoExcetion
 * @Description TODO
 * @Author 月痕江离
 * @Date 2023/5/28 20:10
 * @Version 1.0
 */
public class SangluoExcetion extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 6806129545290130132L;

    /**
     * 异常细分状态码
     */
    private int code = SangluoErrorCodeConstant.CODE_UNDEFINED;

    /**
     * 构建一个异常
     *
     * @param code 异常细分状态码
     */
    public SangluoExcetion(int code) {
        super();
        this.code = code;
    }


    /**
     * 构建一个异常
     *
     * @param message 异常描述信息
     */
    public SangluoExcetion(String message) {
        super(message);
    }

    /**
     * 构建一个异常
     *
     * @param code 异常细分状态码
     * @param message 异常信息
     */
    public SangluoExcetion(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构建一个异常
     *
     * @param cause 异常对象
     */
    public SangluoExcetion(Throwable cause) {
        super(cause);
    }

    /**
     * 构建一个异常
     *
     * @param message 异常信息
     * @param cause 异常对象
     */
    public SangluoExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 获取异常细分状态码
     * @return 异常细分状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 写入异常细分状态码
     * @param code 异常细分状态码
     * @return 对象自身
     */
    public SangluoExcetion setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 如果flag==true，则抛出message异常
     * @param flag 标记
     * @param message 异常信息
     * @param code 异常细分状态码
     */
    public static void throwBy(boolean flag, String message, int code) {
        if(flag) {
            throw new SangluoExcetion(message);
        }
    }

    /**
     * 如果value==null或者isEmpty，则抛出message异常
     * @param value 值
     * @param message 异常信息
     * @param code 异常细分状态码
     */
    public static void throwByNull(Object value, String message, int code) {
        if(SangluoFoxUtil.isEmpty(value)) {
            throw new SangluoExcetion(message).setCode(code);
        }
    }
}
