package com.cdxt.dl.web.teach.service;

import java.util.List;
import java.util.Map;

import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.web.teach.pojo.CourseInfo;

public interface TeachingManageService {
	
	/**
	 * 
	 * @Title: listCourseApply
	 * @author wangxiaolong
	 * @Description:查询权限下的所有课程申请
	 * @param
	 * @return
	 */
	List<Map<String,Object>> listCourseApply(Integer groupId,Integer pageNo, Integer pageSize);

	/**
	 * 
	 * @Title: getCourseInfoByid
	 * @author wangxiaolong
	 * @Description:查询课程详细信息
	 * @param
	 * @return
	 */
	CourseInfo getCourseInfoByid(int cpurseID);
	/**
	 * 
	 * @Title: courseApply
	 * @author wangxiaolong
	 * @Description:课程申请审批
	 * @param
	 * @return
	 */
	ResJson  applyAndAddMeetingRoom(Integer courseId,Integer numberOfExpected,String courseName);
	
	
	
	/**
	 * 
	 * @Title: registerApply
	 * @author wangxiaolong
	 * @Description:报名审核
	 * @param
	 * @return
	 */
	ResJson registerApply(int id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	List<Map<String, Object>> getTeachingPage(Map<String, Object> newmap, Integer pageNo, Integer pageSize);

	Map<String, Object> getTeachingByid(int cpurseID);

	int updateTeachingStatus(int teachingID);

	List<Map<String, Object>> getCourseArrangementeInfoPage(Map<String, Object> newmap, Integer startRow, Integer pageSize);

	void updateCourseArrangementStatus(int id);
	
	/**
	 * 
	 * @Title: listRegister
	 * @author wangxiaolong
	 * @Description:报名申请列表
	 * @param
	 * @return
	 */
	List<Map<String,Object>> listRegister(Integer pageNo, Integer pageSize);

}
