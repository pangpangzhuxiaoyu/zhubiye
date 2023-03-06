package com.zhu.service;

import java.util.List;

import com.zhu.pojo.Brand;
import com.zhu.pojo.PageBean;


public interface BrandService {
	
	 
	/**
	 * 查询所有
	 * @return
	 */
	List<Brand> selectAll();
	
	
	/**
	 *添加数据
	 * @param brand
	 */
	void add(Brand brand);
	
	
	/**
	 *更新数据
	 * @param brand
	 */
	void update(Brand brand);
	
	/**
	 *批量删除
	 * @param brand
	 */
	void deleteById(Brand brand);
	
	
	/**
	 *批量删除
	 * @param ids
	 */
	void deleteByIds(int[] ids);
	
	
	
	/**
	 * @param curPage 当前页码
	 * @param pageSize 每页展示条数
	 * @return
	 */
	PageBean<Brand> selectByPages(int curPage,int pageSize); 
	
	
	/**
	 * 分页条件查询
	 * @param curPage
	 * @param pageSize
	 * @param brand
	 * @return
	 */
	PageBean<Brand> selectByPagesAndCondition(int curPage,int pageSize,Brand brand);

}
