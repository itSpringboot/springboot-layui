package com.example.commonweb.web;

import com.example.commonweb.model.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseCreateController<T extends Serializable> {

    @RequestMapping(method = RequestMethod.POST)
    ResponseResult create(@RequestBody T bean);
}