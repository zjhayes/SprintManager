package com.powerhouse.sprints.schemes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.project.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class PriorityScheme extends NamedEntity {
	@ElementCollection
	private List<String> priorities = new ArrayList<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "workflow")
	private Set<Project> projects;
	
	public PriorityScheme() {
		super();
	}
	
	public void addPriority(String priority) {
		priorities.add(priority);
	}
}
