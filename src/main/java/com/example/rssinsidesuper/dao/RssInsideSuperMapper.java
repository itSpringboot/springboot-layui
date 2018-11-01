package com.example.rssinsidesuper.dao;

import com.example.rssinsidesuper.data.RssInsideSuper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RssInsideSuperMapper {
    int deleteByPrimaryKey(String id);

    int insert(RssInsideSuper record);

    int insertSelective(RssInsideSuper record);

    RssInsideSuper selectByPrimaryKey(String id);
    List<RssInsideSuper> getAlluser();

    int updateByPrimaryKeySelective(RssInsideSuper record);

    int updateByPrimaryKey(RssInsideSuper record);
}