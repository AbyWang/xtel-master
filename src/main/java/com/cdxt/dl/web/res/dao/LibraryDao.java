package com.cdxt.dl.web.res.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface LibraryDao {



	List<Map<String, Object>> getlibraryPage(@Param("newmap")Map<String, Object> newmap,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

	
}
