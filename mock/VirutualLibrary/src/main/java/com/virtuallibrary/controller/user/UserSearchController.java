package com.virtuallibrary.controller.user;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.service.user.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/app/users")
public class UserSearchController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findUserByUserName(@RequestParam String username) {
        return userService.findByUserNameContaining(username);
    }
}
