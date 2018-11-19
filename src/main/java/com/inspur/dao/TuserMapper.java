package com.inspur.dao;

import com.inspur.data.Tuser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TuserMapper extends Mapper<Tuser> {
    public List queryByname(String name);
}