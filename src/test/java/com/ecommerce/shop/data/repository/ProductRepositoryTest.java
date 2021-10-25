package com.ecommerce.shop.data.repository;

import com.ecommerce.shop.data.model.Currency;
import com.ecommerce.shop.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepositoryImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void createProductTest(){

        Product product = new Product();
        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(Currency.NGN);
        product.setDetails("Quisque velit nisi, pretium ut lacinia in," +
                            " elementum id enim. Vivamus magna justo," +
                            " lacinia eget consectetur sed, " +
                            "convallis at tellus. Vivamus suscipit tortor eget felis porttitor volutpat. " +
                            "Vivamus suscipit tortor eget felis porttitor volutpat. " +
                            "Vivamus suscipit tortor eget felis porttitor volutpat." +
                            " Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui." +
                            " Pellentesque in ipsum id orci porta dapibus. " +
                            "Sed porttitor lectus nibh.");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        log.info("Product before saving --> {}", product);
        productRepositoryImpl.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("Product after saving --> {}", product);
    }

    @Test
    @Transactional
    public void whenFindAllProductIsCalledThenProductListIsReturnedTest(){

        List<Product> products = productRepositoryImpl.findAll();
        assertThat(products).hasSize(4);
        log.info("Product returned from database --> {}", products);
    }


    @Test
    public void findExistingProductById(){
        Product existingProduct =
                productRepositoryImpl.findById(110L).orElse(null);

        assertThat(existingProduct).isNotNull();
        log.info("Product --> {}", existingProduct);
    }

    @Test
    public void deleteExistingProductById(){
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNotNull();
        productRepositoryImpl.deleteById(110L);
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNull();

    }

}