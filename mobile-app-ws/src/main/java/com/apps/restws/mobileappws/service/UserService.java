package com.apps.restws.mobileappws.service;

import com.apps.restws.mobileappws.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDto createUser(UserDto userDto);

    public UserDto getUser(String email);

    public UserDto getUserByUserId(String userId);
}
