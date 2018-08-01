package com.cdxt.dl.web.res.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.util.PageUtil;
import com.cdxt.dl.web.res.service.LibraryService;

@Controller
public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	
	
	@RequestMapping(value ="/gotoLibraryPage")
	public String gotoResourcesPage(){
		return "res/library_list";
	}
	
	/**
	 * 
	 * @Title: getlibraryPage
	 * @author wangxiaolong
	 * @Description:查询文库分页信息
	 * @param
	 * @return
	 */
	@RequestMapping("/getlibraryPage")
	@ResponseBody
	public PagePojo getlibraryPage(@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,@Param(value="pageNo")Integer pageNo,@Param(value="pageSize")Integer pageSize) {
		Map<String, Object> newmap =new HashMap<String, Object>();
		newmap.put("nameVlaue", nameVlaue);
		newmap.put("idVlaue", idVlaue);
		List<Map<String, Object>> list =libraryService.getlibraryPage(newmap,pageNo,pageSize);
		return PageUtil.Map2PageInfo(list);
	}

}
