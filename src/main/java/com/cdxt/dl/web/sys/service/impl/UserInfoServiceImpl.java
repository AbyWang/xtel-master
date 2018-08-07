package com.cdxt.dl.web.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdxt.dl.web.sys.dao.UserInfoDao;
import com.cdxt.dl.web.sys.pojo.MenuFunction;
import com.cdxt.dl.web.sys.pojo.SystemManager;
import com.cdxt.dl.web.sys.pojo.UserInfo;
import com.cdxt.dl.web.sys.service.UserInfoService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;
	/**
	 * @throws Exception 
	 * @描述:获取用户信息分页
	 * @方法名: getUserWithPage
	 * @param newmap
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @创建人：张兴成
	 * @创建时间：2018年4月20日下午2:41:34
	 * @修改人：张兴成
	 * @修改时间：2018年4月20日下午2:41:34
	 * @修改备注：
	 * @throws
	 */
	@Override
	public 	 List<UserInfo> getUserWithPage(String nameVlaue,Integer idVlaue,Integer limit,Integer pageNumber){

		//分页
		PageHelper.startPage(pageNumber, limit);
		//List<Map<String, Object>>  map=userInfoDao.getUserWithPage(nameVlaue,idVlaue);

		return userInfoDao.getUserWithPage(nameVlaue,idVlaue);
	}


	@Override
	public List<Map<String, Object>> getRoleWithPage(Integer limit,Integer pageNumber)  {

		//分页
		PageHelper.startPage(pageNumber, limit);
		return  userInfoDao.getRoleWithPage();

	}


	/**
	 * 
	 * @Title: getUserInfoByLoginName
	 * @Description:根据用户名查询用户（管理员）
	 * @param
	 * @return
	 */
	public UserInfo getUserInfoByLoginName(String loginName){

		return userInfoDao.getUserInfoByLoginName(loginName);
	}

	/**
	 * 
	 * @Title: getSystemManagerByLoginName
	 * @author wangxiaolong
	 * @Description:根据登录名查询超级管理员
	 * @param
	 * @return
	 */
	@Override
	public SystemManager getSystemManagerByLoginName(String loginName) {

		//通过登录名名查询manager
		return userInfoDao.getSystemManagerByLoginName(loginName);

	}
	/**
	 * @描述:更新在线状态和登录时间
	 * @方法名: updateSystemManager
	 * @param sysM
	 * @throws Exception
	 * @创建人：张兴成
	 * @创建时间：2018年4月19日下午4:44:53
	 * @修改人：张兴成
	 * @修改时间：2018年4月19日下午4:44:53
	 * @修改备注：
	 * @throws
	 */
	@Override
	public int  updateSystemManager(Integer id,long loginTime,Integer isOnline){

		return userInfoDao.updateSystemManager(id,loginTime,isOnline);

	}


	/**
	 * 
	 * @Title: getUserMenuList
	 * @Description:获取菜单
	 * @param
	 * @return
	 */
	public 	List<MenuFunction> getUserMenuList(){
		List<MenuFunction>resultList=new ArrayList<MenuFunction>();
		List<MenuFunction> menuList=null;
		//id不为空，查询普通管理员
		//超级管理员，查询xtel_systemmanager
		menuList=userInfoDao.getSystemMenuList();
		//		List<MenuFunction>childList=null;
		//查询所有的一级菜单
		for(MenuFunction menuFunction:menuList){
			if(menuFunction.getParent()==null){
				resultList.add(menuFunction);
			}

		}
		for (MenuFunction menu : resultList) {
			menu.setMenuFunctions(getChild(menu.getId(), menuList));
		}

		return resultList;
	}


	/**
	 * 
	 * @Title: getChild
	 * @author wangxiaolong
	 * @Description:递归查找子菜单
	 * @param id
	 *            当前菜单id
	 * @param menuList
	 *            要查找的列表
	 * @return
	 */
	private List<MenuFunction> getChild(Integer id, List<MenuFunction> menuList) {
		// 子菜单
		List<MenuFunction> childList = new ArrayList<>();
		for (MenuFunction menu : menuList) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (menu.getParent()!=null) {
				if (menu.getParent().equals(id)) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (MenuFunction menu : childList) {//type=0,还有子菜单
			if (menu.getType()==0) {
				// 递归
				menu.setMenuFunctions(getChild(menu.getId(), menuList));
			}
		} // 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}

	public int updateOnlineByUserId(Integer id,long loginTime,Integer isOnline){
		return userInfoDao.updateOnlineByUserId(id,loginTime,isOnline);
	}
}
