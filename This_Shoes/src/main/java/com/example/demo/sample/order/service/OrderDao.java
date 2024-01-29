package com.example.demo.sample.order.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.sample.order.domain.Order;
import com.example.demo.sample.order.domain.OrderItem;
@Repository
public interface OrderDao {

	int deleteU_id(String u_id) throws Exception;

	int delete(Order order) throws Exception;

	int insertOrder(Order order) throws Exception;

	int insertOrderItem(OrderItem orderItem) throws Exception;

	List<Order> selectByUserId(String u_id) throws Exception;

	List<Order> selectOrderWithOrderItemsByOrderNo(int o_no) throws Exception;

	List<Order> selectOrderWithOrderItemsByUserId(String u_id) throws Exception;

}