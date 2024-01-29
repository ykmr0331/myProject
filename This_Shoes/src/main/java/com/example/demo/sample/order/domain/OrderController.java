package com.example.demo.sample.order.domain;

import com.example.demo.sample.User;
import com.example.demo.sample.UserService;
import com.example.demo.sample.cart.mapper.CartMapper;
import com.example.demo.sample.cart.service.CartDao;
import com.example.demo.sample.cart.service.CartDaoImpl;
import com.example.demo.sample.cart.service.CartService;
import com.example.demo.sample.order.service.OrderDao;
import com.example.demo.sample.order.service.OrderDaoImpl;
import com.example.demo.sample.order.service.OrderService;
import com.example.demo.sample.product.domain.Product;
import com.example.demo.sample.product.mapper.ProductMapper;
import com.example.demo.sample.product.service.ProductService;
import com.example.demo.sample.product.service.ProductServiceImpl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
   
    private final OrderService orderService;
    private final OrderDao orderDao;
    private final OrderDaoImpl orderDaoImpl;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, OrderDao orderDao, OrderDaoImpl orderDaoImpl,ProductService productService,UserService userService) {
        this.orderService = orderService;
        this.orderDao = orderDao;
        this.orderDaoImpl = orderDaoImpl;
        this.productService = productService;
        this.userService = userService;
    }
    


	// @@@@@@@@@@@@@@아이디로 주문목록 조회
    @GetMapping("/orderListById")
	public List<Order> orderListById() throws Exception{
    	
    	 List<Order> orderList = orderService.orderListById("jaehong");
		return orderList;
	}

	
	// @@@@@@@@@@@@@@주문번호로 주문목록 조회
    @GetMapping("/orderListByOrderNo")
	public List<Order> orderListByOrderNo() throws Exception{
    	List<Order> orderList = orderService.orderListByOrderNo(4);
		return orderList;
	}

    
	
	// @@@@@@@@@@@@@@사용자 아이디에 해당하는 주문 목록 및 주문 항목 조회
    @GetMapping("/orderWithorderItemsByUserId")
    public List<Order> orderWithorderItemsByUserId() throws Exception {
    	List<Order> orderList = orderService.orderWithorderItemsByUserId("jiwoo");
        return orderList;
    }
    
    
	// @@@@@@@@@@@@@@주문목록에 담긴 내역삭제(o_no와 u_id로 주문내역 1개 삭제하는거임)
    @GetMapping("/delete")
	public int delete() throws Exception{
    	Order order = new Order(5, null, null, 0, null, "yujun", null);
		int result = orderService.delete(order);
    	return result;
	}
    
    
	//@@@@@@@@@@@@@@ 주문 삭제 (사용자 아이디에 해당하는 주문 전체 삭제)
    @GetMapping("/deleteOrderByUserId")
	public int deleteOrderByUserId() throws Exception{
		int result = orderService.deleteOrderByUserId("jaehong");
    	return result;
	}
    

 // 주문 추가
    @GetMapping("/addOrder")
    public int addOrder() throws Exception {
    	//o_no, o_desc, o_date, o_price, o_address, u_id,  orderItemList
    	Order order = new Order(0, "좋은물건", null, 1000000, "과천어딘가", "minjun", new ArrayList<OrderItem>());
    	int reuslt = orderService.addOrder(order);
        return reuslt; 
    }
    
	// 주문 항목 추가
    @GetMapping("/addOrderItem")
	public int addOrderItem() throws Exception{
		Product product61 = productService.selectProductByNo(61);
		OrderItem orderItem = new OrderItem(0, 5, 20, 61, product61);
		return orderDao.insertOrderItem(orderItem);
	}
    
    
/*
 
http://localhost:8080/order/orderListByOrderNo      
      
 */
    
}
