
# Spring Boot Actuator Cheatsheet

Spring Boot Actuator provides production-ready features to help you monitor and manage your application. It exposes various endpoints that can be used to get insight into application's metrics, health, environment, etc.

## Getting Started

To start using Spring Boot Actuator, add the actuator dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Then, you can access various actuator endpoints by including them in your `application.properties` or `application.yml`:

```properties
spring.application.name=demo-application
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
management.endpoints.web.exposure.include=*
```

## Prometheus Integration

To enable Prometheus metrics, add the Prometheus dependency:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

Configure the Prometheus endpoint in `application.properties` or `application.yml`:

```properties
management.metrics.export.prometheus.enabled=true
management.endpoints.web.exposure.include=prometheus
```

Prometheus will scrape the metrics from `/actuator/prometheus` endpoint.

## Grafana Integration

To visualize metrics in Grafana, follow these steps:

1. **Add Prometheus as a data source in Grafana:** 
   - Open your Grafana, go to **Configuration > Data Sources**, and add Prometheus.
   - Set the Prometheus URL (for example, `http://localhost:9090`).

2. **Create a Dashboard:**
   - Click on **Create > Dashboard**.
   - Click **Add new panel**, and select metrics you are interested in.
   - Apply some PromQL queries to fetch data from Prometheus.

3. **Visualize Metrics:**
   - Use the panels and charts to visualize your metrics in Grafana.
