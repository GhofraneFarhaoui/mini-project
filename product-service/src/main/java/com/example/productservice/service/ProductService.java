package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public ProductDto save(ProductDto dto) {
        Product p = ProductMapper.toEntity(dto);
        p = repo.save(p);
        return ProductMapper.toDto(p);
    }

    public List<ProductDto> findAll() {
        return repo.findAll().stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    public ProductDto findById(Long id) {
        return repo.findById(id).map(ProductMapper::toDto).orElse(null);
    }
}
