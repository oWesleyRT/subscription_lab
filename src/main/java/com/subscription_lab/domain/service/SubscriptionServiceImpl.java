package com.subscription_lab.domain.service;

import com.subscription_lab.api.v1.dto.SubscriptionDTO;
import com.subscription_lab.core.enums.SubscriptionStatus;
import com.subscription_lab.core.messaging.SubscriptionCreatedEvent;
import com.subscription_lab.core.messaging.SubscriptionEventPublisher;
import com.subscription_lab.domain.SubscriptionRepository;
import com.subscription_lab.domain.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final SubscriptionEventPublisher subscriptionEventPublisher;

    public UUID create(SubscriptionDTO dto) {
        var entity = Subscription.builder()
                        .customerId(dto.customerId())
                        .planId(dto.planId())
                        .paymentMethod(dto.paymentMethod())
                        .status(SubscriptionStatus.PENDING)
                        .createdAt(LocalDateTime.now())
                        .build();

        var created = repository.save(entity);

        sendMessageToQueue(created);

        return created.getId();
    }

    private void sendMessageToQueue(Subscription entity) {
        var event = SubscriptionCreatedEvent.builder()
                .subscriptionId(entity.getId())
                .customerId(entity.getCustomerId())
                .planId(entity.getPlanId())
                .paymentMethod(entity.getPaymentMethod())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();

        subscriptionEventPublisher.publishSubscriptionCreated(event);
    }
}
