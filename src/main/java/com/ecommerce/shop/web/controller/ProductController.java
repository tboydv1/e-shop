package com.ecommerce.shop.web.controller;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.service.ProductService;
import com.ecommerce.shop.web.exception.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    ProductService productServiceImpl;
    @Autowired
    public ProductController(ProductService productServiceImpl){
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/")
    public List<Product> getAll(){
        return productServiceImpl.findAll();
    }
    @PostMapping("/")
    public Product save(@RequestBody Product product){
        log.info("Product request --> {}", product);
        return productServiceImpl.save(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @RequestBody ProductDto productDto){
        log.info("Request id --> {}",id);
        log.info("Product dto details --> {}", productDto);
        Product product = null;

        try{
            product =  productServiceImpl.update(id, productDto);
        }catch (ProductDoesNotExistException ex){

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().body(product);

    }
}
