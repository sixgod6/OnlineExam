/*
 * �������� 2009-2-17
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.speedy.base.util;

import com.speedy.base.common.JetException;

/**
 * @author Administrator
 * 
 *         ��������������ע�͵�ģ��Ϊ ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class SendSMS {
	final static String URL = "http://211.147.222.37/sendsms/?action=sendmsg&pwd=b80e81&username=zfeng";

	public static String sendSMS(String mobile, String msg) throws JetException {
		try {
			StringBuffer sTotalString = new StringBuffer("");
			if (msg == null)
				msg = "";
			StringBuffer urlb = new StringBuffer(URL);
			urlb.append("&tmobile=" + mobile + "&msg=" + msg);
			String url = urlb.toString();
			String sCurrentLine = null;
			java.net.URL l_url = new java.net.URL(url);
			java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();
			l_connection.connect();
			java.io.InputStream l_urlStream = l_connection.getInputStream();
			java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null)
				sTotalString = sTotalString.append(new StringBuffer(sCurrentLine));
			sCurrentLine = null;
			l_reader.close();
			l_urlStream.close();
			l_connection.disconnect();
			l_connection = null;
			return sTotalString.toString();
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}
}
