package com.erikandreas.metricsdemo.rest;

import com.erikandreas.metricsdemo.config.DataLoaderConfig;
import com.erikandreas.metricsdemo.entity.Product;
import com.erikandreas.metricsdemo.service.BlackFridayDiscountWithParallelStreams;
import com.erikandreas.metricsdemo.service.BlackFridayDiscountWithStreams;
import com.erikandreas.metricsdemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    private final BlackFridayDiscountWithStreams blackFridayDiscountWithStreams;

    private final BlackFridayDiscountWithParallelStreams blackFridayDiscountWithParallelStreams;

    private final DataLoaderConfig dataLoaderRunner;

    public ProductRestController(ProductService productService,
                             BlackFridayDiscountWithStreams blackFridayDiscountWithStreams,
                             BlackFridayDiscountWithParallelStreams blackFridayDiscountWithParallelStreams,
                             DataLoaderConfig dataLoaderRunner) {
        this.productService = productService;
        this.blackFridayDiscountWithStreams = blackFridayDiscountWithStreams;
        this.blackFridayDiscountWithParallelStreams = blackFridayDiscountWithParallelStreams;
        this.dataLoaderRunner = dataLoaderRunner;
    }

    //this endpoint for testing
    @GetMapping("/ids")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getIds() {
        return productService.findAll();
    }

    //this endpoint for data reset
    @PostMapping("/reset/{number}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> resetProductRecords(@PathVariable int number) {
        try {
            dataLoaderRunner.reset(number);
            return ResponseEntity.ok("Product records have been reset.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to reset product records.");
        }
    }

    @PostMapping("/discount/stream/{number}")
    public ResponseEntity<String> discount(@PathVariable int number) {
        try {
            dataLoaderRunner.reset(number);
            blackFridayDiscountWithStreams.applyDiscount();
            return ResponseEntity.ok("Products processed and events published.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process product IDs.");
        }
    }

    @PostMapping("/discount/parallel/{number}")
    public ResponseEntity<String> discountAsync(@PathVariable int number) {
        try {
            dataLoaderRunner.reset(number);
            blackFridayDiscountWithParallelStreams.applyDiscount();
            return ResponseEntity.ok("Products processed and events published. Async version");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process product IDs.");
        }
    }

}
