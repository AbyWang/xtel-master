package com.cdxt.dl.web.group.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.core.util.PageUtil;
import com.cdxt.dl.web.group.service.GroupManageService;
import com.cdxt.dl.web.sys.pojo.UserInfo;


@Controller
@RequestMapping("/group")
public class GroupManageController {

	@Autowired
	private GroupManageService groupManageService;





	/**
	 * 
	 * @Title: getgroupManagePage
	 * @author wangxiaolong
	 * @Description:查询群组分页信息
	 * @param
	 * @return
	 */
	@RequestMapping("/getGroupTree")
	@ResponseBody
	public ResJson getGroupTree(@Param("parentId")Integer parentId){
		return groupManageService.getGroupTree(parentId);
	}


	/**
	 * 
	 * @Title: getGroupRoomMemberWithPage
	 * @author wangxiaolong
	 * @Description:查看群组人员分页信息
	 * @param
	 * @return
	 */
	@RequestMapping("/getGroupRoomMemberWithPage")
	@ResponseBody
	public PagePojo getGroupRoomMemberWithPage(@Param(value="roomId")Integer roomId,@Param(value="pageNo")Integer pageNo,@RequestParam(value="pageSize")Integer pageSize){
		List<Map<String, Object>> list=groupManageService.getGroupRoomMemberWithPage(roomId,pageNo,pageSize);
		return PageUtil.Map2PageInfo(list);
	}


	/**
	 * 
	 * @Title: listAllGroup
	 * @author wangxiaolong
	 * @Description:
	 * @param
	 * @return
	 */
	@RequestMapping("/listAllGroup")
	@ResponseBody
	public ResJson listAllGroup(HttpServletRequest request){
		HttpSession session=request.getSession();
		UserInfo userInfo=(UserInfo)session.getAttribute(SysConstants.SYS_ADMIN);

		Integer groupId=userInfo.getGroupId();
		return groupManageService.listAllGroup(groupId);
	}


}
