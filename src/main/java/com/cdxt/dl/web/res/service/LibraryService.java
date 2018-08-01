package com.cdxt.dl.web.res.service;

import java.util.List;
import java.util.Map;

public interface LibraryService {

	List<Map<String, Object>>getlibraryPage(Map<String, Object> newmap, Integer startRow, Integer pageSize);

}
