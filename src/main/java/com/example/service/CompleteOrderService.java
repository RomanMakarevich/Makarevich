package com.example.service;

import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompleteOrderEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.CompleteOrderMapper;
import com.example.reposiroty.CompleteOrderRepository;
import com.example.reposiroty.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Service
public class CompleteOrderService {
    private final OrderRepository orderRepository;
    private final CompleteOrderMapper completeOrderMapper;
    private final CompleteOrderRepository completeOrderRepository;

    @Transactional
    public CompleteOrderDTO completeOrder(final long orderId) {

        final CompleteOrderEntity completeOrderEntity = new CompleteOrderEntity();

        final OrderEntity orderEntity = orderRepository.findById(orderId).get();
        completeOrderEntity.setFio(orderEntity.getUserEntity().getFio());
        completeOrderEntity.setCompanyNameCustomer(orderEntity.getUserEntity().getCompanyName());
        completeOrderEntity.setAddressCustomer(orderEntity.getUserEntity().getAddress());
        completeOrderEntity.setAccountNumberCustomer(orderEntity.getUserEntity().getAccountNumber());
        completeOrderEntity.setCompanyNameSeller("Завод тары для пива");
        completeOrderEntity.setAddressSeller("г. Минск, ул. Предприятий связанных с пивом");
        completeOrderEntity.setAccountNumberSeller("2222 6666 4444 8888");
        completeOrderEntity.setBasketEntity(orderEntity.getBasketEntity());
        completeOrderEntity.setTotalCost(orderEntity.getTotalCost());

        orderRepository.deleteById(orderId);
        completeOrderRepository.save(completeOrderEntity);

        return completeOrderMapper.destinationToSource(completeOrderEntity);
    }

    public CompleteOrderDTO getCompleteOrder(final long orderId) {

        final CompleteOrderEntity compliteOrderEntity = completeOrderRepository.findById(orderId).get();

        return completeOrderMapper.destinationToSource(compliteOrderEntity);
    }
}
