package com.example.demo.sample.cart.domain;

import com.example.demo.sample.User;
import com.example.demo.sample.UserService;
import com.example.demo.sample.cart.mapper.CartMapper;
import com.example.demo.sample.cart.service.CartDao;
import com.example.demo.sample.cart.service.CartDaoImpl;
import com.example.demo.sample.cart.service.CartService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
   
    private final CartService cartService;
    private final CartDao cartDao;
    private final CartDaoImpl cartDaoImpl;
    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, CartDao cartDao, CartDaoImpl cartDaoImpl,ProductService productService) {
        this.cartService = cartService;
        this.cartDao = cartDao;
        this.productService = productService;
        this.cartDaoImpl = cartDaoImpl;
    }
    




	
	// @@@@@@@@@@@카트를 번호 아이디로 찾기
	@GetMapping("/update_qty")
	public Cart findCartByIdWithNo() {
		Cart cart = new Cart(3, 0, null, null);
		Cart findCart = cartService.findCartByIdWithNo(cart);
		return findCart;
	}
	
	
	// @@@@@@@@@@@카트에 담긴 상품 전체보기
	@GetMapping("/cartProductAll")
	public List<Cart> cartProductAll() throws Exception {
		
		String u_id = "jaehong";
		 List<Cart> cartList = cartService.cartProductAll(u_id);
		 return cartList ;
	}
	
	
	// @@@@@@@@@@@카트에 상품 추가
	@GetMapping("/addCart")
	public int addCart() throws Exception {
		Product product = productService.selectProductByNo(55);
		Cart cart = new Cart(3, 4, "jaehong", product);
		int result = cartService.addCart(cart);
		return result;
	}
	
	// @@@@@@@@@@@카트에 담긴 상품 삭제
	@GetMapping("/delete")
	public int delete() throws Exception {
		
		Cart cart = new Cart(3, 4, "jaehong", null);
		int result =  cartService.delete(cart);
		return result;
	}

	
	//@@@@@@@@@@@ 카트에 담긴 상품 수량 변경
	@GetMapping("/update_size")
	public int update_size() throws Exception {
		Product product = productService.selectProductByNo(56);
		Cart cart = new Cart(4, 10, "minjun", product);
		int result = cartService.update_size(cart);
		return result;
	}
	//제품 사이즈와 회원 아이디로 상품갯수 세기(쉽게 말하자면 민준이가 똑같은 제품의 카트가 n개 있을 때 n이 나온다)
	@GetMapping("/count_productWithSize_by_userId")
	public int count_productWithSize_by_userId() throws Exception{
		Product product = productService.selectProductByNo(58);
		System.out.println("56번 제품은 "+product);
		Cart cart = new Cart(7,0 , "minjun", product);
		int result = cartDaoImpl.count_product_by_userId(cart);
		System.out.println("result = "+result);
		return result;
	}
}
