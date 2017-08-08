package com.virtuallibrary.controller.user;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuallibrary.model.user.User;
import com.virtuallibrary.service.user.interf.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService mockUserService;

    List<User> listUser = Arrays.asList(new User(1L, "User 1","AB","12","user1","1",
                    "User",new Date(0),"TPHCM","09xx","xx@gmail.com",
                    "039xx",true),
            new User(2L, "User 2","CD","43","user2","2",
                    "User",new Date(0),"HN","09xx","xx@gmail.com",
                    "039xx",true));

    User user = new User(3L, "User 1","AB","12","user1","1", "User",new Date(0),"TPHCM","09xx","xx@gmail.com",
            "039xx",true);

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    @Test
    public void getAllUser() throws Exception {

        given(this.mockUserService.findAll()).willReturn(listUser);

        MvcResult result = this.mvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();

        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
        List<User> usersRespone = Arrays.asList(mapFromJson(content, User[].class));
        Assert.assertEquals("failure - size user ", 2, usersRespone.size());
        Assert.assertEquals("failure - RoleID", listUser.get(0).getRoleID(), usersRespone.get(0).getRoleID());
        Assert.assertEquals("failure - Lib ID", listUser.get(0).getLibID(), usersRespone.get(0).getLibID());
        Assert.assertEquals("failure - Username", listUser.get(0).getUserName(), usersRespone.get(0).getUserName());
        Assert.assertEquals("failure - Password", listUser.get(0).getPassword(), usersRespone.get(0).getPassword());
        Assert.assertEquals("failure - Last name", listUser.get(0).getLastName(), usersRespone.get(0).getLastName());
        Assert.assertEquals("failure - First name", listUser.get(0).getFirstName(), usersRespone.get(0).getFirstName());
        Assert.assertEquals("failure - Birthday", listUser.get(0).getBirthDay(), usersRespone.get(0).getBirthDay());
        Assert.assertEquals("failure - Address", listUser.get(0).getAddress(), usersRespone.get(0).getAddress());
        Assert.assertEquals("failure - Phone", listUser.get(0).getPhone(), usersRespone.get(0).getPhone());
        Assert.assertEquals("failure - Email", listUser.get(0).getEmail(), usersRespone.get(0).getEmail());
        Assert.assertEquals("failure - Card ID", listUser.get(0).getCardID(), usersRespone.get(0).getCardID());
        Assert.assertEquals("failure - Active", listUser.get(0).isActive(), usersRespone.get(0).isActive());

        Assert.assertEquals("failure - RoleID", listUser.get(1).getRoleID(), usersRespone.get(1).getRoleID());
        Assert.assertEquals("failure - Lib ID", listUser.get(1).getLibID(), usersRespone.get(1).getLibID());
        Assert.assertEquals("failure - Username", listUser.get(1).getUserName(), usersRespone.get(1).getUserName());
        Assert.assertEquals("failure - Password", listUser.get(1).getPassword(), usersRespone.get(1).getPassword());
        Assert.assertEquals("failure - Last name", listUser.get(1).getLastName(), usersRespone.get(1).getLastName());
        Assert.assertEquals("failure - First name", listUser.get(1).getFirstName(), usersRespone.get(1).getFirstName());
        Assert.assertEquals("failure - Birthday", listUser.get(1).getBirthDay(), usersRespone.get(1).getBirthDay());
        Assert.assertEquals("failure - Address", listUser.get(1).getAddress(), usersRespone.get(1).getAddress());
        Assert.assertEquals("failure - Phone", listUser.get(1).getPhone(), usersRespone.get(1).getPhone());
        Assert.assertEquals("failure - Email", listUser.get(1).getEmail(), usersRespone.get(1).getEmail());
        Assert.assertEquals("failure - Card ID", listUser.get(1).getCardID(), usersRespone.get(1).getCardID());
        Assert.assertEquals("failure - Active", listUser.get(1).isActive(), usersRespone.get(1).isActive());
    }

    @Test
    public void getFindOneUser() throws Exception {

        Long id = new Long(1);

        when(mockUserService.findOne(id)).thenReturn(user);

        String uri = "/api/users/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
        User userRespone = mapFromJson(content, User.class);

        Assert.assertEquals("failure - RoleID", user.getRoleID(), userRespone.getRoleID());
        Assert.assertEquals("failure - Lib ID", user.getLibID(), userRespone.getLibID());
        Assert.assertEquals("failure - Username", user.getUserName(), userRespone.getUserName());
        Assert.assertEquals("failure - Password", user.getPassword(), userRespone.getPassword());
        Assert.assertEquals("failure - Last name", user.getLastName(), userRespone.getLastName());
        Assert.assertEquals("failure - First name", user.getFirstName(), userRespone.getFirstName());
        Assert.assertEquals("failure - Birthday", user.getBirthDay(), userRespone.getBirthDay());
        Assert.assertEquals("failure - Address", user.getAddress(), userRespone.getAddress());
        Assert.assertEquals("failure - Phone", user.getPhone(), userRespone.getPhone());
        Assert.assertEquals("failure - Email", user.getEmail(), userRespone.getEmail());
        Assert.assertEquals("failure - Card ID", user.getCardID(), userRespone.getCardID());
        Assert.assertEquals("failure - Active", user.isActive(), userRespone.isActive());
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        Long id = Long.MAX_VALUE;

        when(mockUserService.findOne(id)).thenReturn(null);

        String uri = "/api/users/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() == 0);
    }

    @Test
    public void testCreateUser() throws Exception {

        when(mockUserService.save(any(User.class))).thenReturn(user);

        String uri = "/api/users";
        String inputJson = mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
        User userRespone = mapFromJson(content, User.class);
        Assert.assertNotNull("failure - expected user not null", userRespone);

        Assert.assertEquals("failure - RoleID", user.getRoleID(), userRespone.getRoleID());
        Assert.assertEquals("failure - Lib ID", user.getLibID(), userRespone.getLibID());
        Assert.assertEquals("failure - Username", user.getUserName(), userRespone.getUserName());
        Assert.assertEquals("failure - Password", user.getPassword(), userRespone.getPassword());
        Assert.assertEquals("failure - Last name", user.getLastName(), userRespone.getLastName());
        Assert.assertEquals("failure - First name", user.getFirstName(), userRespone.getFirstName());
        Assert.assertEquals("failure - Birthday", user.getBirthDay(), userRespone.getBirthDay());
        Assert.assertEquals("failure - Address", user.getAddress(), userRespone.getAddress());
        Assert.assertEquals("failure - Phone", user.getPhone(), userRespone.getPhone());
        Assert.assertEquals("failure - Email", user.getEmail(), userRespone.getEmail());
        Assert.assertEquals("failure - Card ID", user.getCardID(), userRespone.getCardID());
        Assert.assertEquals("failure - Active", user.isActive(), userRespone.isActive());
    }

    @Test
    public void testUpdateUser() throws Exception {

        when(mockUserService.update(any(User.class))).thenReturn(user);
        Long id = new Long(1);
        String uri = "/api/users/{id}";
        String inputJson = mapToJson(user);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri, id).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        verify(mockUserService, times(1)).update(any(User.class));

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
        User userRespone = mapFromJson(content, User.class);

        Assert.assertNotNull("failure - expected user not null", userRespone);

        Assert.assertEquals("failure - RoleID", user.getRoleID(), userRespone.getRoleID());
        Assert.assertEquals("failure - Lib ID", user.getLibID(), userRespone.getLibID());
        Assert.assertEquals("failure - Username", user.getUserName(), userRespone.getUserName());
        Assert.assertEquals("failure - Password", user.getPassword(), userRespone.getPassword());
        Assert.assertEquals("failure - Last name", user.getLastName(), userRespone.getLastName());
        Assert.assertEquals("failure - First name", user.getFirstName(), userRespone.getFirstName());
        Assert.assertEquals("failure - Birthday", user.getBirthDay(), userRespone.getBirthDay());
        Assert.assertEquals("failure - Address", user.getAddress(), userRespone.getAddress());
        Assert.assertEquals("failure - Phone", user.getPhone(), userRespone.getPhone());
        Assert.assertEquals("failure - Email", user.getEmail(), userRespone.getEmail());
        Assert.assertEquals("failure - Card ID", user.getCardID(), userRespone.getCardID());
        Assert.assertEquals("failure - Active", user.isActive(), userRespone.isActive());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = new Long(1);
        String uri = "/api/users/{id}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)).andReturn();
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(mockUserService, times(1)).delete(id);
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to be empty",
                content.trim().length() == 0);
    }
}
