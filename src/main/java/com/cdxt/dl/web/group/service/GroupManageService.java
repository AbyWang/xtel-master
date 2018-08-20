package com.cdxt.dl.web.group.service;

import java.util.List;
import java.util.Map;

import com.cdxt.dl.core.model.ResJson;

public interface GroupManageService {

	/**
	 * 
	 * @Title: getgroupManagePage
	 * @author wangxiaolong
	 * @Description:查询群组分页信息
	 * @param
	 * @return
	 */
	ResJson  getGroupTree(Integer parentId);

	List<Map<String, Object>>  getGroupRoomMemberWithPage(Integer roomId, Integer startRow, Integer pageSize);

	/**
	 * 
	 * @Title: ListGroupTree
	 * @author wangxiaolong
	 * @Description:查询权限下的所有群组
	 * @param
	 * @return
	 */
	ResJson listAllGroup(Integer groupId);


}
