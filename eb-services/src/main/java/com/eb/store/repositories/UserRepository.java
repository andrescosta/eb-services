package com.eb.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eb.store.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByOpenId(@Param("openId") String openId);
	User findByEmail(@Param("email") String email);
	Long deleteByEmail(@Param("email") String email);
}
