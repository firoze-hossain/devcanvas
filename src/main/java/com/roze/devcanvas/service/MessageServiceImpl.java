package com.roze.devcanvas.service;


import com.roze.devcanvas.entity.Message;
import com.roze.devcanvas.repository.interfaces.MessageDAO;
import com.roze.devcanvas.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    @Override
    public Message findByEmail(String theEmail) {

        Message foundMessage = messageDAO.findByEmail(theEmail);
        if(foundMessage == null)
            throw new RuntimeException("Experience details not found in database");
        else
            return foundMessage;
    }

    @Transactional
    @Override
    public Message save(Message message) {
        return messageDAO.save(message);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        messageDAO.deleteById(id);
    }
}
