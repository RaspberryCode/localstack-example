package pl.softwarekitchen.localstackexample.sqs.listener;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import pl.softwarekitchen.localstackexample.sqs.SqsIntegrationTest;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
class SqsListenerExampleIT extends SqsIntegrationTest {

    @Test
    void shouldSendMessageAndRead() {
        sqsTemplate.send(QUEUE_NAME, DomainEvent.builder()
                .id(123)
                .message("Hello, World!")
                .build());

        verifySqsIsEmpty();
    }

    private void verifySqsIsEmpty() {
        var response = sqsTemplate.receive(QUEUE_NAME, DomainEvent.class);
        assertTrue(response.isEmpty());
    }
}