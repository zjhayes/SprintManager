package com.powerhouse.beans;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Task {


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String subject;
	private String description;
	private String assignedUser;
	// TODO - this will be a complicated join when custom priority schemes are implemented
	private String currentStep;
	private int storyPoints;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	private Sprint sprint;
}