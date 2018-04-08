package com.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.domain.BotChats;

public interface BotChatsRepository extends JpaRepository<BotChats, Integer>{

    List<BotChats> findByBotId(int botId);
}
