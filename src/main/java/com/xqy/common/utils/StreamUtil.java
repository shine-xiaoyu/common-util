package com.xqy.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * 流工具类
 * @Description:TODO(描述这个类的作用)   
 * @date:   2019年12月5日 下午1:31:39
 */
public class StreamUtil {
	/**
	 * 关闭流的方法
	 * @Title: closeAll   
	 * @Description: 数组参数，可以批量删除多个打开的流   
	 * @param: @param autoCloseables      
	 * @return: void      
	 * @throws
	 */
	public static void closeAll(AutoCloseable... autoCloseables ) {
		if(autoCloseables!=null) {
			for(AutoCloseable autoCloseable:autoCloseables) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Title: readTextFile   
	 * @Description: 以流的方式，读取文本文件内容   
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] b = new byte[1024];
			String str = null;
			while (inputStream.read(b)!=-1) {
				str += new String(b);
			}
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			closeAll(inputStream);
		}
	}
	/**
	 * @Title: getFileContent   
	 * @Description: 根据文件全名读取文件内容   
	 * @param: @param fileFullName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(String fileFullName) {
		return readTextFile(new File(fileFullName));
	}
	
	public static void writeTextFile(String content,File file,boolean append) {
		BufferedWriter writer = null;
		try {
			//判断写文件的文件夹是否存在
			String parent = file.getParent();
			File parentFile = new File(parent);
			if(!parentFile.exists()) {
				parentFile.mkdirs();
			}
			//写文件
			writer = new BufferedWriter(new FileWriter(file,append));
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeAll(writer);
		}
	}
	
	public static void writeTextFile(String content,String fileFullName,boolean append) {
		writeTextFile(content,new File(fileFullName), append);
	}

	public static void main(String[] args) {
		String readTextFile = readTextFile("C:\\Users\\Administrator\\Desktop\\pom.xml");
		writeTextFile(readTextFile, "C:\\Users\\Administrator\\Desktop\\aa\\aa.xml",false);
	}
	
	
	//读取文件对象到list集合中
		public static List<String> readFile2List(File file) throws FileNotFoundException{
			 FileInputStream fileInputStream = new FileInputStream(file);
			return readFile2List(fileInputStream);
		}
		//读取文件地址，并根据编码，把内容放入list集合中
		public static List<String> readFile2List(String filename,String charset) throws FileNotFoundException{
			FileInputStream fileInputStream = new FileInputStream(filename);
			return readFile2List(fileInputStream,charset);
		}
		//读取InputStream对象，把其内容放入集合中
		public static List<String> readFile2List(InputStream in){
			return readFile2List(in,"utf-8");
		}
		//读取InputStream对象，并根据编码 把其内容放入集合中
		public static List<String> readFile2List(InputStream inputStream, String charset)
			     {
			    List<String> list = new ArrayList<String>();
			 
			    BufferedReader br = null;
			    try {
			      br = new BufferedReader(new InputStreamReader(inputStream, charset));
			 
			      String s = null;
			      while ((s = br.readLine()) != null) {
			        list.add(s);
			      }
			    } catch (Exception e) {
					e.printStackTrace();
				}
			 
			    return list;
			  }
}
