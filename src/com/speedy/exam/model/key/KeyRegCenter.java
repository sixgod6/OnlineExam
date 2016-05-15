/*
 * �������� 2004-10-10
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.speedy.exam.model.key;

import java.util.*;
/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class KeyRegCenter 
{
	private static KeyRegCenter keyRegCenter = null;
	private Hashtable keys = new Hashtable();
	
	public static synchronized KeyRegCenter getInstance()
	{
		if(keyRegCenter == null)
			keyRegCenter = new KeyRegCenter();
		return keyRegCenter;
	}
	public static void release()
	{
		keyRegCenter = null;
	}
	public void putKey(String tabName, KeyModel key)
	{
		keys.put(tabName, key);
	}
	public synchronized KeyModel getKey(String tabName)
	{
		KeyModel keyModel = (KeyModel)keys.get(tabName);
		if(keyModel == null)
		{
			keyModel = new KeyModel();
			keys.put(tabName, keyModel);
		}
		return keyModel;
	}
}
