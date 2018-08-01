package com.cdxt.dl.web.res.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdxt.dl.web.res.dao.ResourcesDao;
import com.cdxt.dl.web.res.service.ResourcesService;
import com.github.pagehelper.PageHelper;
@Service
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesDao resourcesDao;

	@Override
	public List<Map<String, Object>> getResourcesPage(Map<String, Object> newmap, Integer pageNo, Integer pageSize){
		//分页
		PageHelper.startPage(pageNo, pageSize);
		return resourcesDao.getResourcesPage(newmap, pageNo, pageSize);

	}

	@Override
	public int findCoursewareInfoByidcount(int courseId)  {
			return resourcesDao.findCoursewareInfoByidcount(courseId);
	}

	@Override
	public void deleteCoursewareInfoByid(int courseId){
		
		resourcesDao.deleteCoursewareInfoByid(courseId);
		
	}

}
