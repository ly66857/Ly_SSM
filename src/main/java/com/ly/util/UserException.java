package com.ly.util;

/*用户自定义异常类(继承已有的异常的类)
 * 
 *1. 需要一个无参构造和一个有参构造（接受传递错误信息） 
 */
public class UserException extends Exception {

	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
//		把错误信息传递给父类
	}
	

}
