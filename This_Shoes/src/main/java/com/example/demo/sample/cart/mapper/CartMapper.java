package com.example.demo.sample.cart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.sample.cart.domain.Cart;


@Mapper
@Repository
public interface CartMapper {

	
	public int insert(Cart cart);
	
	public int update_qty(Cart cart);
	
	public int update_size(Cart cart);

	public int delete(Cart cart);
	
	public List<Cart> cartProductAll(String u_id);////////////
	
	public Integer count_product_by_userId(Cart cart);
	
	public int count_productWithSize_by_userId(Cart cart);
	
	public int update_byProductNo(Cart cart);
	
	public Cart findCartbyNo(Cart cart);
	
	
}
