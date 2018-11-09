package com.inspur.common.web.controller;


import com.inspur.common.web.model.Page;
import com.inspur.common.web.model.RequestPage;
import com.inspur.common.web.model.ResponseResult;
import com.inspur.common.web.model.ResponseValidate;
import com.inspur.common.web.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author hyluan
 * @ClassName: BaseControllerImpl 注意阀盖方法时入参上的注解不能继承，需要重新设置
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2018/3/14 15:18
 * @Copyright: Copyright (c) 2018 wisedu
 */
public abstract class BaseControllerImpl<T extends Serializable, P extends Serializable>
        implements BaseController<T, P> {

    protected AbstractService<T, P> service;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    public BaseControllerImpl(AbstractService service) {
        this.service = service;
    }

    public void setService(AbstractService<T, P> service) {
        this.service = service;
    }

    @Deprecated
    /**
     * @deprecated see  getService(Class<S> serviceClass)
     */
    protected <S> S getService() {
        return (S) this.service;
    }

    protected <S> S getService(Class<S> serviceClass) {
        return serviceClass.cast(this.service);
    }

    /**
     * @param requestPage
     * @param bean
     * @return cn.backflow.admin.common.pagination.Page<T>
     * @throws
     * @Title: query
     * @Description: (这里用一句话描述这个方法的作用)
     * @date 2018/3/14 16:56
     */
    @Override
    public Page<T> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute T bean) {
        return service.queryExample(requestPage.getPage(), requestPage.getLimit(), bean);
    }

    @Override
    public List<T> queryCond(@Validated @ModelAttribute T bean) {
        return service.query(bean);
    }

    /**
     * @param
     * @return
     * @throws
     * @Title:
     * @Description: 根据id查询一条记录
     * @date 2018/3/14 15:18
     */
    @Override
    public T get(@PathVariable("id") P id) {
        return service.get(id);
    }

    /**
     * 按条件查询一个
     *
     * @param bean
     * @return
     */
    @Override
    public T getCond(@Validated @ModelAttribute T bean) {
        return service.getCond(bean);
    }

    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: create
     * @Description: 保存新增
     * @date 2018/3/14 15:23
     */
    @Override
    public ResponseResult create(@RequestBody T bean) {
        return ResponseResult.create(service.add(bean));
    }


    /**
     * @param bean
     * @return ResponseResult
     * @throws
     * @Title: update
     * @Description: (这里用一句话描述这个方法的作用)
     * @date 2018/3/14 15:38
     */
    @Override
    public ResponseResult update(@RequestBody T bean) {
        return ResponseResult.create(service.update(bean));
    }

    /**
     * @param id,
     * @return ResponseResult
     * @throws
     * @Title: delete
     * @Description: 删除
     * @date 2018/3/14 15:39
     */
    @Override
    public ResponseResult delete(@PathVariable("id") P id) {
        return ResponseResult.create(service.delete(id));
    }


    /**
     * 批量删除
     *
     * @param items
     * @return
     */
    @Override
    public ResponseResult delete(@RequestParam("items") Set<P> items) {
        return ResponseResult.create(service.delete(items));
    }

    /**
     * 按条件删除
     *
     * @param bean
     * @return
     */
    @Override
    public ResponseResult deleteCond(@Validated @ModelAttribute T bean) {
        return ResponseResult.create(service.deleteCond(bean));
    }

    /**
     * 校验是否存在
     *
     * @param bean
     * @return
     */
    @Override
    public ResponseValidate validate(T bean) {
        return ResponseValidate.create(!service.existsBean(bean));
    }
}
