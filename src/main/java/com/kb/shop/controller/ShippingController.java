package com.kb.shop.controller;

import com.kb.shop.controller.dto.CreateShippingInfoRequestDto;
import com.kb.shop.controller.dto.UpdateShippingStatusRequestDto;
import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;

    // 배송 정보를 생성하는 POST 호출을 생성합니다. (path : /shipping)
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody CreateShippingInfoRequestDto dto) {
        return ResponseEntity.ok(
            shippingService.save(dto.orderId(), dto.orderItems())
        );
    }

    // 배송 Status를 변경하는 PUT 호출을 생성합니다. (path : /shipping)
    @PutMapping
    public ResponseEntity<Long> updateStatus(@RequestBody UpdateShippingStatusRequestDto dto) {
        return ResponseEntity.ok(
            shippingService.updateStatus(dto.shippingInfoId(), dto.shippingStatus())
        );
    }

    // 배송 정보를 확인하는 GET 호출을 생성합니다.  (path : /shipping)
    @GetMapping
    public ResponseEntity<ShippingInfo> getInfo(@RequestBody Long shippingInfoId) {
        return ResponseEntity.ok(
            shippingService.getShippingInfo(shippingInfoId)
        );
    }
}
