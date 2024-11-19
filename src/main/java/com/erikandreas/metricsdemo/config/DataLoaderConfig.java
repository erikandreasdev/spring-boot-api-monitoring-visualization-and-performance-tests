package com.erikandreas.metricsdemo.config;

import com.erikandreas.metricsdemo.entity.Product;
import com.erikandreas.metricsdemo.repository.ProductRepository;
import lombok.Setter;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfig {

    @Value("${data.load.enabled:false}")
    private boolean dataLoadEnabled;

    @Setter
    @Value("${data.load.items:10}")
    private int numberOfProducts;

    @Autowired
    private ProductRepository productRepository;

    private static final int INITIAL_PRICE_MIN = 10;
    private static final int INITIAL_PRICE_MAX = 200;
    private static final int PRICE_DECIMALS = 2;

    @Bean
    public CommandLineRunner loadSampleProducts() {
        return args -> {
            if (dataLoadEnabled) {
                generateAndSaveFakeProducts(numberOfProducts);
            }
        };
    }

    public void reset(int numberOfProducts) {
        productRepository.deleteAll();
        generateAndSaveFakeProducts(numberOfProducts);
    }

    private void generateAndSaveFakeProducts(int numberOfProducts) {
        Faker faker = new Faker();

        for (int i = 0; i < numberOfProducts; i++) {
            Product product = createFakeProduct(faker);
            productRepository.save(product);
        }
    }

    private Product createFakeProduct(Faker faker) {
        return new Product(
                faker.commerce().productName(),
                faker.commerce().department(),
                faker.number().randomDouble(PRICE_DECIMALS, INITIAL_PRICE_MIN, INITIAL_PRICE_MAX)
        );
    }
}