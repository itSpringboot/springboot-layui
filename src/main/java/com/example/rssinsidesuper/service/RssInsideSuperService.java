package com.example.rssinsidesuper.service;

import com.example.rssinsidesuper.data.RssInsideSuper;

import java.util.List;

/**
 * Created by wu.bing on 2018/10/10.
 */
public interface RssInsideSuperService {

    int insert(RssInsideSuper record);

    RssInsideSuper selectByPrimaryKey(String id);

    List<RssInsideSuper> getAlluser();

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RssInsideSuper record);
}
