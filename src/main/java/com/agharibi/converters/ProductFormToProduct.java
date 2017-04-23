package com.agharibi.converters;


import com.agharibi.commands.ProductForm;
import com.agharibi.domain.Product;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product>{

    @Override
    public Product convert(ProductForm productForm) {

    Product product = new Product();
    product.setId(productForm.getId());
    product.setVersion(productForm.getVersion());
    product.setDescription(productForm.getDescription());
    product.setPrice(productForm.getPrice());
    product.setImageUrl(productForm.getImageUrl());

    return product;
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
