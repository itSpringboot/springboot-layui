package com.example.commonweb.service;

import com.example.commonweb.model.Page;
import com.example.commonweb.util.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

/**
 * 单表操作
 *
 * @ClassName: AbstractService.java
 * @Description: 通用service类
 * 子类覆盖构造器注入mapper即可使用 例如：
 * @public XszcZpService(@ Qualifier ( " xszcZpMapper ") Mapper<XszcZp> mapper) {
 * super(mapper);
 * }
 */
public abstract class AbstractService<T extends Serializable, P extends Serializable> {

    protected Mapper<T> mapper;

    private Class clazz;

    public AbstractService(Mapper<T> mapper) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        this.mapper = mapper;
    }

    protected <S> S getMapper() {
        return (S) this.mapper;
    }

    public T get(P id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public T getCond(T bean) {
        List<T> select = this.mapper.select(bean);
        if (select.isEmpty()) {
            return null;
        }
        return select.get(0);
    }

    public int add(T bean) {
        return this.mapper.insertSelective(bean);
    }

    public int update(T bean) {
        return this.mapper.updateByPrimaryKeySelective(bean);
    }

    public int update(T bean, Example example) {
        return this.mapper.updateByExample(bean, example);
    }

    public int delete(P id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    public int deleteCond(T bean) {
        return this.mapper.delete(bean);
    }

    public int delete(Example example) {
        return this.mapper.deleteByExample(example);
    }

    public int delete(Collection<P> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn("id", ids);
        return this.mapper.deleteByExample(example);
    }

    public boolean existsBean(T bean) {
        return this.mapper.selectCount(bean) > 0 ? true : false;
    }

    public boolean exists(P id) {
        return this.mapper.existsWithPrimaryKey(id);
    }

    public Page<T> query(Integer pageNumber, Integer pageSize, T bean) {
        int trunlatePageNumber = trunlatePageNumber(pageNumber);
        int trunlatePageSize = trunlatePageSize(pageSize);
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(trunlatePageNumber, trunlatePageSize);
        List<T> select = this.mapper.select(bean);
        return new Page<T>(0, (int)startPage.getTotal(),"", select);
    }

    public List<T> query(T bean) {
        return this.mapper.select(bean);
    }

    public T queryOne(T bean) {
        return this.mapper.selectOne(bean);
    }

    public List<T> query(Example e) {
        return this.mapper.selectByExample(e);
    }

    public List<T> queryAll() {
        return this.mapper.selectAll();
    }

    public Page<T> queryExample(Integer pageNumber, Integer pageSize, T bean) {
        int trunlatePageNumber = trunlatePageNumber(pageNumber);
        int trunlatePageSize = trunlatePageSize(pageSize);
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Object fieldValue = ReflectionUtils.getFieldValue(bean, field.getName());
            if (!ObjectUtils.isEmpty(fieldValue)) {
                criteria.andLike(field.getName(), "%" + fieldValue + "%");
            }
        }
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(trunlatePageNumber, trunlatePageSize);
        List<T> select = this.mapper.selectByExample(example);
        return new Page<T>(0, (int)startPage.getTotal(),"", select);
    }

    public int count(T bean) {
        return this.mapper.selectCount(bean);
    }

    public int count(Example example) {
        return this.mapper.selectCountByExample(example);
    }

    protected int trunlatePageNumber(Integer pageNumber) {
        return pageNumber == null ? 1 : pageNumber;
    }

    protected int trunlatePageSize(Integer pageSize) {
        return pageSize == null ? 10 : pageSize;
    }

}
