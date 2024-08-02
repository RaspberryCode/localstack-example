package pl.softwarekitchen.localstackexample.sqs.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import pl.softwarekitchen.localstackexample.sqs.SqsIntegrationTest;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"disable-sqs-listener", "integration-test"})
class SendMessageServiceIT extends SqsIntegrationTest {

    @Autowired
    private SendMessageService sendMessageService;

    @Test
    void shouldSendMessage() {
        sendMessageService.send("Hello, World!");

        var response = sqsTemplate.receive(QUEUE_NAME, DomainEvent.class);

        assertThat(response).isPresent()
                .hasValueSatisfying(message -> {
                    assertThat(message.getPayload()
                            .message()).isEqualTo("Hello, World!");
                });
    }
}