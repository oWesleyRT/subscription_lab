package com.subscription_lab.api.v1.controller;

import com.subscription_lab.api.v1.dto.SubscriptionDTO;
import org.springframework.http.ResponseEntity;

public interface SubscriptionController {

    ResponseEntity<Void> createSubscription(SubscriptionDTO dto);

}
