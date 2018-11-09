package com.inspur.controller;

import com.inspur.common.web.controller.BaseControllerImpl;
import com.inspur.common.web.model.Page;
import com.inspur.common.web.model.RequestPage;
import com.inspur.common.web.model.ResponseResult;
import com.inspur.common.web.service.AbstractService;
import com.inspur.data.RssInsideSuper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * RssInsideSuperController
 */
@Controller
@RequestMapping(value = "/rssinsidesuper")
public class RssInsideSuperController extends BaseControllerImpl<RssInsideSuper, String> {

	public RssInsideSuperController(@Qualifier("rssInsideSuperService") AbstractService service) {
		super(service);
	}
	/**
	 * 分页查询列表数据
	 */
	@Override
	@RequestMapping("/query")
	@ResponseBody
	public Page<RssInsideSuper> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute RssInsideSuper rssInsideSuper) {
		return super.query(requestPage, rssInsideSuper);
	}
	/**
	 * 根据主键查询数据
	 */
	@RequestMapping("/get")
	@ResponseBody
	public RssInsideSuper get(@RequestParam(value="id") java.lang.String id ) {
			return super.get(id);
	}
	/**
	 * 编辑操作
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable java.lang.String id ,ModelMap map) {
			map.addAttribute("data", super.get(id));
			return "jcy/rssinsidesuperedit";
	}
	/**
	 * 根据主键删除数据
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public ResponseResult delete(@PathVariable java.lang.String id ) {
			return super.delete(id);
	}

	/**
	 *保存操作
	 */
	@RequestMapping(value = "/save", produces = {"application/json;charset=UTF-8"})
	public String save(@Validated @ModelAttribute RssInsideSuper rssInsideSuper){
		if(rssInsideSuper.getId()==null||"".equals(rssInsideSuper.getId())){
			rssInsideSuper.setId(UUID.randomUUID().toString().replace("-",""));
			super.create(rssInsideSuper);
		}else{
			super.update(rssInsideSuper);
		}
		return "redirect:/admin/jcy/rssinsidesuper";
	}
}
