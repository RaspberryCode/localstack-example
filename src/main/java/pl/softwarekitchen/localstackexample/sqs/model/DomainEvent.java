package pl.softwarekitchen.localstackexample.sqs.model;

import lombok.Builder;

@Builder
public record DomainEvent(long id, String message) {
}

