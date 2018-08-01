package com.cdxt.dl.web.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cdxt.dl.web.group.pojo.GroupInfo;

public interface GroupManageDao {


	List<GroupInfo> getGroupTree(@Param("parentId")Integer parentId);


	List<Map<String, Object>> getGroupRoomMemberWithPage(@Param("roomId")Integer roomId);

}
