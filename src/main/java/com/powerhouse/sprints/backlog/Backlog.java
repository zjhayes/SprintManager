package com.powerhouse.sprints.backlog;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.sprint.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Backlog extends NamedEntity {
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "backlog", cascade = CascadeType.ALL)
	private List<Task> tasks;

	/**
	 * default constructor
	 */
	public Backlog() {
		super();
	}
	
	
}
