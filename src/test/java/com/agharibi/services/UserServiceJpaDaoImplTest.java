package com.agharibi.services;

import com.agharibi.config.JpaIntegrationConfig;
import com.agharibi.domain.*;
import org.apache.log4j.Logger;
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
public class UserServiceJpaDaoImplTest {

    private transient final Logger log = Logger.getLogger(this.getClass());
    private UserService userService;
    private ProductService productService;

    @Test
    public void testSaveOrUpdateUser() throws Exception {
        User user = new User();
        user.setUsername("agharibi");
        user.setPassword("password");
        user = userService.saveOrUpdate(user);

        assert user.getId() != null;
        assert user.getEncryptedPassword() != null;
        log.error( " \n Encrypted password ->  " + user.getEncryptedPassword());
    }


    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();
        user.setUsername("agharibi");
        user.setPassword("password");

        Customer customer = new Customer();
        customer.setFirstName("Armen");
        customer.setLastName("Hacopian");

        user.setCustomer(customer);
        user = userService.saveOrUpdate(user);

        assert user.getId() != null;
        assert user.getVersion() != null;
        assert user.getCustomer() != null;
        assert user.getCustomer().getId() != null;
    }

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();
        user.setUsername("agharibi");
        user.setPassword("password");
        user.setCart(new Cart());

        user = userService.saveOrUpdate(user);

        assert user.getId() != null;
        assert user.getVersion() != null;
        assert user.getCart() != null;
        assert user.getCart().getId() != null;
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User();
        user.setUsername("agharibi");
        user.setPassword("password");
        user.setCart(new Cart());

        List<Product> products = (List<Product>) productService.listAll();

        CartDetail itemOne = new CartDetail();
        itemOne.setProduct(products.get(0));
        user.getCart().addCartDetail(itemOne);

        CartDetail itemTwo = new CartDetail();
        itemTwo.setProduct(products.get(1));
        user.getCart().addCartDetail(itemTwo);

        user = userService.saveOrUpdate(user);

        assert user.getId() != null;
        assert user.getVersion() != null;
        assert user.getCart() != null;
        assert user.getCart().getId() != null;
        assert user.getCart().getCartDetails().size() == 2;

    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();
        user.setUsername("Alex");
        user.setPassword("password");
        user.setCart(new Cart());

        List<Product> products = (List<Product>) productService.listAll();

        CartDetail itemOne = new CartDetail();
        itemOne.setProduct(products.get(0));
        user.getCart().addCartDetail(itemOne);

        CartDetail itemTwo = new CartDetail();
        itemTwo.setProduct(products.get(1));
        user.getCart().addCartDetail(itemTwo);

        user = userService.saveOrUpdate(user);

        assert user.getCart().getCartDetails().size() == 2;
        user.getCart().removeCartDetail(user.getCart().getCartDetails().get(0));
        userService.saveOrUpdate(user);

        assert user.getCart().getCartDetails().size() == 1;
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
