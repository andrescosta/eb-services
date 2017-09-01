package com.eb.store.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eb.store.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByOpenId(@Param("openId") String openId);
	User findByEmail(@Param("email") String email);
	User findByAccountIdentifier(@Param("accountIdentifier") String accountIdentifier);
	Long deleteByEmail(@Param("email") String email);
}
