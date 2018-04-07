package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.Choices;

public interface ChoicesRepository extends JpaRepository<Choices, Integer> {

}
