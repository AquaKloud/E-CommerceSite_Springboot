package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.model.AuthenticationToken;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenRepo tokenRepo;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {

        tokenRepo.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }
}
