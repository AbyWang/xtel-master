package com.cdxt.dl.web.res.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.util.PageUtil;
import com.cdxt.dl.web.res.service.DataService;

@Controller
public class DataController {
	
	@Autowired
	private DataService dataService;
	
	@RequestMapping(value ="/gotoDataPage")
	public String gotoDataPage(){
		return "res/data_list";
	}
	
	/**
	 * 
	 * @Title: getdataPage
	 * @author wangxiaolong
	 * @Description:获取资料分页信息
	 * @param
	 * @return
	 */
	@RequestMapping("/getDataPage")
	@ResponseBody
	public PagePojo getdataPage(@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,@Param(value="pageNo")Integer pageNo,@RequestParam(value="pageSize")Integer pageSize) {
		Map<String, Object> newmap =new HashMap<String, Object>();
		newmap.put("nameVlaue", nameVlaue);
		newmap.put("idVlaue", idVlaue);
		List<Map<String, Object>> map=dataService.getdataPage(newmap,pageNo,pageSize);
		return PageUtil.Map2PageInfo(map);
		
	}

}
