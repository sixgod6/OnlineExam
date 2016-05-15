/*
 * �������� 2004-10-10
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.speedy.exam.model.key;

import com.speedy.exam.exception.KeyException;



public class KeyModel  
{
	private String date = null;
	private long maxKey = 0;
	private int KeyCount = 0;
	
	public synchronized void addKey(String curDate, long maxKey, int count)
	{
		if(curDate.equals(date))
		{
			this.maxKey = maxKey;
			KeyCount += count;
		}
		else 
		{
			this.maxKey = maxKey;
			KeyCount = count;
			date = curDate;			
		}
	}
	public synchronized long getKey(int count) throws KeyException
	{
		if(KeyCount < count)
			throw new KeyException("��ǰ������������");
		else
		{
			KeyCount -= count; 
			return maxKey-KeyCount;
		}
	}
	public int getCount()
	{
		return KeyCount;
	}
	public synchronized String getCurDate()
	{
		if(date==null)
			return null;
		return new String(date); 
	}
	public synchronized void clearKey()
	{
		date = null;
		maxKey = 0;
		KeyCount = 0;
	}
}
