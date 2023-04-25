package com.zhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhu.pojo.Brand;

public interface BrandMapper {
	
	@Select("select * from tb_brand" )
	@ResultMap(value="brandResultMap")
	List<Brand> selectAll();
	
	//添加数据
	@Insert("insert into tb_brand values(null,#{brandName},#{customName},#{ordered},#{tel},#{status})")
	void add(Brand brand);
	
	//修改
	@Update("update tb_brand set brand_name=#{brandName},custom_name=#{customName},ordered=#{ordered},tel=#{tel},status=#{status} where id=#{id}")
	void update(Brand brand);
	
	//批量删除
	void deleteByIds(@Param("ids") int[] ids);
	
	//根据id删除
	@Delete("delete from tb_brand where id=#{id}")
	void deleteById(Brand brand);
	
	
	//分页查询
	@Select("select * from tb_brand limit #{begin},#{size}")
	@ResultMap(value="brandResultMap")
	List<Brand> selectByPages(@Param("begin") int begin,@Param("size") int size);
	
	//查询总的记录数
	@Select("select count(*) from tb_brand")
	int selectTotalCount();
	
	
	//分页条件查询
	List<Brand> selectByPagesAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);
		
	//条件查询总的记录数
	int selectTotalCountByCondition(Brand brand);
	
	
	
	
	
	
	
	
	
	
	
	
}
