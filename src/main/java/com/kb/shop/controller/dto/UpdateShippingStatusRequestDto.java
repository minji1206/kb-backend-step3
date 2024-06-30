package com.kb.shop.controller.dto;

import com.kb.shop.domain.enums.ShippingStatus;

public record UpdateShippingStatusRequestDto(
    Long shippingInfoId,
    ShippingStatus shippingStatus
) {

}
