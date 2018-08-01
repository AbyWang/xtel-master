package com.cdxt.dl.web.teach.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeachingManageDao {


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
}
