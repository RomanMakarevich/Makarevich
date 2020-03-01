package com.example.service;

import com.example.converter.IdRecorder;
import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompleteOrderEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.CompleteOrderMapper;
import com.example.reposiroty.CompleteOrderRepository;
import com.example.reposiroty.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class CompleteOrderService {
    private final OrderRepository orderRepository;
    private final CompleteOrderMapper completeOrderMapper;
    private final CompleteOrderRepository completeOrderRepository;
    private final IdRecorder idRecorder;

    @Transactional
    public void completeOrder(final Long orderId) {

        final CompleteOrderEntity completeOrderEntity = new CompleteOrderEntity();

        final OrderEntity orderEntity = orderRepository.findById(orderId).get();
        completeOrderEntity.setFio(orderEntity.getUserEntity().getFio());
        completeOrderEntity.setCompanyNameCustomer(orderEntity.getUserEntity().getCompanyName());
        completeOrderEntity.setAddressCustomer(orderEntity.getUserEntity().getAddress());
        completeOrderEntity.setAccountNumberCustomer(orderEntity.getUserEntity().getAccountNumber());
        completeOrderEntity.setCompanyNameSeller("Завод тары для пива");
        completeOrderEntity.setAddressSeller("г. Минск, ул. Предприятий связанных с пивом");
        completeOrderEntity.setAccountNumberSeller("2222 6666 4444 8888");
        completeOrderEntity.setBasketList(orderEntity.getBasketList().stream().map(idRecorder::nullRecorder).collect(Collectors.toList()));
        completeOrderEntity.getBasketList().stream().forEach(productItemEntity -> productItemEntity.setOrderEntity(orderEntity));
        completeOrderEntity.setTotalCost(orderEntity.getTotalCost());

        orderRepository.deleteById(orderId);
        completeOrderRepository.save(completeOrderEntity);

    }

    public CompleteOrderDTO getCompleteOrder(final long orderId) {

        final CompleteOrderEntity compliteOrderEntity = completeOrderRepository.findById(orderId).get();

        return completeOrderMapper.destinationToSource(compliteOrderEntity);
    }
}
