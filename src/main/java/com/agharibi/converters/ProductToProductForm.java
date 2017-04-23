package com.agharibi.converters;

import com.agharibi.commands.ProductForm;
import com.agharibi.domain.Product;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm>{

    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();

        productForm.setId(product.getId());
        productForm.setVersion(product.getVersion());
        productForm.setPrice(product.getPrice());
        productForm.setDescription(product.getDescription());
        productForm.setImageUrl(product.getImageUrl());

        return productForm;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
