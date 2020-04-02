package com.powerhouse.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sprint {
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private List<String> priorities;
	private List<Task> tasks;

	public Sprint() {
		name = "";
		description = "";
		priorities = new ArrayList<String>();
		tasks = new ArrayList<Task>();
	}
}
