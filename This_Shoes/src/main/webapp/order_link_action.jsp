<%@page import="com.example.demo.sample.order.domain.Order"%>
<%@ page import="java.util.List" %>
<%@page import="com.example.demo.sample.order.service.OrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>


<%

// 파라미터 받기

String p_noStr = request.getParameter("p_no");
int p_no = Integer.parseInt(p_noStr);

 

// 카트 페이지로 리디렉션
response.sendRedirect("product_form_action.jsp?p_no=" + p_no);
%>
