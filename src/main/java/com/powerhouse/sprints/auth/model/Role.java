package com.powerhouse.sprints.auth.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.powerhouse.sprints.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	@ToString.Exclude
	private Set<User> users;

	public Role(String name) {
		this.name = name;
	}
}
