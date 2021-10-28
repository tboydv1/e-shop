package com.ecommerce.shop.service;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.data.repository.ProductRepository;
import com.ecommerce.shop.service.mapper.ProductMapper;
import com.ecommerce.shop.web.exception.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(Long id, ProductDto productDto) throws ProductDoesNotExistException {
        if(productDto == null){
            throw new NullPointerException("Product Dto cannot be null");
        }

        Optional<Product> result = productRepository.findById(id);
        log.info("Step 2");
        if(result.isPresent()){
            log.info("Result is present --> {}", result.get());
            Product product = result.get();
            productMapper.mapDtoToProduct(productDto, product);
            return productRepository.save(product);
        }
        else{
            throw new ProductDoesNotExistException("Product with id "+ id +" does not exist");
        }
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
