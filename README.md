# Spring Boot with PostgreSQL: REST API, OpenAPI Documentation, and Advanced Monitoring (Actuator, Prometheus, Grafana) with Performance Testing using Oha

---

## **Table of Contents**
1. [Overview](#overview)
2. [Features and Components](#features-and-components)
3. [Quick Access Links](#quick-access-links)
4. [Requirements](#requirements)
5. [Setup Guide](#setup-guide)
6. [Directory Structure](#directory-structure)
7. [Customization](#customization)
8. [Troubleshooting](#troubleshooting)
9. [Contributing](#contributing)
10. [License](#license)

---

## Overview

This project demonstrates a comprehensive implementation of a **Spring Boot** application integrated with **PostgreSQL**, featuring a robust **REST API** fully documented using **OpenAPI Specification** and **SwaggerUI**. It also includes advanced **monitoring** and **visualization** capabilities powered by **Spring Boot Actuator**, **Prometheus**, and **Grafana**. Additionally, **Oha**, a high-performance HTTP load testing tool, is integrated for stress testing and benchmarking REST API performance.

### Key Highlights:

1. **Spring Boot REST API**:
    - Build scalable and maintainable RESTful endpoints.
    - Document APIs effortlessly with **OpenAPI** and interactive **SwaggerUI**.

2. **Database Integration**:
    - Utilize **PostgreSQL**, a powerful relational database, for reliable and efficient data storage.

3. **Monitoring & Metrics**:
    - Leverage **Spring Boot Actuator** for health checks, metrics, and application insights.
    - Use **Prometheus** for gathering and querying real-time metrics.
    - Visualize data with elegant, customizable dashboards using **Grafana**.

4. **Performance Testing with Oha**:
    - Conduct **HTTP load testing** to evaluate REST API performance.
    - Generate meaningful benchmarks to identify bottlenecks and improve scalability.
    - Effortless CLI-based testing with customizable parameters for concurrent requests and duration.

5. **Containerized Deployment**:
    - Seamless setup using **Docker** and **Docker Compose** to ensure portability and consistency across environments.

This project serves as an all-in-one solution, combining best practices in API development, database integration, monitoring, and performance testing in a production-ready setup.

---

## **Features and Components**

### **Monitoring and Visualization: Prometheus & Grafana**
- **Prometheus** collects and aggregates metrics from services and applications, supports PromQL for querying, and enables alerting configurations.
- **Grafana** provides customizable dashboards for visualizing Prometheus metrics and data from other sources.
- **Ports**:
    - Prometheus: `9090`
    - Grafana: `3000`
- **Persistence**:
    - Prometheus uses the `prom_data` volume for storing metrics.
    - Grafana settings and dashboards are preserved in a mounted volume.
- **Default Credentials** for Grafana:
    - Username: `admin`
    - Password: `admin`

### **Database Management: PostgreSQL & PgAdmin**
- **PostgreSQL** is a relational database for managing structured data with ACID compliance, supporting complex queries and high availability.
- **PgAdmin** is a web-based GUI for managing PostgreSQL databases, simplifying table creation, query execution, and schema design.
- **Ports**:
    - PostgreSQL: `5432`
    - PgAdmin: `5050`
- **Persistence**:
    - PostgreSQL stores data in the `db-data` volume.
    - PgAdmin’s configuration persists in its container volume.
- **Environment Variables**:
    - Configure PostgreSQL and PgAdmin credentials in the `.env` file:
        - `POSTGRES_USER`, `POSTGRES_PW`, `POSTGRES_DB`
        - `PGADMIN_MAIL`, `PGADMIN_PW`

### **Performance Testing: Oha**
- **Oha** is a lightweight and high-performance HTTP load testing tool for benchmarking REST APIs.
- **Key Features**:
    - Conduct concurrent HTTP request simulations to test API scalability.
    - Measure key performance indicators such as latency, throughput, and error rates.
    - Customize testing parameters (e.g., request duration, concurrency levels) directly from the command line.
- **Integration**:
    - Simple CLI installation, compatible with the project’s REST endpoints.
    - Use alongside **Prometheus** and **Grafana** to correlate load testing results with real-time application metrics.
- **Usage Example**:
    ```bash
    oha --duration 30s --concurrency 100 http://localhost:8080/api/endpoint
    ```
  This command sends 100 concurrent requests to the specified API endpoint for 30 seconds.

---


## **Quick Access Links**

### **Documentation**
- **Prometheus**:
    - [PromQL Basics](https://prometheus.io/docs/prometheus/latest/querying/basics/)
    - [Prometheus Configuration Guide](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)
    - [Spring Boot Monitoring with Prometheus and Grafana](https://bell-sw.com/blog/spring-boot-monitoring-in-kubernetes-with-prometheus-and-grafana/)
- **Grafana**:
    - [Grafana Documentation](https://grafana.com/docs/grafana/latest/) (Verified working link)
- **PostgreSQL**:
    - [PostgreSQL Official Docs](https://www.postgresql.org/docs/)
- **PgAdmin**:
    - [PgAdmin Official Docs](https://www.pgadmin.org/docs/)
- **Spring Boot**:
    - [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/reference/actuator/index.html)
    - [Spring Boot External Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
    - [OpenAPI Documentation with Swagger](https://bell-sw.com/blog/documenting-rest-api-with-swagger-in-spring-boot-3/)

---

## **Requirements**

- **Docker**: Version 20.10+
- **Docker Compose**: Version 1.29+
- **Environment Variables**:
    - Ensure the `.env` file is populated with the following:
      ```dotenv
      POSTGRES_USER=your_username
      POSTGRES_PW=your_password
      POSTGRES_DB=your_database
      PGADMIN_MAIL=admin@example.com
      PGADMIN_PW=admin_password
      ```

---

## **Setup Guide**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Configure Environment Variables**
    - Populate the `.env` file as shown in the [Requirements](#requirements).

3. **Start the Services**
   ```bash
   docker-compose up -d
   ```

4. **Verify Setup**
    - **Prometheus**: [http://localhost:9090](http://localhost:9090)
    - **Grafana**: [http://localhost:3000](http://localhost:3000)
    - **PgAdmin**: [http://localhost:5050](http://localhost:5050)

---

## **Directory Structure**

```plaintext
project/
├── docker-compose.yml           # Docker Compose configurations
├── prometheus/                  # Prometheus configuration directory
│   └── prometheus.yml           # Main Prometheus configuration file
├── grafana/                     # Grafana provisioning directory
├── db-data/                     # PostgreSQL data volume
├── src/main/resources/          # Spring Boot resources
│   └── application.properties   # Application-specific properties
├── .env                         # Environment variables file
└── README.md                    # Project documentation
```

---

## **Customization**

1. **Prometheus Configuration**:
    - Edit `./prometheus/prometheus.yml` to add scrape targets and alerts.

2. **Grafana Dashboards**:
    - Customize dashboards and data sources in the `grafana` provisioning directory.

3. **PostgreSQL Initialization**:
    - Place `.sql` files in `/docker-entrypoint-initdb.d/` to initialize database schemas.

4. **Spring Boot Application Properties**:
    - Configure `application.properties` for:
        - Database connections.
        - Logging levels.
        - Custom application behavior.

---

## **Troubleshooting**

### Common Issues:
1. **Containers Fail to Start**:
    - Ensure `.env` is configured correctly.
    - Check for port conflicts using `docker ps` or other system utilities.

2. **Logs**:
    - View logs for any service:
      ```bash
      docker-compose logs <service-name>
      ```

3. **Restart Services**:
    - Restart specific services:
      ```bash
      docker-compose restart <service-name>
      ```

---

## **Contributing**

We welcome contributions! Open an issue or submit a pull request for bug fixes, feature enhancements, or documentation improvements.

---

## **License**

This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---