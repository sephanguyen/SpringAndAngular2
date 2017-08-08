package com.virtuallibrary.service.user;

import com.virtuallibrary.model.user.User;
import com.virtuallibrary.repository.user.interf.UserRepository;
import com.virtuallibrary.service.user.interf.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

/**
 * Created by nhoxbypass@gmail.com on 3/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @MockBean
    UserRepository mockUserRepository;

    @Autowired
    UserService userService;

    @Test
    public void findOneUserWithParamsTest() throws SQLException {
        // prepare mock repository
        given(this.mockUserRepository.findOne(1L)).willReturn(new User(1L, "User 1", "AB", "12", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx", "nhoxbypass@gmail.com",
                "039xx", true));

        // call User Service to find One
        User user = this.userService.findOne(1L);

        // assert Result
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("User 1", user.getRoleID());
        assertEquals("AB", user.getLibID());
        assertEquals("12", user.getUserName());
        assertEquals("user1", user.getPassword());
        assertEquals("Genius", user.getLastName());
        assertEquals("Doan", user.getFirstName());
        assertEquals(new Date(0), user.getBirthDay());
        assertEquals("Tp.HCM", user.getAddress());
        assertEquals("09xx", user.getPhone());
        assertEquals("nhoxbypass@gmail.com", user.getEmail());
    }

    @Test
    public void findOneUserDefaultTest() throws SQLException {
        // prepare mock repository
        given(this.mockUserRepository.findOne(1L)).willReturn(new User());

        // call User Service find One
        User user = this.userService.findOne(1L);

        // assert Result
        assertThat(user.getId()).isNull();
        assertThat(user.getRoleID()).isNull();
        assertThat(user.getLibID()).isNull();
        assertThat(user.getUserName()).isNull();
        assertThat(user.getPassword()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getFirstName()).isNull();
        assertThat(user.getBirthDay()).isNull();
        assertThat(user.getAddress()).isNull();
        assertThat(user.getPhone()).isNull();
        assertThat(user.getEmail()).isNull();
    }

    @Test
    public void findOneUserNotExistTest() throws SQLException {
        // prepare mock repository
        given(this.mockUserRepository.findOne(1L)).willReturn(null);

        // call User Service find One
        User user = this.userService.findOne(1L);

        // assert Result
        assertThat(user).isNull();
    }

    @Test(expected = TransactionSystemException.class)
    public void findOneUserExceptionTest() throws SQLException {
        // prepare mock repository
        given(this.mockUserRepository.findOne(1L)).willThrow(
                new TransactionSystemException("Transaction Error"));

        // call User Service find One
        User user = this.userService.findOne(1L);

        // assert Result
        assertThat(user).isNull();
    }

    @Test
    public void findAllUserTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true));

        given(this.mockUserRepository.findAll()).willReturn(userList);
        List<User> actualUserList = userService.findAll();
        assertNotNull("failure - expected not null", actualUserList);
        assertEquals("failure - expected list size", 2, actualUserList.size());

        for (int i = 0; i < actualUserList.size(); i++) {
            assertEquals("failure - expected get id", userList.get(i).getId(), actualUserList.get(i).getId());
            assertEquals("failure - expected get role Id", userList.get(i).getRoleID(), actualUserList.get(i).getRoleID());
            assertEquals("failure - expected get libId", userList.get(i).getLibID(), actualUserList.get(i).getLibID());
            assertEquals("failure - expected get userName", userList.get(i).getUserName(), actualUserList.get(i).getUserName());
            assertEquals("failure - expected get password", userList.get(i).getPassword(), actualUserList.get(i).getPassword());
            assertEquals("failure - expected get lastname", userList.get(i).getLastName(), actualUserList.get(i).getLastName());
            assertEquals("failure - expected get birth day", userList.get(i).getBirthDay(), actualUserList.get(i).getBirthDay());
            assertEquals("failure - expected get address", userList.get(i).getAddress(), actualUserList.get(i).getAddress());
            assertEquals("failure - expected get phone", userList.get(i).getPhone(), actualUserList.get(i).getPhone());
            assertEquals("failure - expected get email", userList.get(i).getEmail(), actualUserList.get(i).getEmail());
            assertEquals("failure - expected get cardid", userList.get(i).getCardID(), actualUserList.get(i).getCardID());
            assertEquals("failure - expected get isActive", userList.get(i).isActive(), actualUserList.get(i).isActive());
        }
    }

    @Test
    public void findAllUserByIDTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(4L, "User 4", "CD", "43", "user4", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(5L, "User 5", "CD", "43", "user5", "2",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true));
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(4L);
        idList.add(5L);

        given(this.mockUserRepository.findAll(idList)).willReturn(userList);
        List<User> actualUserList = userService.findAll(idList);
        assertNotNull("failure - expected not null", actualUserList);
        assertEquals("failure - expected list size", 4, actualUserList.size());

        for (int i = 0; i < actualUserList.size(); i++) {
            assertEquals("failure - expected get id", userList.get(i).getId(), actualUserList.get(i).getId());
            assertEquals("failure - expected get role Id", userList.get(i).getRoleID(), actualUserList.get(i).getRoleID());
            assertEquals("failure - expected get libId", userList.get(i).getLibID(), actualUserList.get(i).getLibID());
            assertEquals("failure - expected get userName", userList.get(i).getUserName(), actualUserList.get(i).getUserName());
            assertEquals("failure - expected get password", userList.get(i).getPassword(), actualUserList.get(i).getPassword());
            assertEquals("failure - expected get lastname", userList.get(i).getLastName(), actualUserList.get(i).getLastName());
            assertEquals("failure - expected get bday", userList.get(i).getBirthDay(), actualUserList.get(i).getBirthDay());
            assertEquals("failure - expected get address", userList.get(i).getAddress(), actualUserList.get(i).getAddress());
            assertEquals("failure - expected get phone", userList.get(i).getPhone(), actualUserList.get(i).getPhone());
            assertEquals("failure - expected get email", userList.get(i).getEmail(), actualUserList.get(i).getEmail());
            assertEquals("failure - expected get cardid", userList.get(i).getCardID(), actualUserList.get(i).getCardID());
            assertEquals("failure - expected get isActive", userList.get(i).isActive(), actualUserList.get(i).isActive());
        }
    }

    @Test
    public void countUserTest() {
        long count = 9;
        given(mockUserRepository.count()).willReturn(count);
        long actualCount = userService.count();
        assertNotNull("failure - expected not null", actualCount);
        assertEquals("failure - expected count", count, actualCount);
    }

    @Test
    public void checkUserExistTest() {
        boolean isExist = true;
        given(mockUserRepository.exists(9L)).willReturn(isExist);
        boolean isActualExist = userService.exists(9L);
        assertNotNull("failure - expected exists null", isActualExist);
        assertEquals("failure - expected exists", isExist, isActualExist);
    }

    @Test
    public void checkUserNotExistTest() {
        //Not use mockito so that the actual userService will not have this user
        boolean isExist = userService.exists(9L);
        assertNotNull("failure - expected exists null", isExist);
        assertEquals("failure - expected count", false, isExist);
    }

    @Test
    public void saveUserTest() {
        User user = new User(1L, "User 1", "AB", "12", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx", "nhoxbypass@gmail.com",
                "039xx", true);

        given(mockUserRepository.save(user)).willReturn(user);

        User savedUser = userService.save(user);
        assertNotNull("failure - expected user null", savedUser);
        // assert Result
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("User 1", user.getRoleID());
        assertEquals("AB", user.getLibID());
        assertEquals("12", user.getUserName());
        assertEquals("user1", user.getPassword());
        assertEquals("Genius", user.getLastName());
        assertEquals("Doan", user.getFirstName());
        assertEquals(new Date(0), user.getBirthDay());
        assertEquals("Tp.HCM", user.getAddress());
        assertEquals("09xx", user.getPhone());
        assertEquals("nhoxbypass@gmail.com", user.getEmail());
    }

    @Test
    public void saveUserListTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true));


        given(mockUserRepository.save(userList)).willReturn(userList);

        List<User> savedUserList = userService.save(userList);
        assertNotNull("failure - expected not null", savedUserList);
        assertEquals("failure - expected list size", 2, savedUserList.size());

        for (int i = 0; i < savedUserList.size(); i++) {
            assertEquals("failure - expected get id", userList.get(i).getId(), savedUserList.get(i).getId());
            assertEquals("failure - expected get role Id", userList.get(i).getRoleID(), savedUserList.get(i).getRoleID());
            assertEquals("failure - expected get libId", userList.get(i).getLibID(), savedUserList.get(i).getLibID());
            assertEquals("failure - expected get userName", userList.get(i).getUserName(), savedUserList.get(i).getUserName());
            assertEquals("failure - expected get password", userList.get(i).getPassword(), savedUserList.get(i).getPassword());
            assertEquals("failure - expected get lastname", userList.get(i).getLastName(), savedUserList.get(i).getLastName());
            assertEquals("failure - expected get b day", userList.get(i).getBirthDay(), savedUserList.get(i).getBirthDay());
            assertEquals("failure - expected get address", userList.get(i).getAddress(), savedUserList.get(i).getAddress());
            assertEquals("failure - expected get phone", userList.get(i).getPhone(), savedUserList.get(i).getPhone());
            assertEquals("failure - expected get email", userList.get(i).getEmail(), savedUserList.get(i).getEmail());
            assertEquals("failure - expected get cardid", userList.get(i).getCardID(), savedUserList.get(i).getCardID());
            assertEquals("failure - expected get isActive", userList.get(i).isActive(), savedUserList.get(i).isActive());
        }
    }

    @Test
    public void deleteUserByIDTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true));

        given(mockUserRepository.save(userList)).willReturn(userList);
        List<User> savedUserList = userService.save(userList);

        assertNotNull("failure - expected not null", savedUserList);
        assertEquals("failure - expected list size", 2, savedUserList.size());

        userService.delete(1L);

        assertNotNull("failure - expected not null", userService.findAll());
    }

    @Test
    public void deleteUserByObjectTest() {
        User toDeleteUser = new User(2L, "User 2", "CD", "43", "user2", "2",
                "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                "039xx", true);
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                toDeleteUser);


        given(mockUserRepository.save(userList)).willReturn(userList);
        List<User> savedUserList = userService.save(userList);

        assertNotNull("failure - expected not null", savedUserList);
        assertEquals("failure - expected list size", 2, savedUserList.size());

        userService.delete(toDeleteUser);

        assertNotNull("failure - expected not null", userService.findAll());
    }

    @Test
    public void deleteUserByUserListTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(4L, "User 4", "CD", "43", "user4", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(5L, "User 5", "CD", "43", "user5", "2",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true));
        List<User> toDeleteUserList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(4L, "User 4", "CD", "43", "user4", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true));

        given(mockUserRepository.save(userList)).willReturn(userList);
        List<User> savedUserList = userService.save(userList);

        assertNotNull("failure - expected not null", savedUserList);
        assertEquals("failure - expected list size", 4, savedUserList.size());

        userService.delete(toDeleteUserList);

        assertNotNull("failure - expected not null", userService.findAll());
    }

    @Test
    public void deleteAllUserTest() {
        List<User> userList = Arrays.asList(new User(1L, "User 1", "AB", "12", "user1", "1",
                        "User", new Date(0), "TPHCM", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(2L, "User 2", "CD", "43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true));

        given(mockUserRepository.save(userList)).willReturn(userList);
        List<User> savedUserList = userService.save(userList);

        assertNotNull("failure - expected not null", savedUserList);
        assertEquals("failure - expected list size", 2, savedUserList.size());

        userService.deleteAll();

        assertNotNull("failure - expected not null", userService.findAll());
        assertEquals("failure - expected list size", 0, userService.count());
    }


    @Test
    public void findUserByNameTest() {
        List<User> userList = Arrays.asList(new User(2L, "User 2", "CD", "us43", "user2", "2",
                        "User", new Date(0), "HN", "09xx", "xx@gmail.com",
                        "039xx", true),
                new User(1L, "User 1", "AB", "us43", "user1", "Genius",
                        "Doan", new Date(0), "Tp.HCM", "09xx", "nhoxbypass@gmail.com",
                        "039xx", true));

        given(mockUserRepository.findByUserName("us43")).willReturn(userList);

        List<User> actualUserList = userService.findByUserName("us43");
        Assert.assertNotNull("failure - expected user null", actualUserList);
        Assert.assertEquals("failure - expected user size", 2, actualUserList.size());
        for (int i = 0; i < actualUserList.size(); i++) {
            assertEquals("failure - expected get id", userList.get(i).getId(), actualUserList.get(i).getId());
            assertEquals("failure - expected get role Id", userList.get(i).getRoleID(), actualUserList.get(i).getRoleID());
            assertEquals("failure - expected get libId", userList.get(i).getLibID(), actualUserList.get(i).getLibID());
            assertEquals("failure - expected get userName", userList.get(i).getUserName(), actualUserList.get(i).getUserName());
            assertEquals("failure - expected get password", userList.get(i).getPassword(), actualUserList.get(i).getPassword());
            assertEquals("failure - expected get lastname", userList.get(i).getLastName(), actualUserList.get(i).getLastName());
            assertEquals("failure - expected get b.day", userList.get(i).getBirthDay(), actualUserList.get(i).getBirthDay());
            assertEquals("failure - expected get address", userList.get(i).getAddress(), actualUserList.get(i).getAddress());
            assertEquals("failure - expected get phone", userList.get(i).getPhone(), actualUserList.get(i).getPhone());
            assertEquals("failure - expected get email", userList.get(i).getEmail(), actualUserList.get(i).getEmail());
            assertEquals("failure - expected get cardid", userList.get(i).getCardID(), actualUserList.get(i).getCardID());
            assertEquals("failure - expected get isActive", userList.get(i).isActive(), actualUserList.get(i).isActive());
        }
    }

    @Test
    public void findUserByNameEmptyTest() {
        List<User> userList = userService.findByUserName("anyone");
        Assert.assertNotNull("failure - expected user null", userList);
        Assert.assertEquals("failure - expected user size", 0, userList.size());
    }

    @Test
    public void updateUserTest() {
        Long userId = 1L;
        User expectedUser = new User(1L, "User 1", "AB", "12", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx", "nhoxbypass@gmail.com",
                "039xx", true);

        given(mockUserRepository.findOne(userId)).willReturn(expectedUser);

        User userToUpdate = new User(1L, "User 3", "AB3", "123", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx3", "nhoxbypass@gmail.com",
                "039xx", true);

        User updatedUser = userService.update(userToUpdate);

        assertNotNull("failure - expected not null", updatedUser);
        // assert Result
        assertEquals(updatedUser.getId(), expectedUser.getId());
        assertEquals(updatedUser.getRoleID(), expectedUser.getRoleID());
        assertEquals(updatedUser.getLibID(), expectedUser.getLibID());
        assertEquals(updatedUser.getUserName(), expectedUser.getUserName());
        assertEquals(updatedUser.getPassword(), expectedUser.getPassword());
        assertEquals(updatedUser.getLastName(), expectedUser.getLastName());
        assertEquals(updatedUser.getFirstName(), expectedUser.getFirstName());
        assertEquals(updatedUser.getBirthDay(), expectedUser.getBirthDay());
        assertEquals(updatedUser.getAddress(), expectedUser.getAddress());
        assertEquals(updatedUser.getPhone(), expectedUser.getPhone());
        assertEquals(updatedUser.getEmail(), expectedUser.getEmail());
    }


    @Test(expected = NoResultException.class)
    public void updateUserExceptionTest() {
        Long userId = 1L;
        User expectedUser = new User(1L, "User 1", "AB", "12", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx", "nhoxbypass@gmail.com",
                "039xx", true);

        given(mockUserRepository.findOne(userId)).willReturn(expectedUser);

        User userToUpdate = new User(3L, "User 3", "AB3", "123", "user1", "Genius",
                "Doan", new Date(0), "Tp.HCM", "09xx3", "nhoxbypass@gmail.com",
                "039xx", true);

        when(userService.update(userToUpdate)).thenThrow(new NoResultException());
    }
}
