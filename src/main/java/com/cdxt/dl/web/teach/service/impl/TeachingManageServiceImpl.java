package com.cdxt.dl.web.teach.service.impl;

import java.util.HashMap;
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
	public ResJson  applyAndAddMeetingRoom(Integer courseId,Integer numberOfExpected,String courseName){
		int result=0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> map =null;
		Map<String, Object> idMap =null;
		Map<String, Object> retMap =null;
		paramMap.put("ROOMNAME", courseName);
		paramMap.put("ROOM_CAPACITY", numberOfExpected);
		//创建会议室
		teachingManageDao.addMeetingRoom(paramMap);
		//存储过程返回roomId
		Integer roomId=(Integer) paramMap.get("ROOM_ID");
		if(roomId==null){
			return new ResJson(SysConstants.STRING_ZERO,"审核失败");
		}
		//审核通过,更新课程申请状态、roomid
		result=teachingManageDao.courseApply(roomId,courseId);
		if(result!=1){
			return new ResJson(SysConstants.STRING_ZERO,"审核失败");
		}
		map=new HashMap<String, Object>();
		map.put("ROOMID_in", roomId);
		map.put("LOCK_TYPE", 1);
		teachingManageDao.lockRoom(paramMap);
		//存储过程返回锁定结果
		//Integer resultOut=(Integer) map.get("RESULT_out"); 
		String userStrPapam=roomId+"_Recorder";
		idMap=new HashMap<String, Object>();
		idMap.put("USERSTRPARAM", userStrPapam);
		//获取录制客户端id
		teachingManageDao.getUserId(idMap);
		Integer useridRet=(Integer) idMap.get("USERID_RET");
		if(useridRet!=null){
			retMap=new  HashMap<String, Object>();
			retMap.put("USERID_IN", useridRet);
			retMap.put("ROOMID_IN", roomId);
		}
		return new ResJson(SysConstants.STRING_ONE,"审核成功");
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
