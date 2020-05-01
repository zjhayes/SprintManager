package com.powerhouse.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.powerhouse.sprints.sprint.Sprint;
import com.powerhouse.sprints.task.Task;

class SprintTest {
	private Sprint s;

	@BeforeEach
	void setUp() throws Exception {
		s = new Sprint();
	}

	@Test
	void testSprintShouldHaveName() {
		s.setName("test");
		assertEquals("test", s.getName());
	}

	@Test
	void SprintShouldHaveDescription() {
		s.setDescription("description");
		assertEquals("description", s.getDescription());
	}

	@Test
	void testSpringShouldHaveListOfTasks() {
		assertThat(s.getTasks()).isInstanceOf(List.class);
	}

	@Test
	void testSprintShouldReturn0PercentWhenItHasNoTasks() {
		assertThat(s.calculateCompletedPercentage()).isEqualTo(0);
	}

	@Test
	void testSprintShouldReturn50PercentWhenHalfOfTasksAreComplete() {
		Task t1 = new Task();
		Task t2 = new Task();
		t1.setCompleted(true);
		List<Task> tasks = new ArrayList<>();
		tasks.add(t1);
		tasks.add(t2);
		s.setTasks(tasks);
		assertThat(s.calculateCompletedPercentage()).isEqualTo(50);
	}

}
