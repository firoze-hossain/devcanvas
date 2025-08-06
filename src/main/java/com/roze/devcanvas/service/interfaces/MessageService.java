package com.roze.devcanvas.service.interfaces;



import com.roze.devcanvas.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();

    Message findByEmail(String theEmail);

    Message save(Message message);

    void deleteById(Integer id);
}
