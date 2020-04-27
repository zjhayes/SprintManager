package com.powerhouse.sprints.sprint;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.project.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sprint extends ProjectResource {

	private static final long serialVersionUID = 1L;
	private String description;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;

	public Sprint() {
		setName("");
		description = "";
		tasks = new ArrayList<Task>();
	}

	public long calculateCompletedPercentage() {
		if (tasks.size() == 0) {
			return 0;
		}
		List<Task> completedTasks = tasks.stream().filter(Task -> Task.isCompleted()).collect(Collectors.toList());
		long percentage = Math.round(completedTasks.size() / (double) tasks.size() * 100);
		System.out.println(percentage);
		return percentage;
	}

	public void addTask(Task task) {
		if (task.isNew()) {
			this.tasks.add(task);
		}
		task.setSprint(this);
	}

}
