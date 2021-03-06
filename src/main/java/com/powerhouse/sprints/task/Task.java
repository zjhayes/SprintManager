package com.powerhouse.sprints.task;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.sprint.ProjectResource;
import com.powerhouse.sprints.sprint.Sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Task extends ProjectResource implements Comparable<Task> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String taskDescription;
	@OneToOne
	private User assignedUser;
	private String priority;

	// Starting simple
	private boolean completed;

	// TODO - this will be a complicated join when custom priority schemes are
	// implemented
	private String currentStep;
	private int storyPoints;
	@ToString.Exclude
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private Sprint sprint;
	
	@Override
	public int compareTo(Task anotherTask)
	{
		List<String> priorityScheme = sprint.getProject().getPriorityScheme().getPriorities();
		int thisIndex = priorityScheme.indexOf(priority);
		int anotherIndex = priorityScheme.indexOf(anotherTask.getPriority());
		if(thisIndex > anotherIndex) {
			return 1;
		}
		else if(thisIndex < anotherIndex) {
			return -1;
		}
		else {
			return 0;
		}
	}
}