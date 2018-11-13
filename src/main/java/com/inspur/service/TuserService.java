package com.inspur.service;

import com.inspur.common.web.service.AbstractService;
import com.inspur.data.Tuser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * TuserService
 */
@Service
public class TuserService extends AbstractService<Tuser, java.lang.String>{

	public TuserService(@Qualifier("tuserMapper") Mapper<Tuser> mapper) {
		super(mapper);
	}

}
