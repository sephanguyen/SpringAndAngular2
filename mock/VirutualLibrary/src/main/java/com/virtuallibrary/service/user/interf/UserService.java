package com.virtuallibrary.service.user.interf;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.service.IBaseService;

import java.util.List;

public interface UserService extends IBaseService<User, Long> {
	List<User> findByUserName(String username);
	List<User> findByUserNameContaining(String username);
	User update(User userUpdate);

}
