package com.xiaoguai.agentx.infrastrcture.config;


import com.xiaoguai.agentx.infrastrcture.exception.BusinessException;
import com.xiaoguai.agentx.infrastrcture.exception.EntityNotFoundException;
import com.xiaoguai.agentx.infrastrcture.exception.ParamValidationException;
import com.xiaoguai.agentx.interfaces.api.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-07-25 10:08
 * @Description: 全局异常处理拦截器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
        logger.error("业务异常: {}, URL: {}", e.getMessage(), request.getRequestURL(), e);
        return Result.error(400, e.getMessage());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(ParamValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleParamValidationException(ParamValidationException e, HttpServletRequest request) {
        logger.error("参数校验异常: {}, URL: {}", e.getMessage(), request.getRequestURL(), e);
        return Result.badRequest(e.getMessage());
    }

    /**
     * 处理实体未找到异常
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        logger.error("实体未找到异常: {}, URL: {}", e.getMessage(), request.getRequestURL(), e);
        return Result.notFound(e.getMessage());
    }

    /**
     * 处理方法参数校验异常（@Valid注解导致的异常）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                              HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        logger.error("方法参数校验异常: {}, URL: {}", errorMessage, request.getRequestURL(), e);
        return Result.badRequest(errorMessage);
    }

    /**
     * 处理表单绑定异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleBindException(BindException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        logger.error("表单绑定异常: {}, URL: {}", errorMessage, request.getRequestURL(), e);
        return Result.badRequest(errorMessage);
    }

    /**
     * 处理未预期的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleException(Exception e, HttpServletRequest request) {
        logger.error("未预期的异常: ", e);
        return Result.serverError("服务器内部错误: " + e.getMessage());
    }
}