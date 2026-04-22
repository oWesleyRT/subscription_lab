package com.subscription_lab.core.messaging;

import com.subscription_lab.core.enums.PaymentMethod;
import com.subscription_lab.core.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SubscriptionCreatedEvent {

    private UUID subscriptionId;

    private UUID customerId;

    private UUID planId;

    private PaymentMethod paymentMethod;

    private SubscriptionStatus status;

    private LocalDateTime createdAt;
}
