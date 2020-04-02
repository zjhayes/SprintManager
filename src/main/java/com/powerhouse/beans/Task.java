package com.powerhouse.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	private String subject;
	private String description;
	private String assignedUser;
	private String currentStep;
	private int storyPoints;
}
