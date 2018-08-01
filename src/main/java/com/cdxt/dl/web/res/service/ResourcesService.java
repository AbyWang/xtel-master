package com.cdxt.dl.web.res.service;

import java.util.List;
import java.util.Map;

public interface ResourcesService {

	List<Map<String, Object>> getResourcesPage(Map<String, Object> newmap, Integer startRow, Integer pageSize);

	int findCoursewareInfoByidcount(int courseId);

	void deleteCoursewareInfoByid(int courseId);

}
