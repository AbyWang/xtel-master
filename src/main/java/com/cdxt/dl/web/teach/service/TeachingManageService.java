package com.cdxt.dl.web.teach.service;

import java.util.List;
import java.util.Map;

public interface TeachingManageService {

	List<Map<String, Object>> getTeachingPage(Map<String, Object> newmap, Integer pageNo, Integer pageSize);

	Map<String, Object> getTeachingByid(int cpurseID);

	int updateTeachingStatus(int teachingID);

	List<Map<String, Object>> getCourseArrangementeInfoPage(Map<String, Object> newmap, Integer startRow, Integer pageSize);

	void updateCourseArrangementStatus(int id);

}
