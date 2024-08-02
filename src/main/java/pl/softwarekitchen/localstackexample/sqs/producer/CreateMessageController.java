package pl.softwarekitchen.localstackexample.sqs.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class CreateMessageController {

    private final SendMessageService sendMessageService;

    @PostMapping
    public void create(@RequestParam String message) {
        sendMessageService.send(message);
    }
}
