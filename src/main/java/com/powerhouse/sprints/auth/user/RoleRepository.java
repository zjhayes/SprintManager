package com.powerhouse.sprints.auth.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	List<Role> findByRole(RoleEnum role);
}
