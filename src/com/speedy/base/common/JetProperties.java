package com.speedy.base.common;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 *��ȡ�û������ϵͳ�����ļ����ṩ
 *��ȡϵͳ���õ�ͳһ����
*/
public class JetProperties extends PropertyPlaceholderConfigurer {

	public static Properties properties = new Properties();

	public void init() throws IOException {
		properties = mergeProperties();
		PropertyConfigurator.configure(properties);//log4j
		for (Entry<Object, Object> m : properties.entrySet()){
			JetLog.info(this, "��ʼ������"+m.getKey() +" : "+ m.getValue());
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static void setProperty(String key,String value) {
		properties.setProperty(key, value);
	}
	
	public String getProperty(String key, String defaultValue)
	{
		String ret = properties.getProperty(key, defaultValue);
		if(ret != null)
			return ret.trim();
		return null;
	}
}

