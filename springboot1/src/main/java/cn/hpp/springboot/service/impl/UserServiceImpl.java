package cn.hpp.springboot.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.management.Query;
import javax.sound.sampled.Port;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hpp.springboot.model.User;
import cn.hpp.springboot.persistence.UserMapper;
import cn.hpp.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	SQLManager sqlManager;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User getUserById(Integer id) {
		User user = userMapper.selectUserById(id);
		return user;
	}

	@Override
	public User addUser(User user) {
		sqlManager.insert(user);
		return user;
	}

	@Override
	public List<User> listUsers(HashMap<String, Object> map) {
		List<User> list = sqlManager.select("user.listUsers", User.class,map);
		//return userMapper.listUsers(map);
		//return list;
		User user = new User();
		user.setUserName("hpp");
		return userMapper.template(user);
	}

	@Override
	public List<User> listUsersPage(HashMap<String, Object> map) {
		PageQuery<User> query = new PageQuery<User>();
		query.setPageNumber(1);
		query.setPageSize(2);
		sqlManager.pageQuery("user.listUsersPage", User.class, query);
		return query.getList();
	}
	
}
