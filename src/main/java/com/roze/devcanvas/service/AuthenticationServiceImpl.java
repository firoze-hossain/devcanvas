package com.roze.devcanvas.service;

import com.roze.devcanvas.dto.AuthenticationResponse;
import com.roze.devcanvas.dto.RegisterRequest;
import com.roze.devcanvas.entity.User;
import com.roze.devcanvas.exception.DuplicateResourceException;
import com.roze.devcanvas.mapper.UserMapper;
import com.roze.devcanvas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new DuplicateResourceException("This email:\"" + request.getEmail() + "\" already associated with other user ");
        }
        if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
            throw new DuplicateResourceException("This username:\"" + request.getUsername() + "\" already associated with other user ");
        }
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);

    }
}
