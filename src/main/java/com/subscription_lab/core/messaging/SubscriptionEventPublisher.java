package com.subscription_lab.core.messaging;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionEventPublisher {

    private final SqsTemplate sqsTemplate;
    private final String queueName;

    public SubscriptionEventPublisher(
            SqsTemplate sqsTemplate,
            @Value("${app.queues.subscription-created}") String queueName
    ) {
        this.sqsTemplate = sqsTemplate;
        this.queueName = queueName;
    }

    public void publishSubscriptionCreated(SubscriptionCreatedEvent event) {
        sqsTemplate.send(queueName, event);
    }

}