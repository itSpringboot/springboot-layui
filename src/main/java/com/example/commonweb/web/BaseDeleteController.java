package com.example.commonweb.web;

import com.example.commonweb.model.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;

public interface BaseDeleteController<T extends Serializable, P extends Serializable> {

    /**
     *  删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    ResponseResult delete(@PathVariable("id") P id);

    /**
     *  批量删除
     * @param items
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    ResponseResult delete(@RequestParam("items[]") Set<P> items);

    /**
     *  按条件删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "cond", method = RequestMethod.DELETE)
    ResponseResult deleteCond(@Validated @ModelAttribute T bean);
}
