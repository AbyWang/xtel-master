package com.cdxt.dl.web.group.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.web.group.dao.GroupManageDao;
import com.cdxt.dl.web.group.pojo.GroupInfo;
import com.cdxt.dl.web.group.service.GroupManageService;
import com.github.pagehelper.PageHelper;
@Service
public class GroupManageServiceImpl implements GroupManageService {


	@Autowired
	private GroupManageDao groupManageDao;

	@Override
	public ResJson getGroupTree(Integer parentId){

		List<GroupInfo>list=groupManageDao.getGroupTree(parentId);
		Map<String,Object> map = null;
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();

		for(GroupInfo group:list){
			map = new HashMap<String,Object>();
			map = new HashMap<String,Object>();
			map.put("name",group.getName());

			if (group.getIsLeaf()!=null&&group.getIsLeaf()==1) {
				map.put("type","item");

			}else {
				map.put("parentId",group.getId());
				map.put("type","folder");
			}
			dataList.add(map);
		}
		return new ResJson(SysConstants.STRING_ONE,"查询成功",dataList);
	}

	@Override
	public List<Map<String, Object>> getGroupRoomMemberWithPage(Integer roomId, Integer pageNo, Integer pageSize){

		PageHelper.startPage(pageNo, pageSize);
		return groupManageDao.getGroupRoomMemberWithPage(roomId);

	}


	/**
	 * 
	 * @Title: ListGroupTree
	 * @author wangxiaolong
	 * @Description:查询权限下的所有群组
	 * @param
	 * @return
	 */
	public ResJson listAllGroup(Integer groupId){
		List<GroupInfo>groupList=groupManageDao.listGroupTree(groupId);
		List<Map<String,Object>>dataList=new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (GroupInfo group : groupList) {
			map = new HashMap<String,Object>();
			map.put("id", group.getId());
			map.put("name", group.getName());
			map.put("nocheck", false);
			map.put("struct","TREE"); 
			//map.put("otherParam", group);
			if (group.getSuperiorGroupID()!=null) {
				map.put("parentId",group.getSuperiorGroupID());
				map.put("open",false);
			}else {
				map.put("parentId","0");
				map.put("open",false);
			}
			dataList.add(map);
		}
		return new ResJson(SysConstants.STRING_ONE,"",dataList);
	}


}
