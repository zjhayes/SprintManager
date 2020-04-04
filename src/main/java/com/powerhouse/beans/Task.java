package com.powerhouse.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseEntity {

	private String subject;
	private String description;
	private String assignedUser;
	private String priority;

	// Starting simple
	private boolean completed;

	// TODO - this will be a complicated join when custom priority schemes are
	// implemented
	private String currentStep;
	private int storyPoints;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	private Sprint sprint;
}