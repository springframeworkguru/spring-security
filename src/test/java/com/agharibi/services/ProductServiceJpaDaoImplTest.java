package com.agharibi.services;


import com.agharibi.config.JpaIntegrationConfig;
import com.agharibi.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {

    private ProductService  productService;

    @Test
    public void testListMethod() throws Exception {
        List<Product> productList = (List<Product>) productService.listAll();

        assert productList.size() == 5;
    }



    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
