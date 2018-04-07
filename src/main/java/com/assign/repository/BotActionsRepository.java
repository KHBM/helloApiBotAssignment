package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.BotActions;

public interface BotActionsRepository extends JpaRepository<BotActions, Integer> {

    BotActions findByActionId(int index);
}
