package com.inspur.controller;

import com.inspur.common.util.UUIDutil;
import com.inspur.common.web.controller.BaseControllerImpl;
import com.inspur.common.web.model.Page;
import com.inspur.common.web.model.RequestPage;
import com.inspur.common.web.model.ResponseResult;
import com.inspur.common.web.service.AbstractService;
import com.inspur.data.RssInsideSuper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * RssInsideSuperController
 */
@RestController
@RequestMapping(value = "/rssinsidesuper")
public class RssInsideSuperController extends BaseControllerImpl<RssInsideSuper, java.lang.String>{

	public RssInsideSuperController(@Qualifier("rssInsideSuperService") AbstractService service) {
		super(service);
	}
	/**
	 * 分页查询列表数据
	 */
	@RequestMapping("/query")
	public Page<RssInsideSuper> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute RssInsideSuper rssInsideSuper) {
		return super.query(requestPage, rssInsideSuper);
	}
	/**
	 * 根据主键查询数据
	 */
	@RequestMapping("/get")
	public RssInsideSuper get(@RequestParam(value="id") java.lang.String id ) {
		return super.get(id);
	}
	/**
	 * 根据主键删除数据
	 */
	@RequestMapping("/delete/{id}")
	public ResponseResult delete(@PathVariable java.lang.String id ) {
		return super.delete(id);
	}

	/**
	 *保存操作
	 */
	@RequestMapping(value = "/save", produces = {"application/json;charset=UTF-8"})
	public ResponseResult save(@Validated @ModelAttribute RssInsideSuper rssInsideSuper){
		ResponseResult responseResult;
		if(rssInsideSuper.getId()==null||"".equals(rssInsideSuper.getId())){
			rssInsideSuper.setId(UUIDutil.UUID());
			responseResult = super.create(rssInsideSuper);
		}else{
			responseResult = super.update(rssInsideSuper);
		}
		return responseResult;
	}
}
