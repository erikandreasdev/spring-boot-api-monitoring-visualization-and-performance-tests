package com.erikandreas.metricsdemo.service;

import com.erikandreas.metricsdemo.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.stream.Stream;

public abstract class BaseBlackFridayDiscount implements BlackFridayDiscount {

    @Value("${kafka.topic}")
    private String topic;

    private final ProductService productService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    protected BaseBlackFridayDiscount(ProductService productService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.productService = productService;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void applyDiscount() {
        List<Product> products = productService.findAll();
        getStream(products).forEach(this::applyDiscountToProduct);
    }

    protected abstract Stream<Product> getStream(List<Product> products);

    private void applyDiscountToProduct(Product product) {
        int discountPercentage = calculateDiscountPercentage(product.getPrice());
        product.setDiscountPercentage(discountPercentage);
        product.setPriceAfterDiscount(Math.ceil((product.getPrice() - (product.getPrice() * discountPercentage / 100)) * 100) / 100);
        product.setOfferApplied(discountPercentage > 0);
        productService.save(product);
        publishProductEvent(product);
    }

    private int calculateDiscountPercentage(double price) {
        return (price >= 100) ? 50 : (price >= 20 ? 20 : 10);
    }

    private void publishProductEvent(Product product) {
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(product));
        } catch (Exception ex) {
            // Log error with proper logging library
        }
    }
}
