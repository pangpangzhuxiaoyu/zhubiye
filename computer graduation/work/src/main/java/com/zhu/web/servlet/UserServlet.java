package com.zhu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhu.pojo.User;
import com.zhu.service.UserService;
import com.zhu.service.impl.UserServiceImpl;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
	
	private UserService userService=new UserServiceImpl();
	
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user =userService.login(username, password);
		
		if(user!=null) {
			//登录成功，重定向
			String contextPathString=request.getContextPath();
			response.sendRedirect(contextPathString+"/brand.html");
		}else {
			//存储错误信息到req
			request.setAttribute("login_msg","用户名或密码错误！");
			//跳转到login.jsp
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
			
		}
			
		
		
	}	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username==null||password==null||password.equals("")||username.equals("")) {
			request.setAttribute("register_msg", "用户名或密码不能为空");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			
		}
		
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//判断是否可以注册
		boolean b=userService.register(user);
		if(b) {
			
			request.setAttribute("register_msg", "注册成功，请登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}else {
			request.setAttribute("register_msg", "用户名已存在");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}	
		
			
		
		
	}
		
}
