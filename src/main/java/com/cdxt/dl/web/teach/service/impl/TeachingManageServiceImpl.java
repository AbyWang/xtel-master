package com.cdxt.dl.web.teach.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.web.teach.dao.TeachingManageDao;
import com.cdxt.dl.web.teach.pojo.CourseInfo;
import com.cdxt.dl.web.teach.service.TeachingManageService;
import com.github.pagehelper.PageHelper;
@Service
public class TeachingManageServiceImpl implements TeachingManageService {
	@Autowired
	private TeachingManageDao teachingManageDao;


	/**
	 * 
	 * @Title: listCourseApply
	 * @Description:查询排课申请
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> listCourseApply(Integer groupId,Integer pageNo, Integer pageSize){
		//分页
		PageHelper.startPage(pageNo, pageSize);
		return teachingManageDao.listCourseApply(groupId);
	}
	
	
	/**
	 * 
	 * @Title: courseApply
	 * @Description:课程申请审批
	 * @param
	 * @return
	 */
	public ResJson  courseApply(Integer couorseId){
		int result=0;
		result= teachingManageDao.courseApply(couorseId);
		if(result==1){
			return new ResJson(SysConstants.STRING_ONE,"审核成功");
		}
		return new ResJson(SysConstants.STRING_ZERO,"审核失败");
	}
	
	
	public 	ResJson registerApply(int id){
		int result=0;
		result= teachingManageDao.registerApply(id);
		if(result==1){
			return new ResJson(SysConstants.STRING_ONE,"审核成功");
		}
		return new ResJson(SysConstants.STRING_ZERO,"审核失败");
		
	}
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
	
	public 	List<Map<String,Object>> listRegister(Integer pageNo, Integer pageSize){
		//分页
		PageHelper.startPage(pageNo, pageSize);
		return teachingManageDao.listRegister();
	}

	/**
	 * 
	 * @Title: getCourseInfobyCpurseID
	 * @author wangxiaolong
	 * @Description:查询课程信息单一记录
	 * @param
	 * @return
	 */
	@Override
	public CourseInfo getCourseInfoByid(int cpurseID)  {

		return teachingManageDao.getCourseInfoByid(cpurseID);

	}
}
