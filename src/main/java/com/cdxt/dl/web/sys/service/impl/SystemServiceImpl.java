package com.cdxt.dl.web.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdxt.dl.web.sys.dao.SystemDao;
import com.cdxt.dl.web.sys.service.SystemService;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class SystemServiceImpl implements SystemService {

	@Resource
	private SystemDao systemDao;

	
	/**
	 * 
	 * @Title: getSystemVersion
	 * @Description:获取版本列表
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> listSystemVersion(String id,Integer pageNo,Integer pageSize){

		//分页
		PageHelper.startPage(pageNo, pageSize);

		return systemDao.listSystemVersion(id);
	}



}
