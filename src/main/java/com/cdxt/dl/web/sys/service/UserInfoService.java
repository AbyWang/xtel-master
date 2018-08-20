package com.cdxt.dl.web.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cdxt.dl.web.sys.pojo.MenuFunction;
import com.cdxt.dl.web.sys.pojo.SystemManager;
import com.cdxt.dl.web.sys.pojo.UserInfo;

@Transactional
public interface UserInfoService {

	List<UserInfo> getUserWithPage(String nameVlaue,Integer idVlaue,Integer limit,Integer pageNumber);

	List<Map<String, Object>> getRoleWithPage(Integer limit, Integer pageNumber);


	UserInfo getUserInfoByLoginName(String loginName);
	
	SystemManager getSystemManagerByLoginName(String loginName);

	int updateSystemManager(Integer id,long loginTime,Integer isOnline);

	List<MenuFunction> getUserMenuList();
	
	int updateOnlineByUserId(Integer id,long loginTime,Integer isOnline);
	
}
