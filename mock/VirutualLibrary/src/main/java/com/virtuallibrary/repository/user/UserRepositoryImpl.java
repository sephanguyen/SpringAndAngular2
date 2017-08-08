package com.virtuallibrary.repository.user;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.repository.BaseRepository;
import com.virtuallibrary.repository.user.interf.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepository<User, Long> implements UserRepositoryCustom {

}
