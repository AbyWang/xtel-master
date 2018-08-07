package com.cdxt.dl.web.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SystemDao {



	List<Map<String, Object>>   listSystemVersion(@Param("id")String id);


	
}
