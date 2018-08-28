package com.cdxt.dl.web.teach.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.core.util.PageUtil;
import com.cdxt.dl.web.sys.pojo.UserInfo;
import com.cdxt.dl.web.teach.pojo.CourseInfo;
import com.cdxt.dl.web.teach.service.TeachingManageService;

@Controller
@RequestMapping("/teachingController")
public class TeachingManageController {
	
	
	@Autowired
	private TeachingManageService teachingManageService;
	

	/**
	 * 
	 * @Title: gotoRole
	 * @author wangxiaolong
	 * @Description:课程安排
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/gotoCoursePage")
	public String gotoCoursePage(){
		return "teach/courseList";
	}
	
	
	@RequestMapping(value ="/gotoArrangePage")
	public String gotoArrangePage(){
		return "teach/arrangeList";
	}
	
	

	/**
	 * 
	 * @Title: getoSignUp
	 * @author wangxiaolong
	 * @Description:报名跳转
	 * @param
	 * @return
	 */
	@RequestMapping("/getoCourseDetail")
	public String getoCourseDetail(HttpServletRequest request,@Param(value="courseId")Integer courseId){
		CourseInfo courseInfo=teachingManageService.getCourseInfoByid(courseId);
		request.setAttribute("courseInfo", courseInfo);
		return "teach/arrangeDetail";
	}
	
	/**
	 * 
	 * @Title: listCourseApply
	 * @author wangxiaolong
	 * @Description:查询所有的排课申请
	 * @param
	 * @return
	 */
	@RequestMapping("/listCourseApply")
	@ResponseBody
	public PagePojo listCourseApply(@Param(value="groupId")Integer groupId,HttpServletRequest request,
			@Param(value="pageNo")Integer pageNo,@Param(value="pageSize")Integer pageSize){
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute(SysConstants.SYS_ADMIN);
		if(groupId==null){
			groupId=userInfo.getGroupId();
		}
		List<Map<String,Object>>list= teachingManageService.listCourseApply(groupId,pageNo,pageSize);
		
		return PageUtil.Map2PageInfo(list);
	}
	
	/**
	 * 
	 * @Title: courseApply
	 * @author wangxiaolong
	 * @Description:课程审核
	 * @param
	 * @return
	 */
	@RequestMapping("/courseApply")
	@ResponseBody
	public ResJson courseApply(HttpServletRequest request,@Param("courseId")Integer courseId,
		@Param("numberOfExpected")Integer numberOfExpected,@Param("courseName")String courseName){


		return teachingManageService.applyAndAddMeetingRoom(courseId,numberOfExpected,courseName);	
	}
	
	/**
	 * 
	 * @Title: registerApply
	 * @author wangxiaolong
	 * @Description:报名审核
	 * @param
	 * @return
	 */
	@RequestMapping("/registerApply")
	@ResponseBody
	public ResJson registerApply(HttpServletRequest request,@Param(value="id")Integer id){

		return teachingManageService.registerApply(id);	

	}
	/**
	 * 
	 * @Title: getTeachingPage
	 * @author wangxiaolong
	 * @Description:查询课程的信息，带分页
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getCoursePage")
	@ResponseBody
	public PagePojo getCoursePage(@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,
			@Param(value="pageNo")Integer pageNo,@Param(value="pageSize")Integer pageSize) {
		
		Map<String, Object> newmap =new HashMap<String, Object>();
		newmap.put("nameVlaue", nameVlaue);
		newmap.put("idVlaue", idVlaue);
		List<Map<String, Object>> map=teachingManageService.getTeachingPage(newmap,pageNo,pageSize);
		return PageUtil.Map2PageInfo(map);
	}
	
	/**
	 * 
	 * @Title: getTeachingByid
	 * @author wangxiaolong
	 * @Description:根据id查看单一课程信息
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getTeachingByid")
	@ResponseBody
	public Map<String,Object>  getTeachingByid(@RequestParam("CpurseID")int CpurseID) throws Exception{
		Map<String, Object> map=teachingManageService.getTeachingByid(CpurseID);
		return map;

	}
	
	/**
	 * 
	 * @Title: updateTeachingStatus
	 * @author wangxiaolong
	 * @Description:根据id修改课程审核状态
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/updateTeachingStatus")
	@ResponseBody
	public Map<String,Object> updateTeachingStatus(@RequestParam("teachingID")int teachingID){
		Map<String, Object> result =new HashMap<String, Object>();
		Map<String, Object> map =new HashMap<String, Object>();
		try {
			map.put("teachingID", teachingID);
			teachingManageService.updateTeachingStatus(teachingID);
			result.put("flag", true);
			result.put("massge", "审核成功");
			return result;
		} catch (Exception e) {
			result.put("flag", false);
			result.put("massgefalse", "审核失败");
			return result;
		}
		
	}
	
	
	/**
	 * 
	 * @Title: getCourseArrangementeInfoPage
	 * @author wangxiaolong
	 * @Description:获取课程安排信息分页
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getCourseArrangePage")
	@ResponseBody
	public PagePojo getCourseArrangementeInfoPage(@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,@Param(value="pageNo")Integer pageNo,@RequestParam(value="pageSize")Integer pageSize){
		
		Map<String, Object> newmap =new HashMap<String, Object>();
		newmap.put("nameVlaue", nameVlaue);
		newmap.put("idVlaue", idVlaue);
		List<Map<String, Object>> map=teachingManageService.getCourseArrangementeInfoPage(newmap,pageNo,pageSize);

		return PageUtil.Map2PageInfo(map);
		
	}
	
	/**
	 * 
	 * @Title: toCourseArrangementStatus
	 * @author wangxiaolong
	 * @Description:改变排课申请表状态
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/toCourseArrangementStatus/{id}")
	@ResponseBody
	public Map<String,Object> toCourseArrangementStatus(@PathVariable("id")int  id){
		 Map<String,Object>  result=new HashMap<>();
		try {
			teachingManageService.updateCourseArrangementStatus(id);
			result.put("flag", true);
			result.put("massge", "审核成功");
			return result;
		} catch (Exception e) {
			result.put("flag", true);
			result.put("massge", "审核失败");
			return result;
		}
	  
  }
	
	/**
	 * 
	 * @Title: listRegister
	 * @author wangxiaolong
	 * @Description:报名申请
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/listRegister")
	@ResponseBody
	public PagePojo listRegister(HttpServletRequest request,@Param(value="pageNo")Integer pageNo,@Param(value="pageSize")Integer pageSize){
		
		List<Map<String, Object>> map=teachingManageService.listRegister(pageNo,pageSize);
		return PageUtil.Map2PageInfo(map);
	}
	

}
