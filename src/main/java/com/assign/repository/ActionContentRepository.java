package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.BotActionContent;

public interface ActionContentRepository extends JpaRepository<BotActionContent, Integer>{

    BotActionContent findByContentId(int index);
    
    BotActionContent findByActionId(Integer actionId);
}
