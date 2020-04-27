package com.powerhouse.sprints.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.schemes.WorkflowScheme;
import com.powerhouse.sprints.sprint.Sprint;
import com.powerhouse.sprints.sprint.Task;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	private List<Sprint> sprints;

	@ToString.Exclude
	@ManyToMany(mappedBy = "projects", cascade = { CascadeType.MERGE })
	private Set<User> projectMembers = new HashSet<User>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> backlog;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private WorkflowScheme workflow;

	public Project() {
		super();
		backlog = new ArrayList<Task>();
	}

	public void addTask(Task task) {
		if (task.isNew()) {
			this.backlog.add(task);
		}
		task.setProject(this);
	}
	
	public void clearMembers() {
		projectMembers.clear();
	}
  
	public void addMember(User member) {
		projectMembers.add(member);
	}
}
