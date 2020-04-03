package com.powerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerhouse.beans.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
