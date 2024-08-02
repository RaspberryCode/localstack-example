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