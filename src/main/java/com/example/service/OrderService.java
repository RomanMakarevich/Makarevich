package com.example.service;

import com.example.converter.OrderConverter;
import com.example.dto.OrderDTO;
import com.example.entity.BasketEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.OrderMapper;
import com.example.reposiroty.BasketRepositoty;
import com.example.reposiroty.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
    private final BasketRepositoty basketRepositoty;
    private final OrderConverter orderConverter;

    public List<OrderDTO> getList(){
        return orderRepository.findAll().stream().map(orderMapper::destinationToSource).collect(Collectors.toList());
    }

    public OrderDTO getOrder(final long orderid) {
        final OrderEntity orderEntity = orderRepository.getOne(orderid);

        return orderMapper.destinationToSource(orderEntity);
    }

    @Transactional
    public OrderDTO createOrder(final long userId) {
//        ?????????????????????????????????????????????????????????????????????????????????
        final BasketEntity basketEntity = basketRepositoty.getOne(userId);
        final OrderEntity orderEntity = new OrderEntity();

        orderEntity.setBasketEntity(basketEntity);
        orderEntity.setUserEntity(basketEntity.getUserEntity());
        orderEntity.setTotalCost(basketEntity.getTotalCost());

        basketRepositoty.deleteById(userId);
        orderRepository.save(orderEntity);

        return orderConverter.entityToDTO(orderEntity);
    }
}
