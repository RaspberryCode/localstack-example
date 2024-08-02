package pl.softwarekitchen.localstackexample.sqs.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMessageService {

    @Value("${app.queues.example-queue}")
    private String queueName;
    private final SqsTemplate sqsTemplate;

    public void send(String message) {
        var domainEvent = DomainEvent.builder()
                .id(System.currentTimeMillis())
                .message(message)
                .build();
        log.info("Sending message: {}", domainEvent);
        sqsTemplate.send(to -> to.queue(queueName)
                .payload(domainEvent));
    }
}
