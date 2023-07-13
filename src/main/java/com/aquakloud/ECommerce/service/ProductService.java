package com.aquakloud.ECommerce.service;

import com.aquakloud.ECommerce.dto.ProductDTO;
import com.aquakloud.ECommerce.model.Category;
import com.aquakloud.ECommerce.model.Product;
import com.aquakloud.ECommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void createProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        productRepo.save(product);
    }

    public ProductDTO getProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(product.getDescription());
        productDTO.setImageURL(product.getImageURL());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setId(product.getId());
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: allProducts) {
            productDTOS.add(getProductDTO(product));
        }
        return productDTOS;
    }

    public void updateProduct(ProductDTO productDTO, Integer productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        // throw an exception if product does not exists

        if(!optionalProduct.isPresent()){
            throw new RuntimeException("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productRepo.save(product);


    }
}
