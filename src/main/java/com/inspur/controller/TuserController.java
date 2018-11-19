package com.inspur.controller;

import com.inspur.common.web.controller.BaseControllerImpl;
import com.inspur.common.web.service.AbstractService;
import com.inspur.data.Tuser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TuserController
 */
@RestController
@RequestMapping(value = "/tuser")
public class TuserController extends BaseControllerImpl<Tuser, java.lang.String>{

	public TuserController(@Qualifier("tuserService") AbstractService service) {
		super(service);
	}
}
