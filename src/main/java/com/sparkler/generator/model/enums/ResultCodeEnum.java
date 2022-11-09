package com.sparkler.generator.model.enums;

import com.sparkler.generator.config.exception.BaseResultInfoInterface;
import lombok.Getter;

/**
 * @author Sparkler
 * @createDate 2022/11/9
 */
@Getter
public enum ResultCodeEnum implements BaseResultInfoInterface {

    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功"),
    ERROR("-1", "操作失败"),
    BIZ_ERROR("1000", "通用业务异常"),
    FILE_OUT_MAX("9000", "文件超出最大限制"),
    FILE_FORMAT_ERROR("9001", "文件格式不正确"),
    PARAM_ERROR("9050", "参数错误"),
    JSON_FORMAT_ERROR("9051", "Json解析异常"),
    SQL_ERROR("9052", "Sql解析异常"),
    NETWORK_TIMEOUT("9510", "网络超时"),
    UNKNOWN_INTERFACE("9520", "未知的接口"),
    REQ_MODE_NOT_SUPPORTED("9530", "请求方式不支持"),
    SYS_ERROR("9999", "系统异常");
    /**
     * 状态码
     */
    private final String code;

    /**
     * 状态信息
     */
    private final String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getResultCode() {
        return this.code;
    }

    @Override
    public String getResultMessage() {
        return this.message;
    }
}
