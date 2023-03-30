package com.zhu.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zhu.pojo.Brand;
import com.zhu.pojo.PageBean;
import com.zhu.service.BrandService;
import com.zhu.service.impl.BrandServiceImpl;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
	
	 private BrandService brandService = new BrandServiceImpl();
	 
	 public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        //调用service查询
	        List<Brand> brands = brandService.selectAll();

	        //转为JSON
	        String jsonString = JSON.toJSONString(brands);
	        //写数据
	        response.setContentType("text/json;charset=utf-8");
	        response.getWriter().write(jsonString);
	    }
	 public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		    request.setCharacterEncoding("UTF-8");
	        //接收品牌数据
	        BufferedReader br = request.getReader();
	        String params = br.readLine();//json字符串

	        //转为Brand对象
	        Brand brand = JSON.parseObject(params, Brand.class);

	        //调用service添加
	        brandService.add(brand);

	        //响应成功的标识
	        response.getWriter().write("success");
	    }
	 public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
	        //接收品牌数据
	        BufferedReader br = request.getReader();
	        String params = br.readLine();//json字符串

	        //转为Brand对象
	        Brand brand = JSON.parseObject(params, Brand.class);

	        //调用service更新
	        brandService.update(brand);
	        response.setContentType("text/json;charset=utf-8");

	        //响应成功的标识
	        response.getWriter().write("success");
	    }
	 
	 public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        //接收品牌数据
	        BufferedReader br = request.getReader();
	        String params = br.readLine();//json字符串

	        //转为int数组
	        int[] ids = JSON.parseObject(params, int[].class);

	        //调用service添加
	        brandService.deleteByIds(ids);

	        //响应成功的标识
	        response.getWriter().write("success");
	    }
	 
	 public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
	        //接收品牌数据
	        BufferedReader br = request.getReader();
	        String params = br.readLine();//json字符串

	        //转为Brand对象
	        Brand brand = JSON.parseObject(params, Brand.class);

	        //调用service
	        brandService.deleteById(brand);
	       

	        //响应成功的标识
	        response.getWriter().write("success");
	    }
	 
	 
	 public void selectByPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	request.setCharacterEncoding("UTF-8");
		 	String _curPage= request.getParameter("curPage");
	        String _pageSize= request.getParameter("pageSize");
	        int curPage=Integer.parseInt(_curPage);
	        int pageSize=Integer.parseInt(_pageSize);
	        PageBean<Brand> pageBean =brandService.selectByPages(curPage, pageSize);
	        

	        //转为JSON
	        String jsonString = JSON.toJSONString(pageBean);
	        //写数据
	        response.setContentType("text/json;charset=utf-8");
	        response.getWriter().write(jsonString);
	    }
	 public void selectByPagesAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	request.setCharacterEncoding("UTF-8");
		 	String _curPage= request.getParameter("curPage");
	        String _pageSize= request.getParameter("pageSize");
	        int curPage=Integer.parseInt(_curPage);
	        int pageSize=Integer.parseInt(_pageSize);
	        
	        //获取查询条件的对象
	        BufferedReader br = request.getReader();
	        String params = br.readLine();//json字符串
	        //转为Brand
	        Brand brand = JSON.parseObject(params,Brand.class);
	        
	        
	        PageBean<Brand> pageBean =brandService.selectByPagesAndCondition(curPage, pageSize,brand);
	        

	        //转为JSON
	        String jsonString = JSON.toJSONString(pageBean);
	        //写数据
	        response.setContentType("text/json;charset=utf-8");
	        response.getWriter().write(jsonString);
	    }


}
