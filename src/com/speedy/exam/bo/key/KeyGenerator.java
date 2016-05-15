package com.speedy.exam.bo.key;

import java.rmi.RemoteException;

import com.speedy.exam.exception.KeyException;

/**
 *������������EJB��Զ�̽ӿڣ�ͨ����ȡΨһ��ź�Ψһ����Ĺ��ܡ�
 */
public interface KeyGenerator
{
	public long nextKey(String tableName)throws KeyException, RemoteException;
	public long[] nextKey(String tableName, int count)throws KeyException, RemoteException;
	public String nextKey(String tableName, String prefix)throws KeyException, RemoteException;
	public String[] nextKey(String tableName, String prefix,int count)throws KeyException, RemoteException;
	public void clearKey()throws KeyException, RemoteException;
}
