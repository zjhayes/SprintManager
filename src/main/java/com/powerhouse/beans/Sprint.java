package com.powerhouse.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Sprint extends NamedEntity {
	private String description;
	private Date startDate;
	private Date endDate;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sprint", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<Task> tasks;

	public Sprint() {
		setName("");
		description = "";
		tasks = new ArrayList<Task>();
	}

}
