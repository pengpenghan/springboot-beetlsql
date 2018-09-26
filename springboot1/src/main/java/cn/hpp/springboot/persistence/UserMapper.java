package cn.hpp.springboot.persistence;

import java.util.HashMap;
import java.util.List;

import javax.management.Query;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import cn.hpp.springboot.model.User;

//slqrecource注解对应sql目录下的.md文件 （可选）
@SqlResource("user")
public interface UserMapper extends BaseMapper<User>{

	//必须用@Param指定参数否则会报beetl.sql.core.BeetlSQLException: 未命名的参数 0 
	User selectUserById(@Param("id")Integer id);
	
	List<User> listUsers(HashMap<String, Object> map);
	
}
