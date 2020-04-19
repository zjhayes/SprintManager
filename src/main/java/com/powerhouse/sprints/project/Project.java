package com.powerhouse.sprints.project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.powerhouse.sprints.auth.user.Role;
import com.powerhouse.sprints.auth.user.User;
import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.sprint.Sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Project extends NamedEntity {

	// TODO Add Permitted Users
	@CreationTimestamp
	private LocalDate createdDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.ALL)
	private List<Sprint> sprints;
	
	@ToString.Exclude
	@ManyToMany(mappedBy="projects")
	private Set<User> projectMembers = new HashSet<User>();

	public Project() {
		super();
	}
}
