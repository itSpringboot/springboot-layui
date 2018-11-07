package com.example.commonweb.web;

import com.example.commonweb.model.ResponseValidate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseValidateController<T extends Serializable> {

    /**
     *  校验是否存在
     * @param bean
     * @return
     */
    @RequestMapping(value = "validation", method = RequestMethod.GET)
    ResponseValidate validate(T bean);
}
