package pl.softwarekitchen.localstackexample.sqs.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.softwarekitchen.localstackexample.sqs.model.DomainEvent;

@Service
@Slf4j
public class DomainEventHandlerService {

    void handle(DomainEvent event) {
        log.info("Handling event: {}", event);
    }
}
