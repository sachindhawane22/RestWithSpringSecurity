package com.apps.restws.mobileappws.controller;

import com.apps.restws.mobileappws.dto.UserDto;
import com.apps.restws.mobileappws.model.request.UsersDetailsRequestModel;
import com.apps.restws.mobileappws.model.response.UserRest;
import com.apps.restws.mobileappws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id){
        UserRest retValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto,retValue);

        log.info("get user method called");
        return retValue;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UsersDetailsRequestModel usersDetails){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(usersDetails,userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,returnValue);

        return  returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }
}
