# Challenge Project

## Overview
This repository contains the Spring Boot application for the Challenge Project, designed to meet the specifications of a technical test for price management within an e-commerce platform. This application utilizes Java 17 and is built with Maven. It has also been dockerized for easy deployment using Docker Compose.

## Prerequisites
- Java 17
- Maven
- Docker & Docker Compose (for running the dockerized version)

## Running the Application
To run the application locally, you can use the following command:

```bash
mvn spring-boot:run
```

```For running the dockerized version, use:
docker-compose up or docker-compose up --build
```

```
challenge/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── challenge/
│   │   │           ├── controller/
│   │   │           │   └── PriceController.java
│   │   │           ├── domain/
│   │   │           │   └── Price.java
│   │   │           ├── dto/
│   │   │           │   └── PriceResponseDto.java
│   │   │           ├── exception/
│   │   │           │   └── RestResponseEntityExceptionHandler.java
│   │   │           ├── repository/
│   │   │           │   └── PriceRepository.java
│   │   │           ├── service/
│   │   │           │   └── PriceService.java
│   │   │           └── ChallengeApplication.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/
│               └── challenge/
│                   ├── controller/
│                   ├── repository/
│                   ├── service/
│                   └── ChallengeApplicationTests.java
├── target/
├── .gitignore
├── challenge.iml
└── pom.xml
```

## API Documentation
The service provides a REST endpoint that takes an application date, product identifier, and brand identifier as input parameters and returns the product identifier, brand identifier, applicable rate, application dates, and the final price.

## Data Model
The service uses an in-memory H2 database, initialized with the given example data. The PRICES table schema is designed as follows:

BRAND_ID: foreign key of the brand.
START_DATE, END_DATE: Date range for the price rate.
PRICE_LIST: Identifier for the price list.
PRODUCT_ID: Identifier for the product.
PRIORITY: Disambiguator for price application.
PRICE: Final sale price.
CURR: Currency in ISO format.

## How to Use
Make a request to the endpoint with the following parameters:

Application Date
Product Identifier
Brand Identifier

The service will return the requested pricing information.

## Testing
The application includes a suite of tests to validate the functionality of the REST endpoint against various service requests using the example data.

## What's Valued
Service design and construction.
Code quality.
Correct results in the tests.

