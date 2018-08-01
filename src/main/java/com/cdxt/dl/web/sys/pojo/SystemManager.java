package com.cdxt.dl.web.sys.pojo;

import org.apache.ibatis.type.Alias;

@Alias("systemManager")
public class SystemManager {
	 private  int id;
	 private  String name;
	 private  String password;
	 //是否在线 0 - 离线 1 - 在线
	 private  int isOnline;
	 //最后一次登录时间
	 private  long lastLoginTime;
	 //管理员状态0 – 正常1 – 锁定 
	 private  int  status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SystemManager [id=" + id + ", name=" + name + ", password=" + password + ", isOnline=" + isOnline
				+ ", lastLoginTime=" + lastLoginTime + ", status=" + status + "]";
	}
	 
	 
	 

}
