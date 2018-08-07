/**
 * 
 * @ClassName: PropertyUtil.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年6月19日
 */
package com.cdxt.dl.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @ClassName: PropertyUtil.java
 * @Description: 
 * @author wangxiaolong
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2018年6月19日
 */
public class PropertyUtil {

	public static   Map<String,String> getPropertiesValues(String fileName)  {

		Properties properties;

		Map<String,String> propMap = new HashMap<String,String>();
		try {
			properties = PropertyUtil.getProperties(fileName);
			Enumeration<Object> enums =  properties.keys();
			while(enums.hasMoreElements()){
				String key = (String)enums.nextElement();
				String value = properties.getProperty(key);
				propMap.put(key, value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propMap;
	}

	public static Properties getProperties(String fileName) throws IOException {
		Map<Integer, Properties> propertiesMap = new HashMap<Integer, Properties>();
		Properties property = propertiesMap.get(fileName.hashCode());
		if(property==null){
			Properties properties = new Properties();
			properties.load(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName));
			System.out.println(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName));
			propertiesMap.put(fileName.hashCode(), properties);
			property = properties;
		}
		return property;
	}

	//修改方法
	public static void writeProperties(String fileName,Map<String, String>keyValueMap) throws IOException{
		String filePath = PropertyUtil.class.getClassLoader()
				.getResource(fileName).getFile();// 文件的路径
		System.out.println("propertiesPath:" + filePath);
		Properties props = new Properties();
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			// 从输入流中读取属性列表（键和元素对）
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			props.load(br);
			br.close();

			// 写入属性文件
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
			props.clear();// 清空旧的文件
			for (String key : keyValueMap.keySet())
				props.setProperty(key, keyValueMap.get(key));
			props.store(bw, "");
			bw.close();
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + ""+ " value error");
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


}
