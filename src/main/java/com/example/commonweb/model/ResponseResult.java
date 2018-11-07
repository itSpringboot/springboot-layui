package com.example.commonweb.model;

import lombok.*;

import java.io.Serializable;

/**
 * @param <T> 返回的实体对象
 * @ClassName: ResponseResult
 * @Description: 响应结果
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String msg;

    private T data;

    public static <T> ResponseResult<T> ok() {
        ResponseResult responseResult =  new ResponseResult();
        responseResult.setSuccess(true);
        return responseResult;
    }

    public static <T> ResponseResult<T> fail() {
    ResponseResult responseResult =  new ResponseResult();
        responseResult.setSuccess(false);
        return responseResult;
    }

    public static <T> ResponseResult<T> create(int record) {
        return new ResponseResult(record);
    }

    ResponseResult(int record) {
        super();
        this.success = record > 0 ? true : false;
    }

}
