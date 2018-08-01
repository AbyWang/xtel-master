package com.cdxt.dl.web.res.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdxt.dl.web.res.dao.LibraryDao;
import com.cdxt.dl.web.res.service.LibraryService;
import com.github.pagehelper.PageHelper;
@Service
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	private LibraryDao libraryDao;

	@Override
	public 	List<Map<String, Object>>  getlibraryPage(Map<String, Object> newmap, Integer pageNo, Integer pageSize){
		
		//分页
		PageHelper.startPage(pageNo, pageSize);
		return libraryDao.getlibraryPage(newmap, pageNo, pageSize);
	}
}
