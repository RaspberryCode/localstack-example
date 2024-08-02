# Spring Boot with LocalStack for AWS Services

This repository demonstrates how to use [LocalStack](https://github.com/localstack/localstack) with a Spring Boot
application for developing and testing AWS services locally. The project includes examples of local configurations using
Docker Compose and integration tests using Testcontainers.

## Introduction

Developing AWS applications can be challenging due to costs and latencies associated with using real AWS services for
development and testing. This project leverages LocalStack, a fully functional local AWS cloud stack, to help developers
simulate AWS services on their local machine.

The integration of LocalStack with Spring Boot allows developers to:

- Rapidly prototype and test AWS services without incurring costs.
- Use Docker Compose for setting up a local environment.
- Utilize Testcontainers for integration testing.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java Development Kit (JDK) 21 or higher](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)

## Setup and Installation

1. **Clone the repository**:
     ```bash
    git clone https://github.com/RaspberryCode/localstack-example.git
    cd localstack-example
    ```
2. **Start LocalStack using Docker Compose**:
     ```bash
    docker-compose up
    ```
3. **Run Spring Application**:
     ```bash
    mvn spring-boot:run
    ```

## Integration Testing with Testcontainers

This project uses Testcontainers for running integration tests. Testcontainers allows you to spin up LocalStack
containers dynamically during testing.

To run the integration tests, execute:
```bash
mvn test
```

The tests are configured to use Testcontainers to start LocalStack containers, which will simulate the necessary AWS
services during the test executio

## Operations on SQS

### List available SQS queues

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs list-queues
```

### Create a new SQS queue

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs create-queue --queue-name example-queue
```

### List SQS queues again to confirm the new queue is created

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs list-queues
```

### Send a message to the newly created SQS queue

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs send-message --queue-url http://localhost:4566/000000000000/example-queue --message-body "Hello, World!"
```

### Receive the message from the SQS queue

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs receive-message --queue-url http://localhost:4566/000000000000/example-queue
```

### Delete a queue

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs delete-queue --queue-url http://localhost:4566/000000000000/example-queue
```

### Purge a queue

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs purge-queue --queue-url http://localhost:4566/000000000000/example-queue
```

### Get queue attributes

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/example-queue --attribute-names All
```

### Set queue attributes

```bash
aws --endpoint-url=http://localhost:4566 --region us-east-1 sqs set-queue-attributes --queue-url http://localhost:4566/000000000000/example-queue --attributes VisibilityTimeout=60
```