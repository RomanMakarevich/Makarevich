package com.example.service;

import com.example.converter.IdRecorder;
import com.example.converter.OrderConverter;
import com.example.dto.OrderDTO;
import com.example.entity.BasketEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.OrderMapper;
import com.example.reposiroty.BasketRepository;
import com.example.reposiroty.OrderRepository;
import com.example.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BasketRepository basketRepository;
    private final OrderConverter orderConverter;
    private final UserRepository userRepository;
    private final IdRecorder idRecorder;

    public List<OrderDTO> getList() {
        return orderRepository.findAll().stream().map(orderConverter::entityToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrder(final long orderId) {
        final OrderEntity orderEntity = orderRepository.getOne(orderId);

        return orderConverter.entityToDTO(orderEntity);
    }

    @Transactional
    public OrderDTO createOrder(final Long userId) {

        final BasketEntity basketEntity = basketRepository.findByUserId(userId);
        final long basketId = basketEntity.getId();
        final OrderEntity orderEntity = new OrderEntity();


        orderEntity.setBasketList(basketEntity.getBasketList().stream().map(idRecorder::nullRecorder).collect(Collectors.toList()));
        orderEntity.setUserEntity(basketEntity.getUserEntity());
        orderEntity.setTotalCost(basketEntity.getTotalCost());
//        orderEntity.getBasketList().get(0).setOrderEntity(orderEntity);

        basketRepository.deleteById(basketId);
        orderRepository.save(orderEntity);

        return orderConverter.entityToDTO(orderEntity);
    }
}
