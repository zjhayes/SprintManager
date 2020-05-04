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
import com.powerhouse.sprints.schemes.PriorityScheme;
import com.powerhouse.sprints.schemes.WorkflowScheme;
import com.powerhouse.sprints.sprint.Sprint;
import com.powerhouse.sprints.task.Task;

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

	@OneToMany(mappedBy = "project", cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE })
	private List<Sprint> sprints;

	@ToString.Exclude
	@ManyToMany(mappedBy = "projects", cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	private Set<User> projectMembers = new HashSet<User>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Task> backlog;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private WorkflowScheme workflow;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private PriorityScheme priorityScheme;

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
