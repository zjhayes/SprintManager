package com.powerhouse.sprints.project;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.powerhouse.sprints.auth.user.User;
import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.sprint.Sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends NamedEntity {

	// TODO Add Permitted Users
	@CreationTimestamp
	private LocalDate createdDate;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.ALL)
	private List<Sprint> sprints;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="projects", cascade=CascadeType.ALL)
	private List<User> users;

	public Project() {
		super();
	}
}
