package com.example.commonweb.web;

import com.example.commonweb.model.Page;
import com.example.commonweb.model.RequestPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

public interface BaseQueryController<T extends Serializable> {

    /**
     * @param requestPage
     * @param bean
     * @return Page<T>
     * @throws
     * @Title: query
     * @Description: 分页查询
     * @date 2018/3/14 16:56
     */
    @RequestMapping(method = RequestMethod.GET)
    Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean);

    /**
     * @param bean
     * @return List<T>
     * @throws
     * @Title: queryCond
     * @Description: 查询
     * @date 2018/3/14 16:56
     */
    @RequestMapping(value="list",method = RequestMethod.GET)
    List<T> queryCond(@Validated @ModelAttribute T bean);
}
