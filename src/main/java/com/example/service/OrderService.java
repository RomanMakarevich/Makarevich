package com.example.service;

import com.example.dto.OrderDTO;
import com.example.entity.BasketEntity;
import com.example.entity.OrderEntity;
import com.example.mapper.OrderMapper;
import com.example.reposiroty.BasketRepositoty;
import com.example.reposiroty.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private OrderEntity orderEntity;
    private final OrderMapper orderMapper;
    private final BasketRepositoty basketRepositoty;
    private BasketEntity basketEntity;
    private OrderDTO orderDTO;


    public OrderDTO getList(final long orderid) {
        orderEntity = orderRepository.getOne(orderid);
        orderDTO = orderMapper.destinationToSource(orderEntity);
        return orderDTO;
    }

    @Transactional
    public OrderDTO createOrder(final long userId) {
        basketEntity = basketRepositoty.getOne(userId);
        basketRepositoty.deleteById(userId);
        orderRepository.save(orderEntity);
        return orderMapper.destinationToSource(orderEntity);
    }
}
