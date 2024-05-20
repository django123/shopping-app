package com.django.it.shoppingapp.services;

import com.django.it.shoppingapp.dtos.requests.AuthenticationRequest;
import com.django.it.shoppingapp.dtos.responses.AuthenticationResponse;
import com.django.it.shoppingapp.dtos.requests.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
