package com.erikandreas.metricsdemo.service;

import com.erikandreas.metricsdemo.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class BlackFridayDiscountWithParallelStreams extends BaseBlackFridayDiscount {
    public BlackFridayDiscountWithParallelStreams(ProductService productService, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        super(productService, kafkaTemplate, objectMapper);
    }

    @Override
    protected Stream<Product> getStream(List<Product> products) {
        return products.parallelStream();
    }
}
