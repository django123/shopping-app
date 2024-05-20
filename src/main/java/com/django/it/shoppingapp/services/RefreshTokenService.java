package com.django.it.shoppingapp.services;

import com.django.it.shoppingapp.dtos.requests.RefreshTokenRequest;
import com.django.it.shoppingapp.dtos.responses.RefreshTokenResponse;
import com.django.it.shoppingapp.model.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Integer userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
    ResponseCookie generateRefreshTokenCookie(String token);
    String getRefreshTokenFromCookies(HttpServletRequest request);
    void deleteByToken(String token);
    ResponseCookie getCleanRefreshTokenCookie();
}
