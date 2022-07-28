package com.yehia.repositories;

import com.yehia.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();

    User findUserByName(String name);

    User findUserById(int id);
}
