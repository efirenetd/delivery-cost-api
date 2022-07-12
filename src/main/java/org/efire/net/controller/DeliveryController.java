package org.efire.net.controller;

import org.efire.net.dto.ItemDto;
import org.efire.net.dto.ResponseDto;
import org.efire.net.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> cost(@Valid @RequestBody ItemDto dto) {

        var updatedItem = deliveryService.getCost(dto);
        var successResponse = ResponseDto.builder()
                .message("Delivery cost calculation completed.")
                .status(HttpStatus.OK.toString())
                .timestamp(Instant.now())
                .data(updatedItem)
                .build();
        return ResponseEntity.ok(successResponse);
    }
}
