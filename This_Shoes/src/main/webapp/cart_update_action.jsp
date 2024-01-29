<%@page import="com.example.demo.sample.cart.domain.Cart"%>
<%@page import="com.example.demo.sample.cart.service.Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    
<%
    String u_id = (String)session.getAttribute("sUserId");
    String cartNoStr = request.getParameter("cartNo");
    String cartQtyStr = request.getParameter("qty");
    int cartNo = Integer.parseInt(cartNoStr);
    int cartQty = Integer.parseInt(cartQtyStr);

    CartService cartService = new CartService();
    Cart cart = new Cart(cartNo, cartQty, u_id, null);
    cartService.update_qty(cart);
    
    response.sendRedirect("cart_form.jsp");
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=cart %>
</body>
</html>
