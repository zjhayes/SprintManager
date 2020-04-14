package com.powerhouse.sprints.auth.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import com.powerhouse.sprints.model.BaseEntity;
import com.powerhouse.sprints.model.NamedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
	private RoleEnum role;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
