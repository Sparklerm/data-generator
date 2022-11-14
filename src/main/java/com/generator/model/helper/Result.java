package com.generator.model.helper;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sparkler
 * @createDate 2022/11/9
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3960261604605958516L;

    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>();
    }

    /**
     * 成功,默认状态码,返回消息,自定义返回数据
     *
     * @param data 自定义返回数据
     * @param <T>  返回类泛型,不能为String
     * @return 通用返回Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 成功,默认状态码,自定义返回消息,无返回数据
     *
     * @param message 自定义返回消息
     * @param <T>     返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(message);
    }

    /**
     * 成功,默认状态码,自定义返回消息,返回数据
     *
     * @param message 自定义返回消息
     * @param data    自定义返回数据
     * @param <T>     返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(message, data);
    }

    /**
     * 失败,默认状态码,返回消息,无返回数据
     *
     * @param <T> 返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> error() {
        return new Result<>(ResultCode.ERROR);
    }

    /**
     * 失败,默认状态码,自定义返回消息,无返回数据
     *
     * @param <T> 返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.ERROR.getCode(), message);
    }

    /**
     * 构造器,自定义状态码,返回消息
     *
     * @param code    状态码
     * @param message 返回消息
     */
    private Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 失败,使用CodeMsg状态码,返回消息,无返回数据
     *
     * @param resultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> message  返回消息
     * @param <T>        返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 成功构造器,无返回数据
     */
    private Result() {
        this(ResultCode.SUCCESS);
    }

    /**
     * 成功构造器,自定义返回数据
     *
     * @param data 返回数据
     */
    private Result(T data) {
        this(ResultCode.SUCCESS, data);
    }

    /**
     * 成功构造器,自定义返回消息,无返回数据
     *
     * @param message 返回消息
     */
    private Result(String message) {
        this(ResultCode.SUCCESS.getCode(), message);
    }

    /**
     * 成功构造器,自定义返回信息,返回数据
     *
     * @param message 返回信息
     * @param data    返回数据
     */
    private Result(String message, T data) {
        this(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 构造器,自定义状态码,返回消息,返回数据
     *
     * @param code    状态码
     * @param message 返回消息
     * @param data    返回数据
     */
    private Result(String code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    /**
     * 失败,自定义状态码,返回消息,无返回数据
     *
     * @param code    自定义状态码
     * @param message 自定义返回消息
     * @param <T>     返回类泛型
     * @return 通用返回Result
     */
    public static <T> Result<T> error(String code, String message) {
        return new Result<>(code, message);
    }

    /**
     * 构造器,使用CodeMsg状态码与返回信息
     *
     * @param resultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> message  返回消息
     */
    private Result(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * 构造器,使用CodeMsg状态码与返回信息,自定义返回数据
     *
     * @param resultCode CodeMsg,参数如下:
     *                   <p> code 状态码
     *                   <p> message  返回消息
     * @param data       返回数据
     */
    private Result(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

}