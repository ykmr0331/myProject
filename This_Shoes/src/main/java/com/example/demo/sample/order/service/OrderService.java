package com.example.demo.sample.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.sample.User;
import com.example.demo.sample.UserDaoImplMyBatis;
import com.example.demo.sample.cart.service.CartDaoImpl;
import com.example.demo.sample.order.domain.Order;
import com.example.demo.sample.order.domain.OrderItem;


@Service
public class OrderService {
	
	
	@Autowired
	private OrderDaoImpl orderDao;
	@Autowired
	private UserDaoImplMyBatis userDaoImpl; 

	public OrderService() throws Exception {
		orderDao = new OrderDaoImpl();
	}

	// 아이디로 주문목록 조회
	public List<Order> orderListById(String u_id) throws Exception{
		return orderDao.selectByUserId(u_id);
	}
	
	// 주문번호로 주문목록 조회
	public List<Order> orderListByOrderNo(int o_no) throws Exception{
		return orderDao.selectOrderWithOrderItemsByOrderNo(o_no);
	}
	
	// 사용자 아이디에 해당하는 주문 목록 및 주문 항목 조회
    public List<Order> orderWithorderItemsByUserId(String u_id) throws Exception {
        return orderDao.selectOrderWithOrderItemsByUserId(u_id);
    }
	
	// 주문목록에 담긴 내역삭제
	public int delete(Order order) throws Exception{
		return orderDao.delete(order);
	}
	
	// 주문 삭제 (사용자 아이디에 해당하는 주문 전체 삭제)
	public int deleteOrderByUserId(String u_id) throws Exception{
		return orderDao.deleteU_id(u_id);
	}

	// 주문 추가
    public int addOrder(Order order) throws Exception {
		List<OrderItem> orderItems = order.getOrderItemList();
		
		//주문설명
		if (orderItems.size() > 1) {
			order.setO_desc(order.getO_desc() + "외 " + (orderItems.size() - 1) + "종");
		} else if(orderItems.size() == 1) {
			System.out.println("@@@@@@@@@@@@orderItems의 제품 이름은 "+orderItems.get(0).getProduct().getP_name() );
			order.setO_desc(orderItems.get(0).getProduct().getP_name());
		} else {
			
		}
		System.out.println("order의 주문 설명은 "+order.getO_desc());
		
		
		int Count = orderDao.insertOrder(order);
		for (OrderItem orderItem : orderItems) {
			orderItem.setO_no(order.getO_no());
			orderDao.insertOrderItem(orderItem);
		}
		 
        return Count; 
    }
	// 주문 항목 추가
	public int addOrderItem(OrderItem orderItem) throws Exception{
		return orderDao.insertOrderItem(orderItem);
	}
    

	
	/*
	 * public List<OrderItem> getOrderItemsByOrder(Order order) throws Exception{
	 * return orderDao.getOrderItemsByOrder(order); }
	 */
	

}
