package com.powerhouse.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Sprint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private List<String> priorities;
<<<<<<< Updated upstream
=======
	private List<String> workflows;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
>>>>>>> Stashed changes
	private List<Task> tasks;

	public Sprint() {
		name = "";
		description = "";
		priorities = new ArrayList<String>();
		tasks = new ArrayList<Task>();
	}
}
