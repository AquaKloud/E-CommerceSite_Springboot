package com.aquakloud.ECommerce.repository;

import com.aquakloud.ECommerce.model.AuthenticationToken;
import com.aquakloud.ECommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
