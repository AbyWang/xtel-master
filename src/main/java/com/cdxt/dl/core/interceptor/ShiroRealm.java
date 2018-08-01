package com.cdxt.dl.core.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.cdxt.dl.core.constant.SysConstants;
import com.cdxt.dl.core.util.MD5Util;
import com.cdxt.dl.web.sys.pojo.MenuFunction;
import com.cdxt.dl.web.sys.pojo.SystemManager;
import com.cdxt.dl.web.sys.pojo.UserInfo;
import com.cdxt.dl.web.sys.service.UserInfoService;

@SuppressWarnings("javadoc")
public class ShiroRealm extends AuthorizingRealm {

	@Resource
	private UserInfoService userService;

	/**
	 * 
	 * @Title: doGetAuthorizationInfo
	 * @Description:授权验证
	 * @param
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String username = (String) pc.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

	
		
		
		SystemManager systemManager = userService.getSystemManagerByLoginName(username);
		// 角色名的集合
		Set<String> roles = new HashSet<String>();
		// 权限名的集合
		Set<String> permissions = new HashSet<String>();
		if(systemManager!=null){
			List<MenuFunction>menuList=userService.getUserMenuList();
			authorizationInfo.addRole("super"); 
			for (MenuFunction right : menuList) {
				permissions.add(String.valueOf(right.getId()));
			}
			authorizationInfo.addStringPermissions(permissions);
			return authorizationInfo;
		}

		
  
//
//		List<Role> roleList = userService.getUserRoleById(user.getId());
//	//	List<Right> rightList = userService.getUserRightList(user.getId());
//
//		// 角色名的集合
//		Set<String> roles = new HashSet<String>();
//		// 权限名的集合
//		Set<String> permissions = new HashSet<String>();
//		for (Role role : roleList) {
//			roles.add(role.getPermissionCode());
//		}
//		for (Right right : rightList) {
//			permissions.add(right.getPermissonCode());
//		}

		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);

		return authorizationInfo;
	}

	/**
	 * @Title: doGetAuthenticationInfo @Description:
	 *         登录 @最后修改人：hezheng @最后修改时间：2017-4-24 下午3:01:46 @param
	 *         at @return @throws AuthenticationException 对方法的参数进行描述 @throws
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) at;
		// 通过表单接收的用户名
		String username = token.getUsername();
		String password = String.valueOf(token.getPassword());
		String host = token.getHost();

		// 判断用户是否为空
		if (StringUtils.isEmpty(username)) {
			return null;
		}

		UserInfo user = new UserInfo();
		// 判断是否走统一登录认证
		if (host == null || host.equals("")) {
			try {
				//user = userService.getUserByUname(username);
			} catch (Exception e) {
				e.printStackTrace();
				throw new AuthenticationException("系统错误，登录失败！");
			}
			if (user == null) {
				throw new AuthenticationException("用户名不存在！");
			}
			if (!user.getPassword().equals(MD5Util.enCode(password))) {
				throw new AuthenticationException("密码错误！");
			}
			// user.setRoles(roleService.getRolesByAccount(account));
			// user.setOrgInfo(orgInfoService.queryUserOrgByAccount(account));
		} else {
			System.out.println(host);
		//	user.setId(host);
		}
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute(SysConstants.SHIRO_USER, user);
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	/**
	 * 权限被修改时，手动修改缓存信息 @Title: removeUserAuthorizationInfoCache @Description:
	 * @最后修改人：hezheng @最后修改时间：2017-5-2 下午3:15:44 @param
	 * username 对方法的参数进行描述 @return void 返回类型 @throws
	 */
	public void removeUserAuthorizationInfoCache(String username) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(username, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

	//	/** 
	//	 * 将一些数据放到ShiroSession中,以便于其它地方使用 
	//	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
	//	 */  
	//	private void setSession(Object key, Object value){  
	//		Subject currentUser = SecurityUtils.getSubject();  
	//		if(null != currentUser){  
	//			Session session = currentUser.getSession();  
	//			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
	//			if(null != session){  
	//				session.setAttribute(key, value);  
	//			}  
	//		}  
	//	}

}
