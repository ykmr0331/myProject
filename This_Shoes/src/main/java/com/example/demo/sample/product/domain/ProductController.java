package com.example.demo.sample.product.domain;

import com.example.demo.sample.User;
import com.example.demo.sample.UserService;
import com.example.demo.sample.product.mapper.ProductMapper;
import com.example.demo.sample.product.service.ProductService;
import com.example.demo.sample.product.service.ProductServiceImpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
   
	private final ProductService productService;

    
	public ProductController(ProductService ProductService){
        this.productService = ProductService;
    }
    


	
    // @@@@@@@@@@@@@@전체상품 높은 가격순으로 정렬(내림차순)
    @GetMapping("/findAllByPriceHigh")
    public List<Product> findAllByPriceHigh() throws Exception {
        return productService.findAllByPriceHigh();
    }
   
    
    
	// @@@@@@@@@@@@@@브랜드별 가격 높은순 정렬
    @GetMapping("/findBrandPriceHigh")
	public List<Product> findBrandPriceHigh() throws Exception {
    	String p_brand = "아식스";
    	
		return productService.findBrandPriceHigh(p_brand);
	}
    
	// @@@@@@@@@@@@@@전체상품 낮은 가격순으로 정렬(오름차순)
    @GetMapping("/findAllByPriceLow")
	public List<Product> findAllByPriceLow() throws Exception {
		return productService.findAllByPriceLow();
	}
    
	// @@@@@@@@@@@@@@브랜드별 낮은 가격순으로 정렬(오름차순)
    @GetMapping("/findBrandPriceLow")
	public List<Product> findBrandPriceLow() throws Exception {
    	String p_brand = "아식스";
		return productService.findBrandPriceLow(p_brand);
	}
    
    
	// @@@@@@@@@@@@@@전체상품 조회순 높은 순으로 정렬(인기순)
    @GetMapping("/findAllByClickCountHigh")
	public List<Product> findAllByClickCountHigh() throws Exception {
		return productService.findAllByClickCountHigh();
	}
    
    
	// @@@@@@@@@@@@@@브랜드별 조회순 높은 순으로 정렬(인기순)
    @GetMapping("/findBrandClickCountHigh")
	public List<Product> findBrandClickCountHigh() throws Exception {
		return productService.findBrandClickCountHigh();
	}

    
	// @@@@@@@@@@@@@@검색 기능 구현
    @GetMapping("/searchProductsByName")
	public List<Product> searchProductsByName() throws Exception {
    	String keyword = "젤";
		return productService.searchProductsByName(keyword);
	}
    
    
	//@@@@@@@@@@@@@@ 전체 목록 뽑아오기
    @GetMapping("/productSelectAll")
	public List<Product> productSelectAll() throws Exception {
		return productService.productSelectAll();
	}
    
    
	// @@@@@@@@@@@@@@상품 번호로 상품 뽑아오기
    @GetMapping("/selectProductByNo")
	public Product selectProductByNo() throws Exception {
    	int p_no = 55;
		return productService.selectProductByNo(p_no);
	}
    
	// @@@@@@@@@@@@@@브랜드로 상품 뽑아오기
    @GetMapping("/selectProductByBrand")
	public List<Product> selectProductByBrand() throws Exception {
    	String p_brand = "나이키";
		return productService.selectProductByBrand(p_brand);
	}
    
    
	//@@@@@@@@@@@@@@품절된 상품 가져오기
    @GetMapping("/soldOutProducts")
	public List<Product> soldOutProducts() throws Exception {
    	int p_size = 250;
		return productService.soldOutProducts(p_size);
	}
    
	// 상품 번호로 상품 뽑아오기
    @GetMapping("/getTwoProductsPerBrand")
	public List<Product> getTwoProductsPerBrand() throws Exception {
		return productService.getTwoProductsPerBrand();
	} 
    
}
