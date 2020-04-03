package com.powerhouse.controller;

import org.springframework.context.annotation.Configuration;

import com.powerhouse.beans.Sprint;
import com.powerhouse.beans.Task;

@Configuration
public class BeanConfiguration {

	public Sprint sprint() {
		Sprint bean = new Sprint();
		return bean;
	}

	public Task task() {
		Task bean = new Task();
		return bean;
	}
}
