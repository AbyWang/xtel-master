/**
 * 
 * @ClassName: SystemController.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年8月3日
 */
package com.cdxt.dl.web.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.core.util.PropertyUtil;
import com.cdxt.dl.web.sys.service.SystemService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: SystemController.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年8月3日
 */
@Controller
@RequestMapping(value ="/systemController")
public class SystemController {
	
	
	@Resource
	private SystemService systemService;
	
	/**
	 * 
	 * @Title: getDbInfo
	 * @author wangxiaolong
	 * @Description:获取db配置
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getDbInfo")
	@ResponseBody
	public PagePojo getDbInfo(){

		List<Map<String, String>> mapList=new ArrayList<Map<String ,String>>();
		Map<String, String>dataMap=new HashMap<String, String>();
		//将数据放入pageInfo，pageInfo会对数据进行处理，这个是封装好的类，直接调用即可
		Map<String, String>map=PropertyUtil.getPropertiesValues("jdbc.properties");
		dataMap.put("url", map.get("jdbc.url"));
		dataMap.put("username", map.get("jdbc.username"));
		dataMap.put("password", map.get("jdbc.password"));
		mapList.add(dataMap);
		//封装bootstrap
		PagePojo page=new PagePojo();
		page.setPage(1);
		page.setTotal(1L);
		page.setRows(mapList);
		return page;
	}
	/**
	 * 
	 * @Title: updateDbInfo
	 * @author wangxiaolong
	 * @Description:修改数据库信息
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/updateDbInfo")
	@ResponseBody
	public ResJson updateDbInfo(HttpServletRequest request){
		Map<String, String>map=new HashMap<String,String>();
		String userName=request.getParameter("username");
		String url=request.getParameter("url");
		String password=request.getParameter("password");
		map.put("jdbc.url", url);
		map.put("jdbc.username", userName);
		map.put("jdbc.password", password);
		try {
			PropertyUtil.writeProperties("jdbc.properties",map);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResJson("0","修改失败");
		}
		
		return new ResJson("1","修改成功");
	}
	
	
	/**
	 * 
	 * @Title: getSystemVersion
	 * @author wangxiaolong
	 * @Description:获取系统版本号
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/listSystemVersion")
	@ResponseBody
	public PagePojo listSystemVersion(HttpServletRequest request,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		
		String id=request.getParameter("id");
		List<Map<String, Object>> mapList=systemService.listSystemVersion(id,pageNo,pageSize);
		PageInfo<Map<String, Object>> pageInfo=new PageInfo<Map<String, Object>>(mapList);
		//封装bootstrap
		PagePojo page=new PagePojo();
		page.setPage(pageInfo.getPageNum());
		//age.getPage()(pageInfo.getPages());
		page.setTotal(pageInfo.getTotal());
		page.setRows(mapList);
		return page;
	}

}
