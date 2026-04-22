package com.subscription_lab.domain.service;

import com.subscription_lab.api.v1.dto.SubscriptionDTO;

import java.util.UUID;

public interface SubscriptionService {

    UUID create(SubscriptionDTO dto);
}
