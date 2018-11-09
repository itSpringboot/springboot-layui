package com.inspur.service;

import com.example.commonweb.service.AbstractService;
import com.inspur.data.RssInsideSuper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * RssInsideSuperService
 */
@Service
public class RssInsideSuperService extends AbstractService<RssInsideSuper, java.lang.String>{

	public RssInsideSuperService(@Qualifier("rssInsideSuperMapper") Mapper<RssInsideSuper> mapper) {
		super(mapper);
	}

}
