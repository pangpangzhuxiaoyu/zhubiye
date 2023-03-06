package com.zhu.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.zhu.mapper.UserMapper;
import com.zhu.pojo.User;
import com.zhu.service.UserService;
import com.zhu.util.SqlSessionFactoryUtils;

public class UserServiceImpl implements UserService{

	//创建工厂对象
	SqlSessionFactory factory=SqlSessionFactoryUtils.getSqlSessionFactory();
	
	@Override
	public User login(String username, String password) {
		// 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username, password);

        //释放资源
        sqlSession.close();

        return user;
	}

	@Override
	public boolean register(User user) {
		// 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        
        //判断用户是否存在
        User userTemp=mapper.selectByUsername(user.getUsername());
        
        if(userTemp==null) {
        	mapper.add(user);
        	sqlSession.commit();
        	
        }
        return userTemp==null;
		
	}

}
