package com.yswuyu.backend.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yswuyu.backend.common.exception.BaseException;
import com.yswuyu.backend.common.exception.ErrorType;
import com.yswuyu.backend.common.exception.SystemErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Getter
public class ResResult<T> {

    public static final String SUCCESSFUL_CODE = "000000";
    public static final String SUCCESSFUL_MESG = "处理成功";

    @ApiModelProperty(value = "处理结果code", required = true)
    private String code;
    @ApiModelProperty(value = "处理结果描述信息")
    private String mesg;
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    @ApiModelProperty(value = "处理结果数据信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResResult() {
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     */
    public ResResult(ErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * @param errorType
     * @param data
     */
    public ResResult(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param mesg
     * @param data
     */
    private ResResult(String code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static ResResult success(Object data) {
        return new ResResult<>(SUCCESSFUL_CODE, SUCCESSFUL_MESG, data);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static ResResult success() {
        return success(null);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static ResResult fail() {
        return new ResResult(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @param baseException
     * @return Result
     */
    public static ResResult fail(BaseException baseException) {
        return fail(baseException, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static ResResult fail(BaseException baseException, Object data) {
        return new ResResult<>(baseException.getErrorType(), data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static ResResult fail(ErrorType errorType, Object data) {
        return new ResResult<>(errorType, data);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static ResResult fail(ErrorType errorType) {
        return ResResult.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static ResResult fail(Object data) {
        return new ResResult<>(SystemErrorType.SYSTEM_ERROR, data);
    }


    /**
     * 成功code=000000
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    /**
     * 失败
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }
}
