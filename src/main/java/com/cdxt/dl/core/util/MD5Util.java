package com.cdxt.dl.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/* 
 * MD5 加密算法 
*/  
/**
 * @author HanChengBing
 *
 */
public class MD5Util {  
      
    // 全局数组  
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
  
    // 返回形式为数字跟字符串  
    private static String byteToArrayString(byte bByte) {  
        int iRet = bByte;  
        if (iRet < 0) {  
            iRet += 256;  
        }  
        int iD1 = iRet / 16;  
        int iD2 = iRet % 16;  
        return strDigits[iD1] + strDigits[iD2];  
    }  
  
    // 转换字节数组为16进制字串  
    private static String byteToString(byte[] bByte) {  
        StringBuffer sBuffer = new StringBuffer();  
        for (int i = 0; i < bByte.length; i++) {  
            sBuffer.append(byteToArrayString(bByte[i]));  
        }  
        return sBuffer.toString();  
    }  
  
    /**
     * MD5解密
     * @param strObj
     * @return
     */
    public static String enCode(String strObj) {  
        String resultString = null;  
        try {  
            resultString = new String(strObj);  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // md.digest() 该函数返回值为存放哈希值结果的byte数组  
            resultString = byteToString(md.digest(strObj.getBytes()));  
        } catch (NoSuchAlgorithmException ex) {  
            ex.printStackTrace();  
        }  
        return resultString;
    }  

    /**
     * main测试方法
     * @param args
     */
    public static void main(String[] args) {  
        System.out.println(MD5Util.enCode("123456"));  
    }  
}