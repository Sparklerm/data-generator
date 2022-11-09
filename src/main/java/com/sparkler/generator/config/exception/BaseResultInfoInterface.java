package com.sparkler.generator.config.exception;

/**
 * 服务接口类
 *
 * @author Sparkler
 * @createDate 2022/11/9
 */
public interface BaseResultInfoInterface {
    /**
     * 错误码
     *
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getResultMessage();
}
