package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public OrderService(OrderRepository repo, RestTemplate restTemplate, @Value("${product.service.url}") String productServiceUrl) {
        this.repo = repo;
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public OrderDto createOrder(OrderDto dto, String authHeader) {
        String url = productServiceUrl + "/api/products/" + dto.getProductId();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Product not found or unauthorized");
        }

        Map prod = response.getBody();
        double price = ((Number) prod.get("price")).doubleValue();

        Order o = new Order();
        o.setProductId(dto.getProductId());
        o.setQuantity(dto.getQuantity());
        o.setTotalPrice(price * dto.getQuantity());

        o = repo.save(o);

        OrderDto out = new OrderDto();
        out.setId(o.getId());
        out.setProductId(o.getProductId());
        out.setQuantity(o.getQuantity());
        out.setTotalPrice(o.getTotalPrice());
        return out;
    }
}
