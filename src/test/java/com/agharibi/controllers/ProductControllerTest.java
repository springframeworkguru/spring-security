package com.agharibi.controllers;

import com.agharibi.domain.Product;
import com.agharibi.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Armen on 4/9/17.
 */
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        // initializes the controller and mocks
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productService.listAll()).thenReturn((List)productList);
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"));

    }

    @Test
    public void testShow() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        Integer id = 1;
        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNewProduct() throws Exception {
        // should not call product service
        verifyZeroInteractions(productService);

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description = "Test description";
        BigDecimal price = new BigDecimal("12.99");
        String imageUrl = "http://example.com";

        Product product = new Product();
        product.setId(id);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);

        when(productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(product);

        mockMvc.perform(post("/product")
                .param("id", "1")
                .param("description" , description)
                .param("price", "12.99")
                .param("imageUrl", imageUrl))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/product/show/1"))
                    .andExpect(model().attribute("product", instanceOf(Product.class)))
                    .andExpect(model().attribute("product", hasProperty("id", is(id))))
                    .andExpect(model().attribute("product", hasProperty("description", is(description))))
                    .andExpect(model().attribute("product", hasProperty("price", is(price))))
                    .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(captor.capture());

        assertEquals(id, captor.getValue().getId());
        assertEquals(description, captor.getValue().getDescription());
        assertEquals(price, captor.getValue().getPrice());
        assertEquals(imageUrl, captor.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));
        verify(productService, times(1)).delete(id);
    }

}
