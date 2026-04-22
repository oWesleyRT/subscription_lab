package com.subscription_lab.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubscriptionStatus {

    PENDING,
    ACTIVATING,
    ACTIVE,
    ACTIVATION_FAILED

}
