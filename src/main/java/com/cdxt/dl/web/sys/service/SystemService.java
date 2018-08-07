package com.cdxt.dl.web.sys.service;

import java.util.List;
import java.util.Map;



public interface SystemService {

	List<Map<String, Object>> listSystemVersion(String id,Integer pageNo,Integer pageSize);

}
