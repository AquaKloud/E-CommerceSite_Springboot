package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.exceptions.AuthenticationFailException;
import com.aquakloud.ECommerce.model.AuthenticationToken;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.repository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public void authenticate(String token) throws AuthenticationFailException{
        // null check
        if(Objects.isNull(token)){
            // throw an exception
            throw new AuthenticationFailException("token not present");

        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token not valid");
        }
    }

    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        // authentication is not null
        return authenticationToken.getUser();
    }
}
