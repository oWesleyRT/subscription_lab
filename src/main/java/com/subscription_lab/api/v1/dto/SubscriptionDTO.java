package com.subscription_lab.api.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record SubscriptionDTO(
        @NotBlank
        String customerId,
        @NotBlank
        String planId,
        @NotBlank
        String paymentMethod
) {
}
