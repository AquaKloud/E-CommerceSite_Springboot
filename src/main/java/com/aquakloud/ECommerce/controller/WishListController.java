package com.aquakloud.ECommerce.controller;

import com.aquakloud.ECommerce.common.ApiResponse;
import com.aquakloud.ECommerce.dto.ProductDTO;
import com.aquakloud.ECommerce.model.Product;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.model.WishList;
import com.aquakloud.ECommerce.service.AuthenticationService;
import com.aquakloud.ECommerce.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

    //save product as wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token){

        // authenticate the token
        authenticationService.authenticate(token);
        // find the user
        User user = authenticationService.getUser(token);
        //save the item in wishlist

        WishList wishList = new WishList(user, product);

        wishListService.createWishlist(wishList);
        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token){
        // authenticate token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);
        List<ProductDTO> productDTOList = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);

    }

    //get all wishlist item for a user

}
