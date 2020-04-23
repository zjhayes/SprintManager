package com.powerhouse.sprints.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.powerhouse.sprints.auth.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByName(String name);
	Role findOneByName(String name);
}
