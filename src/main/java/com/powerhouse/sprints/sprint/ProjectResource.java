package com.powerhouse.sprints.sprint;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.powerhouse.sprints.model.NamedEntity;
import com.powerhouse.sprints.project.Project;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class ProjectResource extends NamedEntity {
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private Project project;

}
