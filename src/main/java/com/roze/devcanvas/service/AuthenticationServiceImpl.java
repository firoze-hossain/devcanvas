package com.roze.devcanvas.service;

import com.roze.devcanvas.dto.AuthenticationRequest;
import com.roze.devcanvas.dto.AuthenticationResponse;
import com.roze.devcanvas.dto.RegisterRequest;
import com.roze.devcanvas.entity.User;
import com.roze.devcanvas.exception.DuplicateResourceException;
import com.roze.devcanvas.mapper.UserMapper;
import com.roze.devcanvas.repository.UserRepository;
import com.roze.devcanvas.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

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

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername());
        return userMapper.toDto(user);
    }
}
