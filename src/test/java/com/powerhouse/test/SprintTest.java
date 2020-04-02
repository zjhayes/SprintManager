package com.powerhouse.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.powerhouse.beans.Sprint;

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
	void testSprintShouldHaveListOfPriorities() {
		assertThat(s.getPriorities()).isInstanceOf(List.class);
	}

	@Test
	void testSpringShouldHaveListOfTasks() {
		assertThat(s.getTasks()).isInstanceOf(List.class);
	}
	
	void testSprintShouldHaveStartDate() {
		s.setStartDate(new Date());
		assertThat(s.getStartDate()).isNot(null);
	}
	void testSprintShouldHaveEndDate() {
		s.setEndDate(new Date());
		assertThat(s.getStartDate()).isNot(null);
	}

}
