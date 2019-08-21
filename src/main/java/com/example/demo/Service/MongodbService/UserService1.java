package com.example.demo.Service.MongodbService;



import com.example.demo.POJO.MongodbPojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.util.List;


public interface UserService1 {
	public void saveUser(User user);

	public DeleteResult deleteUser(Long id);

	public List<User> findUser(User user);

	public UpdateResult updateUser( User user);

	public User getUser(Long id);
}
