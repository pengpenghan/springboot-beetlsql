package cn.hpp.springboot.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.hpp.springboot.model.User;
import cn.hpp.springboot.service.IUserService;

@RestController
public class HomeController {
	
	@Autowired
	IUserService userService;
	
	@Value("${server.port}")
	String port ;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@GetMapping(value={"/","/index"})
	public Object index(){
		User user = userService.getUserById(1);
		return "userName:"+user.getUserName()+"<br>password:"+user.getPassword() +"port:"+port;
	}
	
	@PostMapping(value="/addUser")
	public Object addUser(@RequestBody User user){
		User user2 = userService.addUser(user);
		return user2.getUserName();
	}
	
	@PostMapping(value = "/listUsers")
	public Object listUsers(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<User> list = userService.listUsers(map);
		return list;
	}
	
	@PostMapping(value = "/listUsersPage")
	public Object listUsersPage(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<User> list = userService.listUsersPage(map);
		return list;
	}
	
	@PostMapping("/get/{id}")
	public User test(@PathVariable Integer id){
		RestTemplate client = restTemplateBuilder.build();
		String url = "http://localhost:8080/user/{id}";
		User user = client.getForObject(url, User.class,id);
		return user;
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Integer id){
		User user = userService.getUserById(id);
		return user;
	}
	
	@GetMapping("/addUserClient")
	@ResponseBody
	public String addUserClient(){
		RestTemplate client = restTemplateBuilder.build();
		String uri = "http://localhost:8080/addUser";
		User user = new User();
		user.setUserName("hpp");
		user.setPassword("123456");
		String ret = client.postForObject(uri, user, String.class);
		return ret;
	}
}
