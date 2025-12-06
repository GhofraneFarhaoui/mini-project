package com.example.productservice.mapper;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.model.Product;

public class ProductMapper {
    public static ProductDto toDto(Product p) {
        if (p == null) return null;
        ProductDto d = new ProductDto();
        d.setId(p.getId());
        d.setName(p.getName());
        d.setDescription(p.getDescription());
        d.setPrice(p.getPrice());
        return d;
    }

    public static Product toEntity(ProductDto d) {
        if (d == null) return null;
        Product p = new Product();
        p.setName(d.getName());
        p.setDescription(d.getDescription());
        p.setPrice(d.getPrice());
        return p;
    }
}
