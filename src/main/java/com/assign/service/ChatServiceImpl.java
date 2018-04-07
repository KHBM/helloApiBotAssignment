package com.assign.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.repository.ActionContentRepository;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
    
    @Autowired
    private ActionContentRepository contentRepository;
    
    @PostConstruct
    public void postConstruct() {
        
    }
}
