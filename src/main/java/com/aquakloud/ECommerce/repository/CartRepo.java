package com.aquakloud.ECommerce.repository;

import com.aquakloud.ECommerce.model.Cart;
import com.aquakloud.ECommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
