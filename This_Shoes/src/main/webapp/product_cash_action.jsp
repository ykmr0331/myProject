<%@page import="com.example.demo.sample.product.service.ProductService"%>
<%@page import="com.example.demo.sample.User"%>
<%@page import="com.example.demo.sample.UserService"%>
<%@page import="com.example.demo.sample.order.domain.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.demo.sample.product.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.example.demo.sample.order.domain.Order"%>
<%@page import="com.example.demo.sample.order.service.OrderService"%>
<%@page import="com.example.demo.sample.cart.domain.Cart"%>
<%@page import="com.example.demo.sample.cart.service.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	OrderService orderService = new OrderService();
	UserService userService = new UserService(); 
	ProductService productService=new ProductService();
	
	String u_id = (String)session.getAttribute("sUserId");
	
	
	User user = userService.findUser(u_id);
	ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
	String pNo=request.getParameter("p_no");
	String selectedQuantity=request.getParameter("selectedQuantity");
	Product product = productService.selectProductByNo(Integer.parseInt(pNo));
	
	int qty	=Integer.parseInt(selectedQuantity);	
    int pPrice = product.getP_price();
	int totalPrice = 0;
    totalPrice = qty * pPrice;
    orderItemList.add(new OrderItem(0, qty, 0, Integer.parseInt(pNo),product));
	Order order = new Order(0,null,null,totalPrice,user.getU_address(),u_id,orderItemList);
	orderService.addOrder(order);
	
	response.sendRedirect("order_form.jsp"); 
	
	
%>
