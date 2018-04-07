package com.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.Choices;

public interface ChoicesRepository extends JpaRepository<Choices, Integer> {

    Choices findByChoiceId(int choiceId);

    List<Choices> findByContentId(Integer contentId);
}
