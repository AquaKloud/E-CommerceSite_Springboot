package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.dto.cart.AddToCartDTO;
import com.aquakloud.ECommerce.dto.cart.CartDTO;
import com.aquakloud.ECommerce.dto.cart.CartItemDTO;
import com.aquakloud.ECommerce.exceptions.CustomException;
import com.aquakloud.ECommerce.model.Cart;
import com.aquakloud.ECommerce.model.Product;
import com.aquakloud.ECommerce.model.User;
import com.aquakloud.ECommerce.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepo cartRepo;

    public void addToCart(AddToCartDTO addToCartDTO, User user) {

        // validate if the product id is valid
        Product product = productService.findById(addToCartDTO.getProductId());

        // save the cart

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDTO.getQuantity());
        cart.setCreatedDate(new Date());

        // save the cart
        cartRepo.save(cart);
    }

    public CartDTO listCartItems(User user) {

        List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        double totalCost = 0;
        for(Cart cart : cartList){
            CartItemDTO cartItemDTO = new CartItemDTO(cart);
            totalCost +=cartItemDTO.getQuantity() * cart.getProduct().getPrice();
            cartItemDTOList.add(cartItemDTO);
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalCost(totalCost);
        cartDTO.setCartItemDTOList(cartItemDTOList);
        return cartDTO;

    }

    public void deleteCartItem(Integer cartItemId, User user) {

        // the item id belongs to user
       Optional<Cart> optionalCart = cartRepo.findById(cartItemId);

       if(optionalCart.isPresent()){
           throw new CustomException("cart item id is invalid: " + cartItemId);
       }
       Cart cart = optionalCart.get();

       if(cart.getUser() != user){
           throw new CustomException("cart item does not belong to user:" +cartItemId);
       }

       cartRepo.delete(cart);
    }
}
