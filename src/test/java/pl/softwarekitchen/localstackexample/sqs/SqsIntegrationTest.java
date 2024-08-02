package pl.softwarekitchen.localstackexample.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.UUID;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConfiguration(proxyBeanMethods = false)
@ActiveProfiles("integration-test")
@Testcontainers
@Slf4j
public abstract class SqsIntegrationTest {

    @Container
    public static LocalStackContainer localStack = new LocalStackContainer(
            DockerImageName.parse("localstack/localstack:3.6.0")
    );
    public static final String QUEUE_NAME = UUID.randomUUID()
            .toString();

    @Autowired
    protected SqsTemplate sqsTemplate;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        log.info("Creating queue: {} in Localstack({})", QUEUE_NAME, localStack.getEndpointOverride(SQS));
        localStack.execInContainer(
                "awslocal",
                "sqs",
                "create-queue",
                "--queue-name",
                QUEUE_NAME
        );
    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.credentials.access-key", () -> localStack.getAccessKey());
        registry.add("spring.cloud.aws.credentials.secret-key", () -> localStack.getSecretKey());
        registry.add("spring.cloud.aws.region.static", () -> localStack.getRegion());
        registry.add("spring.cloud.aws.sqs.endpoint",
                () -> localStack.getEndpointOverride(SQS)
                .toString());
        registry.add("app.queues.example-queue", () -> QUEUE_NAME);
    }
}
