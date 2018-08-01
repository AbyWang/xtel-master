package com.cdxt.dl.web.res.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ResourcesDao {



	List<Map<String, Object>> getResourcesPage(@Param("newmap")Map<String, Object> newmap,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);




	int findCoursewareInfoByidcount(@Param("cpurseID")Integer cpurseID);


	void  deleteCoursewareInfoByid(@Param("courseId") Integer courseId);
	
}
