/*
 * �������� 2005-3-10
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.speedy.base.lock;

/**
 * @author Administrator
 * 
 *         ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class Lock {
	private int lockCount = 0;
	private int slockCount = 0;
	private int waitCount = 0;
	private Object shareLock = new Object();

	public void increaseWaitCount() {
		waitCount++;
	}

	public void decreaseWaitCount() {
		waitCount--;
	}

	public int getWaitCount() {
		return waitCount;
	}

	public void increase() {
		lockCount++;
	}

	public void decrease() {
		if (lockCount > 0)
			lockCount--;
	}

	public void increaseShare() {
		slockCount++;
	}

	public void decreaseShare() {
		if (slockCount > 0)
			slockCount--;
	}

	public int getLockCount() {
		return lockCount;
	}

	public int getShareLockCount() {
		return slockCount;
	}
}
