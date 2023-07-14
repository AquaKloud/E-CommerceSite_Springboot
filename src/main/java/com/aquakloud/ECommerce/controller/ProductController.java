package com.aquakloud.ECommerce.controller;

import com.aquakloud.ECommerce.common.ApiResponse;
import com.aquakloud.ECommerce.dto.ProductDTO;
import com.aquakloud.ECommerce.model.Category;
import com.aquakloud.ECommerce.repository.CategoryRepo;
import com.aquakloud.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO productDTO){
        Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse(false, "category does not exists"),
                    HttpStatus.BAD_REQUEST
            );
        }
        productService.createProduct(productDTO, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(true, "product has been added"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> productDTOList = productService.getAllProducts();
        return  new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }

    // create an api to edit the product

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDTO productDTO) throws Exception {
       Optional<Category> optionalCategory = categoryRepo.findById(productDTO.getCategoryId());
       if(!optionalCategory.isPresent()){
           return new ResponseEntity<ApiResponse>(
                   new ApiResponse(false,"category does not exists"),
                   HttpStatus.BAD_REQUEST
           );
       }
       productService.updateProduct(productDTO, productId);
       return new ResponseEntity<ApiResponse>(
               new ApiResponse(true, "product has been updated"),
               HttpStatus.OK
       );
    }
}
