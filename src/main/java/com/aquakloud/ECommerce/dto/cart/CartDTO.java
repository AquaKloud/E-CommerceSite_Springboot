package com.aquakloud.ECommerce.dto.cart;

import java.util.List;

public class CartDTO {

    private List<CartItemDTO> cartItemDTOList;
    private double totalCost;

    public CartDTO() {
    }

    public CartDTO(List<CartItemDTO> cartItemDTOList, double totalCost) {
        this.cartItemDTOList = cartItemDTOList;
        this.totalCost = totalCost;
    }

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
