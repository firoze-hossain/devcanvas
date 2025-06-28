package com.roze.devcanvas.service;

import com.roze.devcanvas.dto.AuthenticationResponse;
import com.roze.devcanvas.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
}
