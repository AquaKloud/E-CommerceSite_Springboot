package com.aquakloud.ECommerce.repository;

import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
