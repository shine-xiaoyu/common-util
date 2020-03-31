package com.xqy.common.utils;

import java.security.MessageDigest;
  
/**
 * @Description:采用MD5加密解密   
 * @date:   2019年12月13日 上午8:46:51
 */
public class Md5Util {
    /**
     * @Title: string2MD5   
     * @Description: MD5加码 生成32位md5码  
     * @param: @param inStr
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /**
     * @Title: convertMD5   
     * @Description: 加密解密算法 执行一次加密，两次解密  
     * @param: @param inStr
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String convertMD5(String inStr){  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ '9');  
        }  
        String s = new String(a);
        return s;
    }  
}

