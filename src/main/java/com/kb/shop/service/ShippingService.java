package com.kb.shop.service;

import com.kb.shop.domain.Order;
import com.kb.shop.domain.OrderItem;
import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.domain.enums.ShippingStatus;
import com.kb.shop.repository.OrderItemRepository;
import com.kb.shop.repository.OrderRepository;
import com.kb.shop.repository.ShippingInfoRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShippingService {
    private final ShippingInfoRepository shippingInfoRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public int save(Long orderId, List<Long> orderItems) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(
                () -> new IllegalArgumentException("[ERROR] Order not found"));
        List<OrderItem> orderItemList = orderItemRepository.findAllById(orderItems);
        List<ShippingInfo> shippingInfos = new ArrayList<>();

        for (OrderItem orderItem : orderItemList) {
            ShippingInfo shippingInfo = ShippingInfo.builder()
                .order(order)
                .orderItem(orderItem)
                .build();

            shippingInfos.add(shippingInfo);
        }

        shippingInfoRepository.saveAll(shippingInfos);
        return shippingInfos.size();
    }

    public Long updateStatus(Long shippingInfoId, ShippingStatus status) {
        ShippingInfo shippingInfo = shippingInfoRepository.findById(shippingInfoId)
            .orElseThrow(() -> new IllegalArgumentException(
                "[ERROR] ShippingInfo not found"));

        shippingInfo.setShippingStatus(status);
        shippingInfoRepository.save(shippingInfo);
        return shippingInfo.getId();
    }

    public ShippingInfo getShippingInfo(Long shippingInfoId) {
        return shippingInfoRepository.findById(shippingInfoId)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] ShippingInfo not found"));
    }
}
