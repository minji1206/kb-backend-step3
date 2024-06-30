package com.kb.shop.controller.dto;

import java.util.List;

public record CreateShippingInfoRequestDto(
    Long orderId,
    List<Long> orderItems
) {


}
