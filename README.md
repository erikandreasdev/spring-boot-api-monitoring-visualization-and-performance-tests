Here's a detailed `README.md` for the provided `docker-compose.yml` file.

---

# Monitoring and Database Infrastructure

This repository contains a `docker-compose.yml` configuration to set up a monitoring stack with **Prometheus**, **Grafana**, and **PostgreSQL**, along with **pgAdmin** for database management.

## Table of Contents

1. [Overview](#overview)
2. [Services](#services)
3. [Getting Started](#getting-started)
4. [Environment Variables](#environment-variables)
5. [Volumes](#volumes)
6. [Ports](#ports)
7. [Usage](#usage)
8. [Troubleshooting](#troubleshooting)

## Overview

This setup provides:
- **Prometheus**: A powerful monitoring and alerting toolkit.
- **Grafana**: A data visualization platform that integrates with Prometheus.
- **PostgreSQL**: A robust relational database system.
- **pgAdmin**: A management tool for PostgreSQL databases.

The infrastructure is containerized using Docker Compose, ensuring easy deployment and maintenance.

---

## Services

### 1. Prometheus
- **Image**: `prom/prometheus`
- **Purpose**: Monitoring and alerting.
- **Config Location**: `./prometheus`
- **Ports**: `9090:9090`

### 2. Grafana
- **Image**: `grafana/grafana`
- **Purpose**: Visualizing metrics from Prometheus.
- **Config Location**: `./grafana`
- **Environment Variables**:
    - `GF_SECURITY_ADMIN_USER`: Grafana admin username.
    - `GF_SECURITY_ADMIN_PASSWORD`: Grafana admin password.
- **Ports**: `3000:3000`

### 3. PostgreSQL
- **Image**: `postgres:latest`
- **Purpose**: Relational database.
- **Environment Variables**:
    - `POSTGRES_USER`: PostgreSQL username.
    - `POSTGRES_PASSWORD`: PostgreSQL password.
    - `POSTGRES_DB`: Name of the default database.
- **Ports**: `5432:5432`

### 4. pgAdmin
- **Image**: `dpage/pgadmin4:latest`
- **Purpose**: Database administration for PostgreSQL.
- **Environment Variables**:
    - `PGADMIN_DEFAULT_EMAIL`: Email for pgAdmin login.
    - `PGADMIN_DEFAULT_PASSWORD`: Password for pgAdmin login.
- **Ports**: `5050:80`

---

## Getting Started

### Prerequisites
- Docker and Docker Compose installed on your system.
- Ensure environment variables are defined in a `.env` file or passed directly.

### Steps
1. Clone this repository:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```
2. Define environment variables in a `.env` file:
   ```env
   POSTGRES_USER=your-username
   POSTGRES_PW=your-password
   PGADMIN_MAIL=your-email
   PGADMIN_PW=your-pgadmin-password
   ```
3. Start the services:
   ```bash
   docker-compose up -d
   ```

4. Access the services:
    - **Prometheus**: [http://localhost:9090](http://localhost:9090)
    - **Grafana**: [http://localhost:3000](http://localhost:3000)
    - **pgAdmin**: [http://localhost:5050](http://localhost:5050)

---

## Environment Variables

| Variable           | Description                     | Default/Required |
|---------------------|---------------------------------|------------------|
| `POSTGRES_USER`     | PostgreSQL username            | Required         |
| `POSTGRES_PW`       | PostgreSQL password            | Required         |
| `PGADMIN_MAIL`      | pgAdmin email                  | Required         |
| `PGADMIN_PW`        | pgAdmin password               | Required         |
| `GF_SECURITY_ADMIN_USER` | Grafana admin username        | Default: `admin` |
| `GF_SECURITY_ADMIN_PASSWORD` | Grafana admin password        | Default: `admin` |

---

## Volumes

| Volume Name | Mounted Path                          | Description              |
|-------------|---------------------------------------|--------------------------|
| `prom_data` | `/prometheus`                        | Prometheus data storage  |
| `db-data`   | `/var/lib/postgresql/data`           | PostgreSQL data storage  |

---

## Ports

| Service    | Host Port | Container Port |
|------------|-----------|----------------|
| Prometheus | 9090      | 9090           |
| Grafana    | 3000      | 3000           |
| PostgreSQL | 5432      | 5432           |
| pgAdmin    | 5050      | 80             |

---

## Usage

- **Customizing Prometheus**: Update the configuration in `./prometheus/prometheus.yml`.
- **Adding Grafana Dashboards**: Place JSON dashboard files in `./grafana`.
- **Accessing PostgreSQL**:
  ```bash
  psql -h localhost -U <POSTGRES_USER> -d <POSTGRES_DB>
  ```

---

## Troubleshooting

- **Containers not starting**: Check `.env` variables and ensure required ones are defined.
- **Permission issues**: Run `chmod -R 755 ./prometheus ./grafana`.
- **Logs**: View container logs:
  ```bash
  docker-compose logs <service-name>
  ```

---

## Contributing

Feel free to submit issues or pull requests for improvements.

## License

This project is licensed under the MIT License.

---

Let me know if you'd like adjustments! 🚀