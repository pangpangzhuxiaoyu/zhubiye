package com.zhu.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zhu.mapper.BrandMapper;
import com.zhu.pojo.Brand;
import com.zhu.pojo.PageBean;
import com.zhu.service.BrandService;
import com.zhu.util.SqlSessionFactoryUtils;

public class BrandServiceImpl implements BrandService{
	
	//创建工厂对象
	SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();

	@Override
	public List<Brand> selectAll() {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		
		//调用
		List<Brand> brands = brandMapper.selectAll();
		sqlsession.close();
		return brands;
	}

	@Override
	public void add(Brand brand) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		//调用
		brandMapper.add(brand);
		sqlsession.commit();
		sqlsession.close();
		
	}

	@Override
	public void deleteByIds(int[] ids) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		//调用
		brandMapper.deleteByIds(ids);
		sqlsession.commit();
		sqlsession.close();
		
		
	}

	@Override
	public void update(Brand brand) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		//调用
		brandMapper.update(brand);
		sqlsession.commit();
		sqlsession.close();
		
	}

	@Override
	public void deleteById(Brand brand) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		//调用
		brandMapper.deleteById(brand);
		sqlsession.commit();
		sqlsession.close();
		
	}

	@Override
	public PageBean<Brand> selectByPages(int curPage, int pageSize) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
		
		//计算开始索引
		int begin =(curPage-1)*pageSize;
		//计算查询条数
		int size =pageSize;
		
		List<Brand> rows=brandMapper.selectByPages(begin,size);
		int count =brandMapper.selectTotalCount();
		
		PageBean<Brand> pageBean=new PageBean<>();
		pageBean.setRowsBrands(rows);
		pageBean.setTotalCount(count);
		sqlsession.close();
		return pageBean;
		
	
	}

	@Override
	public PageBean<Brand> selectByPagesAndCondition(int curPage, int pageSize, Brand brand) {
		// 获取对象
		SqlSession sqlsession =factory.openSession();
		//获取BrandMapper对象
		BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
				
		//计算开始索引
		int begin =(curPage-1)*pageSize;
		//计算查询条数
		int size =pageSize;
		
		String brandNameString = brand.getBrandName();
		if(brandNameString!=null && brandNameString.length()>0) {
			brand.setBrandName("%"+brandNameString+"%");
		}
		String customNameString = brand.getCustomName();
		if(customNameString!=null && customNameString.length()>0) {
			brand.setCustomName("%"+customNameString+"%");
		}
				
		List<Brand> rows=brandMapper.selectByPagesAndCondition(begin,size,brand);
		//获取总符合条件数量
		int count =brandMapper.selectTotalCountByCondition(brand);
				
		PageBean<Brand> pageBean=new PageBean<>();
		pageBean.setRowsBrands(rows);
		pageBean.setTotalCount(count);
		sqlsession.close();
		return pageBean;
		
		
		
		
	}

}
