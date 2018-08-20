package com.cdxt.dl.web.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: SystemController.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年7月18日
 */
@Controller
public class CommonController {


	//@Autowired
	//private SystemService systemService;

	/**
	 * 
	 * @Title: toLogin
	 * @author wangxiaolong
	 * @Description:登录页面跳转
	 * @param
	 * @return
	 */
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(){

		return new ModelAndView("main/login");
	}
	
	
	/**
	 * 
	 * @Title: gotoRegisterReview
	 * @author wangxiaolong
	 * @Description:报名审核
	 * @param
	 * @return
	 */
	@RequestMapping("/gotoRegisterApply")
	public String gotoRegisterApply(){
		return "teach/registerList";
	}
	
	/**
	 * 
	 * @Title: systemLogin
	 * @author wangxiaolong
	 * @Description:登录页面跳转
	 * @param
	 * @return
	 */
	@RequestMapping("/login")
	public String systemLogin(HttpServletRequest request){

		return "main/main";
	}

	
	@RequestMapping(value ="/gotoGroupPage")
	public String gotoGroupPage(){
		return "group/group_list";
	}
	
	/**
	 * 
	 * @Title: gotoRole
	 * @author wangxiaolong
	 * @Description:课程安排
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/gotoDatabasePage")
	public String gotoCoursePage(){
		return "sys/database";
	}


	/**
	 * 
	 * @Title: gotoCoursePage
	 * @author wangxiaolong
	 * @Description:
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/gotoVersionRelease")
	public String gotoVersionRelease(){
		return "sys/versionList";
	}
	/**
	 * 
	 * @Title: gotoUserInfo
	 * @author wangxiaolong
	 * @Description:用户页面跳转
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/gotoUserInfo")
	public String gotoUserInfo(){
		return "sys/userList";
	}
	/**
	 * 
	 * @Title: gotoRole
	 * @author wangxiaolong
	 * @Description:用户页面跳转
	 * @param
	 * @return
	 */
	@RequestMapping(value ="/gotoRole")
	public String gotoRole(){
		return "sys/roleList";
	}


}
