package com.boommanpro.webfolderupload.common;

import lombok.Getter;

/**
 * @author boommanpro
 * @date 2020/3/16 9:20
 */
@Getter
public enum ResultCode {

    /**
     * SUCCESS
     */
    SUCCESS(20000, "SUCCESS"),
    /**
     * Inner Error
     */
    INNER_ERROR(50000, "系统内部错误"),
    /**
     * Login Error
     */
    LOGIN_ERROR(50001, "登录失败,用户名或者密码错误!"),

    /**
     * 登录超时
     */
    LOGIN_TIMEOUT(50002, "登录会话失效"),

    /**
     * 会话最大限制
     */
    SESSION_MAX_ERROR(50003, "登录失败,当前登录用户超过限制,建议您修改密码!"),
    /**
     * 方法找不到
     */
    NO_HANDLER(40004, "请求匹配失败"),
    /**
     * 失败请求
     */
    BAD_REQUEST(40000, "请求失败"),
    ;

    private int code;

    private String showMsg;

    ResultCode(int code, String showMsg) {
        this.code = code;
        this.showMsg = showMsg;
    }
}
