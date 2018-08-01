package com.cdxt.dl.core.util;

import java.security.MessageDigest;
import java.util.Date;
/** 
 * 说明：MD5处理
 * 密码加密
 * @version
 */
public class MD5 {

	public static String toMD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
		
		Date date=new Date();
		long time = date.getTime();
		System.out.println(time);
		System.out.println(toMD5("123456"));
	}
}
