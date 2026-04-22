package com.subscription_lab.api.v1.controller;

import com.subscription_lab.api.v1.dto.SubscriptionDTO;
import com.subscription_lab.domain.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/subscription")
public class SubscriptionControllerImpl implements SubscriptionController {

    private final SubscriptionService service;

    @PostMapping
    public ResponseEntity<Void> createSubscription(
            @RequestBody
            @Valid
            SubscriptionDTO dto
    ) {
        UUID id = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
