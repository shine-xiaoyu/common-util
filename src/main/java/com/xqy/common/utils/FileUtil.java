package com.xqy.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * 
 * @Description:封装文件的常用方法
 * @date: 2019年12月5日 下午1:27:47
 */
public class FileUtil {
	/**
	 * 根据文件，截取扩展名
	 * 
	 * @param fileName "aa.png"
	 * @return
	 */
	public static String getExtName(String fileName) {
		// 处理空异常
		if (fileName == null || "".equals(fileName)) {
			throw new RuntimeException("文件名不能为空");
		}
		if (fileName.indexOf(".") <= -1) {
			throw new RuntimeException(fileName + ":该文件名没有包含扩展名");
		}
		String extName = fileName.substring(fileName.lastIndexOf("."));
		return extName;
	}

	/**
	 * 获取系统当前用户目录
	 * 
	 * @return
	 */
	public static String getSystemUserHome() {
		return System.getProperty("user.home");
	}

	/**
	 * @Title: getSystemTempDirectory @Description:
	 * 操作系统临时目录 @param: @return @return: String @throws
	 */
	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * @Title: readTextFileByLine @Description: 读取文件内容 @param: @param
	 * pathname @param: @return @return: String @throws
	 */
	public static String readTextFileByLine(String pathname) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(new File(pathname)));
			do {
				sb.append(br.readLine());
				sb.append("\r\n");
			} while (br.read() != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			StreamUtil.closeAll(br);
		}
		return sb.toString();
	}

	/**
	 * @Title: readTextFileOfList @Description: 按行读取文件内容到list集合 @param: @param
	 * pathname @param: @return @return: List<String> @throws
	 */
	public static List<String> readTextFileOfList(String pathname) {
		BufferedReader br = null;
		List<String> strList = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(new File(pathname)));
			do {
				strList.add(br.readLine());
			} while (br.read() != -1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			StreamUtil.closeAll(br);
		}
		return strList;
	}

	/**
	 * @Title: deleteFile @Description: 递归删除文件 @param: @param file @return:
	 * void @throws
	 */
	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File theFile : listFiles) {
				deleteFile(theFile);
			}
			file.delete();
		} else {
			file.delete();
		}
	}

	/**
	 * @Title: deleteFile @Description: 递归删除文件 @param: @param filePath @return:
	 * void @throws
	 */
	public static void deleteFile(String filePath) {
		deleteFile(new File(filePath));
	}

	/**
	 * @Title: getFileSize @Description: 获得文件大小 返回文件以指定单位大小表示 File
	 * a.txt=2k @param: @param file @param: @return @return: String @throws
	 */
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length / 1024.0;
//		return Math.round((length/1024.0))+"kb";
		return String.format("%.2f", len) + "kb";
	}

	public static String getFileSize(String fileFullName) {
		return getFileSize(new File(fileFullName));
	}

	public static void main(String[] args) {
		System.out.println(getSystemTempDirectory());
	}

	// 读取文件对象到list集合中
	public static List<String> readFile2List(File file) throws FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(file);
		return readFile2List(fileInputStream);
	}

	// 读取文件地址，并根据编码，把内容放入list集合中
	public static List<String> readFile2List(String filename, String charset) throws FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(filename);
		return readFile2List(fileInputStream, charset);
	}

	// 读取InputStream对象，把其内容放入集合中
	public static List<String> readFile2List(InputStream in) {
		return readFile2List(in, "utf-8");
	}

	// 读取InputStream对象，并根据编码 把其内容放入集合中
	public static List<String> readFile2List(InputStream inputStream, String charset) {
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

	/**
	 * @Title: writeFile
	 * @Description: 按照指定的编码把内容写入指定的文件中
	 * @param path
	 * @param content
	 * @param charset
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 判断父目录是否存在
		if (!file.getParentFile().exists()) {
			// 创建父目录
			file.getParentFile().mkdirs();
		}
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		if (content != null) {
			bw.write(content);
		}
		bw.flush();
		bw.close();
	}

	/**
	 * @Title: readFile
	 * @Description: 读取文件内容
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 * @return: String
	 */
	public static String readFile(File file, String charset) throws IOException {
		// 创建输出流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		// 定义缓冲对象
		StringBuffer sb = new StringBuffer();
		// 定义读取每行的结果
		String content = null;
		// 循环读取
		while ((content = br.readLine()) != null) {
			// 加入缓冲对象
			sb.append(content);
		}
		// 关闭流
		br.close();
		// 返回结果
		return sb.toString();

	}

}
