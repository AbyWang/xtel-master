package com.cdxt.dl.web.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cdxt.dl.web.sys.pojo.MenuFunction;
import com.cdxt.dl.web.sys.pojo.SystemManager;
import com.cdxt.dl.web.sys.pojo.UserInfo;

public interface UserInfoDao {


	/**
	 * 
	 * @Title: getSystemManagerByLoginName
	 * @author wangxiaolong
	 * @Description:通过名字查询管理员
	 * @param
	 * @return
	 */
	List<UserInfo>    getUserWithPage(@Param("nameVlaue")String nameVlaue,@Param("idVlaue")Integer idVlaue);


	/**
	 * 
	 * @Title: updateSystemManager
	 * @author wangxiaolong
	 * @Description:更新在线时间
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getRoleWithPage();

	/**
	 * 
	 * @Title: getSystemManagerByLoginName
	 * @author wangxiaolong
	 * @Description:通过名字查询管理员
	 * @param
	 * @return
	 */
	SystemManager  getSystemManagerByLoginName(@Param("loginName")String loginName);


	/**
	 * 
	 * @Title: updateSystemManager
	 * @author wangxiaolong
	 * @Description:更新在线时间
	 * @param
	 * @return
	 */
	int updateSystemManager(@Param("id")Integer id,@Param("lastLoginTime")long lastLoginTime,@Param("isOnline")Integer isOnline);

	/**
	 * 
	 * @Title: getUserMenuList
	 * @author wangxiaolong
	 * @Description:查询菜单
	 * @param
	 * @return
	 */
	List<MenuFunction> getUserMenuList(@Param("userId")int userId);
	
	/**
	 * 
	 * @Title: getSystemMenuList
	 * @author wangxiaolong
	 * @Description:查询菜单
	 * @param
	 * @return
	 */
	List<MenuFunction>getSystemMenuList();

	/**
	 * 
	 * @Title: getUserInfoByLoginName
	 * @author wangxiaolong
	 * @Description:根据用户名查询用户（管理员）
	 * @param
	 * @return
	 */
	UserInfo getUserInfoByLoginName(String loginName);

	/**
	 * 
	 * @Title: updateOnlineByUserId
	 * @author wangxiaolong
	 * @Description:更新用户登录状态
	 * @param
	 * @return
	 */
	int updateOnlineByUserId(@Param("id")Integer id,@Param("lastLoginTime")long lastLoginTime,@Param("isOnline")Integer isOnline);

}
