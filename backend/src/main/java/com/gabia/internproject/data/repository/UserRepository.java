package com.gabia.internproject.data.repository;

import com.gabia.internproject.data.entity.user;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<user, Integer> {

}
