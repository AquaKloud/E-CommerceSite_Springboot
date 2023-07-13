package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.dto.ProductDTO;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.model.WishList;
import com.aquakloud.ECommerce.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepo wishListRepo;

    @Autowired
    private ProductService productService;

    public void createWishlist(WishList wishList) {
           wishListRepo.save(wishList);
    }

    public List<ProductDTO> getWishListForUser(User user) {
        final List<WishList> wishListList = wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(WishList wishList : wishListList){
            productDTOList.add(productService.getProductDTO(wishList.getProduct()));

        }
        return productDTOList;
    }
}
