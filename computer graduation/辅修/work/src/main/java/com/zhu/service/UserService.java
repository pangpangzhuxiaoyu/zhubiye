package com.zhu.service;

import com.zhu.pojo.User;

public interface UserService {
	public User login(String username,String password);
	
	public boolean register(User user);

}
