package com.cdxt.dl.web.teach.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdxt.dl.web.teach.dao.TeachingManageDao;
import com.cdxt.dl.web.teach.service.TeachingManageService;
import com.github.pagehelper.PageHelper;
@Service
public class TeachingManageServiceImpl implements TeachingManageService {
	@Autowired
	private TeachingManageDao teachingManageDao;


	/**
	 * 
	 * @Title: getTeachingPage
	 * @Description:查询课程的信息，带分页
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTeachingPage(Map<String, Object> newmap, Integer pageNo, Integer pageSize) {

		//分页
		PageHelper.startPage(pageNo, pageSize);
		return teachingManageDao.getTeachingPage(newmap, pageNo, pageSize);

	}
	
	/**
	 * 
	 * @Title: getTeachingByid
	 * @author wangxiaolong
	 * @Description:根据id查看单一课程信息
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> getTeachingByid(int cpurseID) {

		return teachingManageDao.getTeachingByid(cpurseID);
	}

	
	/**
	 * 
	 * @Title: updateTeachingStatus
	 * @author wangxiaolong
	 * @Description:根据id修改课程审核状态
	 * @param
	 * @return
	 */
	@Override
	public int updateTeachingStatus(int teachingID) {

		return teachingManageDao.updateTeachingStatus(teachingID);

	}


	@Override
	public 	List<Map<String, Object>>  getCourseArrangementeInfoPage(Map<String, Object> newmap, Integer pageNo,Integer pageSize) {

		//分页
		PageHelper.startPage(pageNo, pageSize);
		return teachingManageDao.getCourseArrangementeInfoPage(newmap,pageNo,pageSize);
	}


	@Override
	public void updateCourseArrangementStatus(int id){

		teachingManageDao.updateCourseArrangementStatus(id);

	}

}
