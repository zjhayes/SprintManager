package com.powerhouse.sprints.backlog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.project.Project;
import com.powerhouse.sprints.sprint.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Backlog extends NamedEntity {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "backlog", cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	@OneToOne
	@MapsId
	private Project project;
	

	/**
	 * default constructor
	 */
	public Backlog() {
		tasks = new ArrayList<Task>();
	}
	
	public void addTask(Task task) {
		if (task.isNew()) {
			this.tasks.add(task);
		}
		task.setBacklog(this);
	}
}
