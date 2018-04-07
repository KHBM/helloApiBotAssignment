package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.Bots;

public interface BotsRepository extends JpaRepository<Bots, Integer> {

    Bots findByBotId(int index);
}
