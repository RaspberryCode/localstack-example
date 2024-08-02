package pl.softwarekitchen.localstackexample.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;
import pl.softwarekitchen.localstackexample.sqs.producer.SendMessageService;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MessagePublishedAndReadIT extends SqsIntegrationTest {

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private SqsTemplate sqsTemplate;

    @Test
    void shouldSendMessage() {
        sendMessageService.send("Hello, World!");
        verifySqsIsEmpty();
    }

    private void verifySqsIsEmpty() {
        var response = sqsTemplate.receive(QUEUE_NAME, DomainEvent.class);
        assertTrue(response.isEmpty());
    }
}
