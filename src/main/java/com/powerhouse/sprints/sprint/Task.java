package com.powerhouse.sprints.sprint;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.powerhouse.sprints.model.BaseEntity;

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
public class Task extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String taskDescription;
	private String assignedUser;
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
}