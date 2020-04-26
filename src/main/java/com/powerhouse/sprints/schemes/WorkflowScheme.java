package com.powerhouse.sprints.schemes;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
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
public class WorkflowScheme extends NamedEntity {
	
	@ElementCollection
	private Set<String> steps = new LinkedHashSet<String>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "workflow", cascade = { CascadeType.MERGE })
	private Set<Project> projects;
	
	public WorkflowScheme() {
		super();
	}
	
	public WorkflowScheme(Set<String> steps) {
		super();
		this.steps = steps;
	}
	
	public void addStep(String step) {
		steps.add(step);
	}
}
