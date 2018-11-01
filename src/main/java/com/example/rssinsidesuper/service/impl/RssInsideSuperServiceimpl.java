package com.example.rssinsidesuper.service.impl;

import com.example.rssinsidesuper.dao.RssInsideSuperMapper;
import com.example.rssinsidesuper.data.RssInsideSuper;
import com.example.rssinsidesuper.service.RssInsideSuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wu.bing on 2018/10/10.
 */
@Service
public class RssInsideSuperServiceimpl implements RssInsideSuperService{

    @Autowired
    private RssInsideSuperMapper rssInsideSuperMapper;
    @Override
    public int insert(RssInsideSuper record) {
        return rssInsideSuperMapper.insert(record);
    }

    @Override
    public RssInsideSuper selectByPrimaryKey(String id) {
        return rssInsideSuperMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RssInsideSuper> getAlluser() {
        return rssInsideSuperMapper.getAlluser();
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return rssInsideSuperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RssInsideSuper record) {
        return rssInsideSuperMapper.updateByPrimaryKeySelective(record);
    }
}
