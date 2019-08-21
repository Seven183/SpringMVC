package com.example.demo.Controller.MongoDBController;

import java.util.List;

import com.example.demo.POJO.MongodbPojo.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MongodbService.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.web.bind.annotation.ResponseBody;

/**** imports ****/
@Controller("userController1")
@RequestMapping("/user")
public class UserController  {
	
	// 后面会给出其操作的方法
	@Autowired
	private UserService1 userService = null;

    // 注入接口
    @Autowired
    private UserRepository userRepository = null;

	// 跳转到测试页面
	@RequestMapping("/page")
	public String page() {
		return "User";
	}
	
	/**
	 * 保存（新增或者更新）用户
	 * @param user -- 用户
	 * @return 用户信息
	 */
	@RequestMapping("/save")
	@ResponseBody
	public User saveUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}
	
//	/***
//	 * 获取用户
//	 * @param id -- 用户主键
//	 * @return 用户信息
//	 */
	@PostMapping("/get")
	@ResponseBody
	public User getUser(@RequestBody User user1) {
		User user = userService.getUser(user1.getId());
		return user;
	}

//	/**
//	 * 查询用户
//	 * @param userName --用户名称
//	 * @param note -- 备注
//	 * @param skip -- 跳过用户个数
//	 * @param limit -- 限制返回用户个数
//	 * @return
//	 */
	@PostMapping("/find")
	@ResponseBody
	public List<User> addUser(@RequestBody User user) {
		List<User> userList = userService.findUser(user);
		return userList;
	}
	
//	/**
//	 * 更新用户部分属性
//	 * @param id —— 用户编号
//	 * @param userName —— 用户名称
//	 * @param note —— 备注
//	 * @return 更新结果
//	 */
	@PostMapping("/update")
	@ResponseBody
	public UpdateResult updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	/**
	 * 删除用户
	 * @param id -- 用户主键
	 * @return 删除结果
	 */
	@PostMapping("/delete")
	@ResponseBody
	public DeleteResult deleteUser(Long id) {
		return userService.deleteUser(id);
	}
	

    // 执行查询
    @PostMapping("/byName")
	@ResponseBody
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserNameLike(userName);
    }
    
    // 执行查询
    @PostMapping("/findOr")
	@ResponseBody
    public User findUserByIdOrUserName(Long id, String userName) {
    	return userRepository.findUserByIdOrUserName(id, userName);
    }
}