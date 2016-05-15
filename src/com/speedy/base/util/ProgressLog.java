/*
 * �������� 2006-6-2
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.speedy.base.util;

/**
 * @author xzm
 * 
 *         ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ProgressLog {
	PrintThread print = null;
	Thread thread = null;

	public void start() {
		if (print == null)
			print = new PrintThread();
		print.run = true;
		thread = new Thread(print);
		thread.start();
	}

	public void start(String info, long sp) {
		if (print == null)
			print = new PrintThread(info, sp);
		print.run = true;
		thread = new Thread(print);
		thread.start();
	}

	public void stop() {
		if (print == null)
			return;
		print.run = false;
		print = null;
	}
}
class PrintThread implements Runnable {
	String info = ".";
	long time = 50;
	boolean run = true;

	public PrintThread() {
	}

	public PrintThread(String info) {
		this.info = info;
	}

	public PrintThread(String info, long time) {
		this.info = info;
		this.time = time;
	}

	public void run() {
		try {
			while (run) {
				System.out.print(info);
				Thread.sleep(time);
			}
		} catch (InterruptedException interruptedexception) {
			return;
		}
	}
}