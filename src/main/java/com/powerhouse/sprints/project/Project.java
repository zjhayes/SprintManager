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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = { CascadeType.MERGE })
	private List<Sprint> sprints;

	@ToString.Exclude
	@ManyToMany(mappedBy = "projects")
	private Set<User> projectMembers = new HashSet<User>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Task> backlog;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project", cascade = { CascadeType.MERGE })
	private Set<WorkflowScheme> availableWorkflowSchemes;
	private Long currentWorkflow;

	public Project() {
		super();
		backlog = new ArrayList<Task>();
		/*
		// Create and Add Default Workflow
		Set<String> defaultSteps = new HashSet<String>();
		defaultSteps.add("To Do");
		defaultSteps.add("In Progress");
		defaultSteps.add("Done");
		WorkflowScheme defaultWorkflow = new WorkflowScheme(defaultSteps);
		this.addWorkflowScheme(defaultWorkflow);*/
	}

	public void addTask(Task task) {
		if (task.isNew()) {
			this.backlog.add(task);
		}
		task.setProject(this);
	}
  
	public void addMember(User member) {
		projectMembers.add(member);
	}
	
	public void addWorkflowScheme(WorkflowScheme workflowScheme) {
		availableWorkflowSchemes.add(workflowScheme);
	}
}
