package com.cdxt.dl.web.sys.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.model.PagePojo;
import com.cdxt.dl.core.model.ResJson;
import com.cdxt.dl.core.util.MD5;
import com.cdxt.dl.core.util.PropertyUtil;
import com.cdxt.dl.web.sys.pojo.MenuFunction;
import com.cdxt.dl.web.sys.pojo.SystemManager;
import com.cdxt.dl.web.sys.pojo.UserInfo;
import com.cdxt.dl.web.sys.service.UserInfoService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserInfoController {


	@Autowired
	private UserInfoService userInfoService;


	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public ResJson checkuser(HttpServletRequest req,@RequestParam("loginname")String loginName,
			@RequestParam("password")String password){
		HttpSession session=req.getSession();
		UserInfo userInfo=userInfoService.getUserInfoByLoginName(loginName);
		String pwMd5=MD5.toMD5(password); 

		if(userInfo!=null){
			if(!pwMd5.equals(userInfo.getPassword())){
				return new ResJson(SysConstants.STRING_ZERO,"密码错误");
			}
			if(userInfo.getStatus()==0){
				return new ResJson(SysConstants.STRING_ZERO,"用户已锁定,请联系管理员");
			}
			userInfoService.updateOnlineByUserId(userInfo.getUserId(), new Date().getTime(),SysConstants.INTEGER_ONE);
			session.setAttribute(SysConstants.SYS_ADMIN, userInfo);
			return new ResJson(SysConstants.STRING_ONE,"登录成功",userInfo);
		}
		//普通管理员没查到，查询是否为超级管理员
		//通过登录名名查询manager
		SystemManager manager=userInfoService.getSystemManagerByLoginName(loginName);
		if(manager==null || !pwMd5.equals(manager.getPassword())){
			return new ResJson(SysConstants.STRING_ZERO,"用户名或密码错误");
		}
		if(manager.getStatus()==0){
			return new ResJson(SysConstants.STRING_ZERO,"用户已锁定,请联系管理员");
		}
		if(manager.getIsOnline()!=1){
			//更新在线状态和登录时间
			userInfoService.updateSystemManager(manager.getId(),new Date().getTime(),SysConstants.INTEGER_ONE);
		}

		session.setAttribute(SysConstants.SUPPER_ADMIN, manager);
		return new ResJson(SysConstants.STRING_ONE,"登录成功",manager);
	}


	/**
	 * @throws Exception 
	 * @描述:注销用户信息修状态
	 * @方法名: logout
	 * @param session
	 * @return
	 * @返回类型 String
	 * @创建人 张兴成
	 * @创建时间 2018年4月20日下午1:37:38
	 * @修改人 张兴成
	 * @修改时间 2018年4月20日下午1:37:38
	 * @修改备注
	 * @since
	 * @throws
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		SystemManager sysM = (SystemManager) session.getAttribute(SysConstants.SUPPER_ADMIN);
		UserInfo userM = (UserInfo) session.getAttribute(SysConstants.SYS_ADMIN);
		if(sysM!=null||userM!=null){
			userInfoService.updateSystemManager(sysM.getId(), 0,SysConstants.INTEGER_ZERO);
			session.removeAttribute(SysConstants.SUPPER_ADMIN);
		}
		session.invalidate();
		return "redirect:/toLogin";
	}

	/*
	@RequestMapping("/updateDateBaseInfo")
	@ResponseBody
	public Map<String,Object> updateDateBaseInfo(HttpServletRequest  request,@RequestBody DateBaseInfoBean  dateBaseInfoBean){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			//String path = request.getServletContext().getRealPath("upload\\");
			map.put("jdbc.password", dateBaseInfoBean.dbpassword);
			map.put("jdbc.url", dateBaseInfoBean.dbadresss);
			map.put("jdbc.username", dateBaseInfoBean.dbAccount);
			PropertiesConfig.setString("F:/meetingSystem-01/resources/jdbc", map);
			result.put("successFlag", true);
			result.put("message", "修改成功");
			return result;

		} catch (Exception e) {

			result.put("successFlag", false);
			result.put("message", "操作失败");
		}
		return result;

	}	*/


	/**
	 * 
	 * @Title: getRootMenus
	 * @author wangxiaolong
	 * @Description:c查询所有的菜单
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getMenus")
	@ResponseBody
	public ResJson getRootMenus(HttpServletRequest request,HttpSession session,Integer parentId) {
		SystemManager systemManager = (SystemManager)session.getAttribute(SysConstants.SUPPER_ADMIN);
		UserInfo userInfo=(UserInfo)session.getAttribute(SysConstants.SYS_ADMIN);
		Integer userId=null;
		List<MenuFunction>menuList=new ArrayList<MenuFunction>();
		if(systemManager == null&&userInfo==null){
			return new ResJson(SysConstants.STRING_ZERO,"请先进行登录操作！");
		}
		menuList=userInfoService.getUserMenuList();
		return new ResJson(SysConstants.STRING_ONE,"登录成功！",menuList);


	}

	/**
	 * 
	 * @Title: getUserWithPage
	 * @author wangxiaolong
	 * @Description:获取用户信息
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getUserWithPage")
	@ResponseBody
	public PagePojo getUserWithPage(Model model,@Param(value="nameVlaue")String nameVlaue,@Param(value="idVlaue")Integer idVlaue,@Param(value="limit")Integer limit,
			@Param(value="pageNo")Integer pageNo){

		List<UserInfo>userInfos=userInfoService.getUserWithPage(nameVlaue,idVlaue,limit,pageNo);

		//将数据放入pageInfo，pageInfo会对数据进行处理，这个是封装好的类，直接调用即可
		PageInfo<UserInfo> pageInfo=new PageInfo<UserInfo>(userInfos);
		//封装bootstrap
		PagePojo page=new PagePojo();
		page.setPage(pageInfo.getPageNum());
		//age.getPage()(pageInfo.getPages());
		page.setTotal(pageInfo.getTotal());
		page.setRows(userInfos);
		return page;
	}

	/**
	 * 
	 * @Title: getRoleWithPage
	 * @author wangxiaolong
	 * @Description:获取角色信息
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/getRoleWithPage")
	@ResponseBody
	public PagePojo getRoleWithPage(@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,@RequestParam(value="limit",defaultValue="10")Integer limit){

		List<Map<String, Object>> map=userInfoService.getRoleWithPage(limit,pageNo);
		//将数据放入pageInfo，pageInfo会对数据进行处理，这个是封装好的类，直接调用即可
		PageInfo<Map<String, Object>> pageInfo=new PageInfo<Map<String, Object>>(map);
		//封装bootstrap
		PagePojo page=new PagePojo();
		page.setPage(pageInfo.getPageNum());
		page.setTotal(pageInfo.getTotal());
		page.setRows(map);
		return page;

	}


}
