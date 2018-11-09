package com.inspur.common.web.controller;

import com.inspur.common.web.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseUpdateController<T extends Serializable> {

    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: update
     * @Description: 更新对象
     */
    @RequestMapping(method = RequestMethod.PUT)
    ResponseResult update(@RequestBody T bean);
}
