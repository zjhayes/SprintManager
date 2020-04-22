package com.powerhouse.sprints.project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.powerhouse.sprints.auth.user.User;
import com.powerhouse.sprints.backlog.Backlog;
import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.sprint.Sprint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Project extends NamedEntity {

	@CreationTimestamp
	private LocalDate createdDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = CascadeType.ALL)
	private List<Sprint> sprints;
	
	@ToString.Exclude
	@ManyToMany(mappedBy="projects")
	private Set<User> projectMembers = new HashSet<User>();

	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Backlog backlog;
	
	public Project() {
		super();
	}
	
	public void addMember(User member) {
		projectMembers.add(member);
	}
}
