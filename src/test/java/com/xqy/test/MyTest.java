package com.xqy.test;

import org.junit.Test;

import com.xqy.common.utils.StringUtil;


public class MyTest {
	
	//判断字符串是否为空
	@Test
	public void isNulltest() {
		String str  = "asds";
		boolean b = StringUtil.isBlank(str);
		System.out.println(b);
	}
	
	//判断是否为邮箱
	@Test
	public void isEmail() {
		String str = "qwe@qq.com";
		boolean b = StringUtil.isEmail(str);
		System.out.println(b);
	}
	
	
	//判断是否为手机号
	@Test
	public void isPhone() {
		String str = "13345678901";
		boolean b = StringUtil.isPhoneNum(str);
		System.out.println(b);
	}
	
	
	
}
