package com.powerhouse.sprints.auth.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;

import com.powerhouse.sprints.model.BaseEntity;
import com.powerhouse.sprints.project.Project;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;

	@Column(name = "password")
	@Transient
	private String password;

	@Column(name = "first_name")
	@NotEmpty(message = "Please provide your first name")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "confirmation_token")
	private String confirmationToken;

	@ManyToMany
	@ToString.Exclude
	private Set<Role> roles;

	@ManyToMany
	@JoinTable(name = "project_project_members", joinColumns = @JoinColumn(name = "project_member_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@ToString.Exclude
	private Set<Project> projects = new HashSet<Project>();

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void addToProject(Project project) {
		projects.add(project);
	}
}
