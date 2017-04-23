package com.agharibi.services;

import com.agharibi.commands.ProductForm;
import com.agharibi.domain.Product;

/**
 * Created by Armen on 4/8/17.
 */
public interface ProductService extends CRUDService<Product> {

    Product saveOrUpdate(ProductForm productForm);
}
