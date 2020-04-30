package com.powerhouse.sprints.sprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@PostAuthorize("returnObject.project.projectMembers.contains(authentication.principal.user)")
	public Task findById(long id) {
		return taskRepository.findById(id).orElse(null);
	}
	
	public void save(Task task) {
		taskRepository.save(task);
	}
}
