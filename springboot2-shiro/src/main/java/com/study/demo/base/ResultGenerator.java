package com.study.demo.base;

/**
 *
 * @author valiantzh
 * @version 1.0
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    //成功
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCodeEnum.FAIL)
                .setMessage(message);
    }

    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResultCodeEnum.UNAUTHORIZED)
                .setMessage("权限不足！");
    }
}
