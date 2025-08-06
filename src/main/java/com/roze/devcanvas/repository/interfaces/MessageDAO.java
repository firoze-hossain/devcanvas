package com.roze.devcanvas.repository.interfaces;

import com.roze.devcanvas.entity.Message;

import java.util.List;

public interface MessageDAO {
    List<Message> getAllMessages();

    Message findByEmail(String theEmail);

    Message save(Message message);

    void deleteById(Integer id);
}
