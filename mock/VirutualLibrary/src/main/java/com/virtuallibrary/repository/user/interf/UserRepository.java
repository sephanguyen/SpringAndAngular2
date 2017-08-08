package com.virtuallibrary.repository.user.interf;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.repository.user.interf.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    List<User> findByUserName(String username);
    List<User> findByUserNameContaining(String username);
}
