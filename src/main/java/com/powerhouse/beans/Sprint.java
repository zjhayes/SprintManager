package com.powerhouse.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Sprint extends NamedEntity {
	private String description;
	private Date startDate;
	private Date endDate;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<Task> tasks;

	public Sprint() {
		setName("");
		description = "";
		tasks = new ArrayList<Task>();
	}
	
	public long calculateCompletedPercentage() {
		if (tasks.size()==0) {
			return 0;
		}
		List<Task> completedTasks = tasks.stream()
				.filter(Task -> Task.isCompleted())
				.collect(Collectors.toList());
		long percentage = Math.round(completedTasks.size()/(double) tasks.size() * 100);
		System.out.println(percentage);
		return percentage;
	}

}
