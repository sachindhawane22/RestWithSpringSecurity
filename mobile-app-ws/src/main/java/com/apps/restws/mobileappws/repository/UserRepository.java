package com.apps.restws.mobileappws.repository;

import com.apps.restws.mobileappws.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findUserByEmail(String email);

    UserEntity findUserByUserId(String userId);


}

