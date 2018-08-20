package com.cdxt.dl.web.teach.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cdxt.dl.web.teach.pojo.CourseInfo;

public interface TeachingManageDao {

	/**
	 * 
	 * @Title: listCourseApply
	 * @author wangxiaolong
	 * @Description:课程申请列表
	 * @param
	 * @return
	 */
	List<Map<String,Object>> listCourseApply(@Param("groupId")Integer groupId);

	/**
	 * @Title: courseApply
	 * @author wangxiaolong
	 * @Description:课程申请审批
	 * @param
	 * @return
	 */
	int courseApply(int courseId);
	
	
	CourseInfo getCourseInfoByid(int cpurseID);
	/**
	 * 
	 * @Title: registerApply
	 * @author wangxiaolong
	 * @Description:报名审核
	 * @param
	 * @return
	 */
	int registerApply(int id);
	/**
	 * 
	 * @Title: getTeachingPage
	 * @author wangxiaolong
	 * @Description:查询课程的信息，带分页
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getTeachingPage(@Param("newmap")Map<String, Object> newmap,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);



	/**
	 * 
	 * @Title: getTeachingByid
	 * @author wangxiaolong
	 * @Description:根据id查看单一课程信息
	 * @param
	 * @return
	 */
	Map<String, Object>  getTeachingByid(@Param("cpurseID")Integer cpurseID);


	/**
	 * 
	 * @Title: updateTeachingStatus
	 * @author wangxiaolong
	 * @Description:根据id修改课程审核状态
	 * @param
	 * @return
	 */
	int  updateTeachingStatus(@Param("teachingID") Integer teachingID);

	/**
	 * 
	 * @Title: getCourseArrangementeInfoPage
	 * @author wangxiaolong
	 * @Description:
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getCourseArrangementeInfoPage(Map<String, Object> newmap,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);


	void updateCourseArrangementStatus(@Param("id")int id);

	/**
	 * 
	 * @Title: listRegister
	 * @author wangxiaolong
	 * @Description:排课申请列表
	 * @param
	 * @return
	 */
	List<Map<String, Object>> listRegister();
}
