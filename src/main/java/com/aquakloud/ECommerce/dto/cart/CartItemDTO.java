package com.aquakloud.ECommerce.dto.cart;

import com.aquakloud.ECommerce.model.Cart;
import com.aquakloud.ECommerce.model.Product;

public class CartItemDTO {

    private Integer id;
    private Integer quantity;

    private Product product;

    public CartItemDTO(Cart cart) {
    }

    public CartItemDTO(Integer id, Integer quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
