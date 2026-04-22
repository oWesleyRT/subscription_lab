package com.subscription_lab.api.v1.dto;

import com.subscription_lab.core.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubscriptionDTO(
        @NotNull
        UUID customerId,
        @NotNull
        UUID planId,
        @NotNull
        PaymentMethod paymentMethod
) {
}
