package org.efire.net.controller;

import lombok.extern.slf4j.Slf4j;
import org.efire.net.model.Voucher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/vouchers")
@Slf4j
public class MockVoucherApi {

    @GetMapping("/{code}")
    public ResponseEntity<Voucher> redeem(@PathVariable String code) {
        log.info("Getting voucher details....");
        var voucher = new Voucher();
        voucher.setCode(code);
        voucher.setDiscount(BigDecimal.TEN);
        return ResponseEntity.ok(voucher);
    }
}
