package com.powerhouse.sprints;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.powerhouse.sprints.auth.model.Role;
import com.powerhouse.sprints.auth.model.User;
import com.powerhouse.sprints.auth.repository.RoleRepository;
import com.powerhouse.sprints.auth.repository.UserRepository;
import com.powerhouse.sprints.schemes.PriorityScheme;
import com.powerhouse.sprints.schemes.PrioritySchemeRepository;
import com.powerhouse.sprints.schemes.WorkflowScheme;
import com.powerhouse.sprints.schemes.WorkflowSchemeRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private WorkflowSchemeRepository workflowRepository;
	
	@Autowired
	private PrioritySchemeRepository priorityRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		createRoleIfNotFound("ROLE_USER");
		createRoleIfNotFound("ROLE_ADMIN");
		User admin = createAdminUserIfNotFound();
		userRepository.save(admin);
		createWorkflowIfNotFound();
		createPrioritySchemeIfNotFound();
		
		alreadySetup = true;
	}

	private User createAdminUserIfNotFound() {
		User admin = userRepository.findOneByEmail("admin@cis175sprints.com");
		if (admin == null) {
			admin = new User();
			Role adminRole = roleRepository.findOneByName("ROLE_ADMIN");
			admin.setFirstName("ADMIN");
			admin.setLastName("ADMIN");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setEmail("admin@cis175sprints.com");
			admin.setEnabled(true);
			admin.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
			adminRole.setUsers(new HashSet<User>(Arrays.asList(admin)));
		}
		return admin;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findOneByName(name);
		if (role == null) {
			role = new Role(name);
			roleRepository.save(role);
		}
		return role;
	}
	
	private void createWorkflowIfNotFound() {
		
		if(workflowRepository.findAll().size() == 0) {
			
			WorkflowScheme defaultWorkflow = new WorkflowScheme();
			defaultWorkflow.setName("Default Workflow");
			defaultWorkflow.addStep("To Do");
			defaultWorkflow.addStep("In Progress");
			defaultWorkflow.addStep("On Hold");
			defaultWorkflow.addStep("Done");
			
			workflowRepository.save(defaultWorkflow);
		}
	}
	
	private void createPrioritySchemeIfNotFound() {
		
		if(priorityRepository.findAll().size() == 0) {
			
			PriorityScheme defaultPriorityScheme = new PriorityScheme();
			defaultPriorityScheme.setName("Default Priority Scheme");
			defaultPriorityScheme.addPriority("High");
			defaultPriorityScheme.addPriority("Medium");
			defaultPriorityScheme.addPriority("Low");
			defaultPriorityScheme.addPriority("Blocker");
			
			priorityRepository.save(defaultPriorityScheme);
		}
	}
}