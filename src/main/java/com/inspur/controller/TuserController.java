package com.inspur.controller;

import com.inspur.common.util.UUIDutil;
import com.inspur.common.web.controller.BaseControllerImpl;
import com.inspur.common.web.model.Page;
import com.inspur.common.web.model.RequestPage;
import com.inspur.common.web.model.ResponseResult;
import com.inspur.common.web.service.AbstractService;
import com.inspur.data.Tuser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * TuserController
 */
@RestController
@RequestMapping(value = "/tuser")
public class TuserController extends BaseControllerImpl<Tuser, java.lang.String>{

	public TuserController(@Qualifier("tuserService") AbstractService service) {
		super(service);
	}
	/**
	 * 分页查询列表数据
	 */
	@RequestMapping("/query")
	public Page<Tuser> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute Tuser tuser) {
		return super.query(requestPage, tuser);
	}
	/**
	 * 根据主键查询数据
	 */
	@RequestMapping("/get")
	public Tuser get(@RequestParam(value="id") java.lang.String id ) {
			return super.get(id);
	}
	/**
	 * 根据主键删除数据
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(@RequestParam(value="id") java.lang.String id ) {
			return super.delete(id);
	}

	/**
	 *保存操作
	 */
	@RequestMapping(value = "/save")
	public ResponseResult save(@Validated @ModelAttribute Tuser tuser){
		ResponseResult responseResult;
		if(tuser.getId()==null||"".equals(tuser.getId())){
			tuser.setId(UUIDutil.UUID());
			responseResult = super.create(tuser);
		}else{
			responseResult = super.update(tuser);
		}
		return responseResult;
	}
}
