package com.example.service;

import com.example.dto.CompleteOrderDTO;
import com.example.entity.CompliteOrderEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.CompliteOrderMapper;
import com.example.reposiroty.CompliteOrderRepository;
import com.example.reposiroty.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Service
public class CompliteOrderService {
    private final OrderRepository orderRepository;
    private OrderEntity orderEntity;
    private CompliteOrderEntity compliteOrderEntity;
    private CompleteOrderDTO completeOrderDTO;
    private CompliteOrderMapper compliteOrderMapper;
    private final CompliteOrderRepository compliteOrderRepository;

    @Transactional
    public CompleteOrderDTO compliteOrder(final long orderId) {

        orderEntity = orderRepository.findById(orderId).get();
        compliteOrderEntity.setFio(orderEntity.getUserEntity().getFio());
        compliteOrderEntity.setCompanyNameCustomer(orderEntity.getUserEntity().getCompanyName());
        compliteOrderEntity.setAdressCustomer(orderEntity.getUserEntity().getAdress());
        compliteOrderEntity.setAccountNumberCustomer(orderEntity.getUserEntity().getAccountNumber());
        compliteOrderEntity.setCompanyNameSeller("Завод тары для пива");
        compliteOrderEntity.setAdressSeller("г. Минск, ул. Предприятий связанных с пивом");
        compliteOrderEntity.setAccountNumberSeller("2222 6666 4444 8888");
//        compliteOrderEntity.setBasketList(orderEntity.getBasketEntity().getBasketList());
        compliteOrderEntity.setTotalCost(orderEntity.getTotalCost());

        completeOrderDTO = compliteOrderMapper.destinationToSource(compliteOrderEntity);

        orderRepository.deleteById(orderId);
        compliteOrderRepository.save(compliteOrderEntity);

        return completeOrderDTO;
    }

    public CompleteOrderDTO getCompleteOrder(final long orderId) {
        compliteOrderEntity = compliteOrderRepository.findById(orderId).get();
        completeOrderDTO = compliteOrderMapper.destinationToSource(compliteOrderEntity);

        return completeOrderDTO;
    }
}
