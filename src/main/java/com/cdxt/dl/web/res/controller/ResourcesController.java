package com.cdxt.dl.web.res.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.util.PageUtil;
import com.cdxt.dl.web.res.service.ResourcesService;

@Controller
public class ResourcesController {
	
	@Autowired
	private ResourcesService resourcesService;
	
	
	@RequestMapping(value ="/gotoResourcesPage")
	public String gotoResourcesPage(){
		return "res/courseWare_list";
	}
	

	/**
	 * 
	 * @Title: getResourcesPage
	 * @author wangxiaolong
	 * @Description:获取课件管理分页信息
	 * @param
	 * @return
	 */
	@RequestMapping("/getResourcesPage")
	@ResponseBody
	public PagePojo getResourcesPage(@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,@Param(value="pageNo")Integer pageNo,@RequestParam(value="pageSize")Integer pageSize){
		
	
			Map<String, Object> newmap =new HashMap<String, Object>();
			newmap.put("nameVlaue", nameVlaue);
			newmap.put("idVlaue", idVlaue);
			List<Map<String, Object>> map=resourcesService.getResourcesPage(newmap,pageNo,pageSize);
			return PageUtil.Map2PageInfo(map);
	
	}
	
	
	/**
	 * @描述:查询课件属于课程的数量
	 * @方法名: findCoursewareInfoByidcount
	 * @param courseId
	 * @return
	 * @返回类型 Map<String,Object>
	 * @创建人 张兴成
	 * @创建时间 2018年4月27日下午2:45:07
	 * @修改人 张兴成
	 * @修改时间 2018年4月27日下午2:45:07
	 * @修改备注
	 * @since
	 * @throws
	 */
	@RequestMapping("/findCoursewareInfoByidcount/{courseId}")
	@ResponseBody
	public Map<String,Object> findCoursewareInfoByidcount(@PathVariable(value="courseId")int courseId){
		
		Map<String,Object> result=new HashMap<>();
		try {
			int  count=resourcesService.findCoursewareInfoByidcount(courseId);
			result.put("flag", true);
			result.put("courseCount", count);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("massge", "查询异常");
			return result;
		}
		
	}
	
	/**
	 * @描述:根据id删除课件信息
	 * @方法名: deleteCoursewareInfoByid
	 * @param courseId
	 * @return
	 * @返回类型 Map<String,Object>
	 * @创建人 张兴成
	 * @创建时间 2018年4月27日下午2:46:26
	 * @修改人 张兴成
	 * @修改时间 2018年4月27日下午2:46:26
	 * @修改备注
	 * @since
	 * @throws
	 */
	@RequestMapping("/deleteCoursewareInfoByid/{courseId}")
	@ResponseBody
	public Map<String,Object> deleteCoursewareInfoByid(@PathVariable(value="courseId")int courseId){
		
		Map<String,Object> result=new HashMap<>();
		try {
			resourcesService.deleteCoursewareInfoByid(courseId);
			result.put("flag", true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("massge", "查询异常");
			return result;
		}
		
	}
	

}
