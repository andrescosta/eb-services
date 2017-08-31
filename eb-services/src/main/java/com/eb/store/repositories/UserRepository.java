package com.eb.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eb.store.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
