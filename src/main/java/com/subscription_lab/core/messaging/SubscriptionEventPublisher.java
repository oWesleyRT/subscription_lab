package com.subscription_lab.core.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import tools.jackson.databind.ObjectMapper;

@Component
public class SubscriptionEventPublisher {

    private final SqsAsyncClient sqsAsyncClient;
    private final ObjectMapper objectMapper;
    private final String subscriptionCreatedQueueUrl;

    public SubscriptionEventPublisher(
            SqsAsyncClient sqsAsyncClient,
            ObjectMapper objectMapper,
            @Value("${app.queues.subscription-created}")
            String queueName
    ) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.objectMapper = objectMapper;
        this.subscriptionCreatedQueueUrl = resolveQueueUrl(queueName);
    }

    public void publishSubscriptionCreated(SubscriptionCreatedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(subscriptionCreatedQueueUrl)
                    .messageBody(payload)
                    .build();

            sqsAsyncClient.sendMessage(request).join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String resolveQueueUrl(String queueName) {
        return sqsAsyncClient.getQueueUrl(r -> r.queueName(queueName)).join().queueUrl();
    }

}
