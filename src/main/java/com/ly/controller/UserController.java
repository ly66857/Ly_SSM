package com.ly.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiFileChooserUI;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ly.model.User;
import com.ly.util.UserException;

@Controller
@RequestMapping("/user")
public class UserController {
private List<User> users=new ArrayList<User>();

public UserController() {
	super();
	// TODO Auto-generated constructor stub
	users.add(new User("a","a","a","a"));
	users.add(new User("b","b","b","b"));
	users.add(new User("c","c","c","c"));
	users.add(new User("d","d","d","d"));
}

@RequestMapping(value="/list")
//value可省略
public String lists(Model model){
	model.addAttribute("users", users);
	System.out.println(users);
	return "UserJsp/list";
	
}

@RequestMapping("/add")
//默认get方式访问 用来打开页面
public String add(Model model){
	System.out.println("执行了默认add");
	model.addAttribute("use",new User());
//	相当于request传值，单次传值
	return "UserJsp/add";
}

/*
 * 
 * 
@RequestMapping(value="/add",method=RequestMethod.POST)
//post方式访问 用来传递数据
public String add(@Validated User user, Model model,BindingResult br){
	System.out.println("执行了Post的add");
	if(br.hasErrors()){
		System.out.println("有错误");
		return "UserJsp/add";
	}
	users.add(user);
	return "redirect:/user/list";
//	return "UserJsp/list";
//	单纯访问list.jsp 不通过后台不执行方法不传值 request单次传值 
//	页面第二次请求之前设置的值就消失了 要重定向再设置一遍
	
}
*
*
*/

@RequestMapping(value="/add",method=RequestMethod.POST)
//post方式访问 用来传递数据
public String add(@Validated User use,BindingResult br,
		@RequestParam("files") MultipartFile[] files,HttpServletRequest request){
//	1.传文件前要在XX-servlet.xml配置 2.在jsp中要将键值对儿传递方式变成二进制流式传递
//	@RequestParam("files") MultipartFile[] files传递多个文件 用数组接收
	
	System.out.println("执行了Post的add");
	if(br.hasErrors()){
		System.out.println("有错误");
		return "UserJsp/add";
	}
	users.add(use);
	
//文件处理：
	for(MultipartFile mf:files){
		if(mf.isEmpty()){
			continue;
			//为空跳过			
		}
//	1 设置存储路径
		String UploadPath=request.getSession().getServletContext().getRealPath("/resources/upload");
		
//		System.out.println(UploadPath);
//		D:\DataWork\TOMCAT\apache-tomcat-8.5.54\webapps\Ly_SpringMVC_02_User\resources/upload

		System.out.println(mf.getOriginalFilename());
//	2	得到文件名加后缀
		
//	3  创建文件对象
		File destfile=new File(UploadPath+"/"+mf.getOriginalFilename());
		
//  4  文件转流并上传
		try {
			FileUtils.copyInputStreamToFile(mf.getInputStream(), destfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	return "redirect:/user/list";
//	return "UserJsp/list";
//	单纯访问list.jsp 不通过后台不执行方法不传值 request单次传值 
//	页面第二次请求之前设置的值就消失了 要重定向再设置一遍
	
}



@RequestMapping(value="login",method=RequestMethod.POST)
public String login(String username,String password,HttpSession session) throws UserException{
	System.out.println("进入login");
	//	判断登陆的用户名和密码是否在users中
	for(User u:users){
		if(username.equals(u.getUsername())){
//			用户名对了判断密码
			if(password.equals(u.getPassword())){
//				登陆成功
				session.setAttribute("loginUser", u);
				return "redirect:/user/list";
				
			}else {
				throw new UserException("密码不正确");
//				密码不正确
			}
		}	

//		循环结束 用户名不存在
	}

	throw new UserException("用户名不存在");
//	抛异常不用返回值！？
	
}
	
/*局部异常 只能当前类使用
 * 
 *全局异常 所有类都能使用
 *
 * 局部异常优先级高
 */

/*
 * 
 * 
@ExceptionHandler(value={UserException.class})
//局部异常
public String error(UserException ue,HttpServletRequest request){
	request.setAttribute("error", ue);
	return "UserJsp/exception";
	
}
*
*
*/




}
