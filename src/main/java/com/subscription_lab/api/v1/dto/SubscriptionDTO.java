package com.subscription_lab.api.v1.dto;

import com.subscription_lab.core.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;

public record SubscriptionDTO(
        @NotBlank
        String customerId,
        @NotBlank
        String planId,
        @NotBlank
        PaymentMethod paymentMethod
) {
}
