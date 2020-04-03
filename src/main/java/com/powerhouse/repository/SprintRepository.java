package com.powerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerhouse.beans.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
