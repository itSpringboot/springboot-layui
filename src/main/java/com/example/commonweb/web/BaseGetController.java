package com.example.commonweb.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

public interface BaseGetController<T extends Serializable, P extends Serializable> {

    /**
     * @param
     * @return
     * @throws
     * @Title:
     * @Description: 根据id查询一条记录
     * @date 2018/3/14 15:18
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    T get(@PathVariable("id") P id);

    /**
     * @param
     * @return
     * @throws
     * @Title:
     * @Description: 根据条件查询一条记录
     * @date 2018/3/14 15:18
     */
    @RequestMapping(value = "one", method = RequestMethod.GET)
    T getCond(@Validated @ModelAttribute T bean);
}
