package pl.softwarekitchen.localstackexample.sqs.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;

@Controller
@Slf4j
@RequiredArgsConstructor
@Profile({"!disable-sqs-listener"})
public class SqsListenerExample {

    private final pl.softwarekitchen.localstackexample.sqs.listener.DomainEventHandlerService domainEventHandler;

    @SqsListener(queueNames = {"${app.queues.example-queue}"})
    public void listen(@Payload DomainEvent event) {
        domainEventHandler.handle(event);
    }
}
