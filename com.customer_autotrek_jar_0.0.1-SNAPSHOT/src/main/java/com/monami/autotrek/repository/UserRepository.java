package com.monami.autotrek.repository;

import com.monami.autotrek.user.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User,String> {

}
