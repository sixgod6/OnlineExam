package com.speedy.exam.exception;

import com.speedy.base.common.JetException;

/**
 *�����������ɹ������׳����쳣��
 */
public class KeyException extends JetException 
{
	public KeyException() 
	{
		super();
	}
	public KeyException(String message) 
	{
		super(message);
	}
	public KeyException(Throwable e) 
	{
		super(e);
	}	
	public KeyException(String message, Throwable e) 
	{
		super(message, e);
	}	
}
