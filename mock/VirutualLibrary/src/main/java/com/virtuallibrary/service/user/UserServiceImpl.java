package com.virtuallibrary.service.user;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.repository.user.interf.UserRepository;
import com.virtuallibrary.service.BaseService;
import com.virtuallibrary.service.user.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User, Long> implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	};

	@Override
	public List<User> findAll(List<Long> listId) {
		return (List<User>) userRepository.findAll(listId);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public boolean exists(Long id) {
		return userRepository.exists(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> save(List<User> listUser) {
		return ( List<User>) userRepository.save(listUser);
	}

	@Override
	public void delete(Long id) {
		this.userRepository.delete(id);
	}

	@Override
	public void delete(User entity) {
		this.userRepository.delete(entity);
	}

	@Override
	public void delete(List<User> listEntity) {
		userRepository.delete(listEntity);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public List<User> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public List<User> findByUserNameContaining(String username) {
		return userRepository.findByUserNameContaining(username);
	}

	public User update(User user) {
		User userUpdate = userRepository.findOne(user.getId());
		if(userUpdate == null) {
			throw new NoResultException("user not found.");
		}
		userUpdate.setRoleID(user.getRoleID());
		userUpdate.setLibID(user.getLibID());
		userUpdate.setUserName(user.getUserName());
		userUpdate.setPassword(user.getPassword());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setBirthDay(user.getBirthDay());
		userUpdate.setAddress(user.getAddress());
		userUpdate.setPhone(user.getPhone());
		userUpdate.setEmail(user.getEmail());
		userUpdate.setCardID(user.getCardID());
		userUpdate.setActive(user.isActive());
		userRepository.save(userUpdate);
		return userUpdate;
	}
}
