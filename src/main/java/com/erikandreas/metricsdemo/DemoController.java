package com.erikandreas.metricsdemo;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Timed(value = "z.api", description = "Time spent processing all tenants", histogram = true)
    @GetMapping()
    public String slowAPI() throws InterruptedException {
        slowMethod();
        return "Hello World!";
    }

    private static void slowMethod() {
        try {
            Thread.sleep((long) (Math.random() * 5000)); // Mock processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}