package com.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.BotActions;

public interface BotActionsRepository extends JpaRepository<BotActions, Integer> {

    BotActions findByActionId(int index);
    
    List<BotActions> findByBotId(int botId);
    
    BotActions findByBotIdAndActionId(int botId, int actionId);
}
