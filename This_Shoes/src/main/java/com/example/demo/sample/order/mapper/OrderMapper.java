package com.example.demo.sample.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.sample.order.domain.Order;
import com.example.demo.sample.order.domain.OrderItem;

@Mapper
public interface OrderMapper {

	int deleteU_id(String u_id);

	int delete(Order order);

	int insertOrder(Order order);

	int insertOrderItem(OrderItem orderItem);

	List<Order> selectByUserId(String u_id);

	List<Order> selectOrderWithOrderItemsByOrderNo(int o_no);

	List<Order> selectOrderWithOrderItemsByUserId(String u_id);

	/* List<OrderItem> getOrderItemsByOrder(Order order); */
}
