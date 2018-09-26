package cn.hpp.springboot.service;

import java.util.HashMap;
import java.util.List;

import cn.hpp.springboot.model.User;

public interface IUserService {
	
	User getUserById(Integer id);
	
	User addUser(User user);
	
	List<User> listUsers(HashMap<String, Object> map);

	List<User> listUsersPage(HashMap<String, Object> map);
}
