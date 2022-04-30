package com.apps.restws.mobileappws.service.impl;

import com.apps.restws.mobileappws.dto.UserDto;
import com.apps.restws.mobileappws.entity.UserEntity;
import com.apps.restws.mobileappws.repository.UserRepository;
import com.apps.restws.mobileappws.service.UserService;
import com.apps.restws.mobileappws.utils.CommonUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonUtils utils;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if(userRepository.findUserByEmail(userDto.getEmail()) !=null ){
            throw new RuntimeException("Duplicate email id");
        }
        UserEntity userEntity  = new UserEntity();
        BeanUtils.copyProperties(userDto,userEntity);

        userEntity.setEncryptedPassword(encoder.encode(userDto.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));

        UserEntity storedUserEntity = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUserEntity,userDto);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if(null==userEntity){
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
