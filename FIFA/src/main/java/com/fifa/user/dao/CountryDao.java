package com.fifa.user.dao;

import java.util.List;

import com.fifa.user.entity.Continent;
import com.fifa.user.entity.Country;

public interface CountryDao {
	//find,findAll,insert, 
	
	/*
	 * id로 Country하나 찾기
	 */
	Country findCountry(Long id) throws Exception;
	
	/*
	 * 멤버 id로 모든 Country 찾기
	 */
//	List<Country> findCountryByMember_UserName(String userName) throws Exception;
	
	
	/*
	 * Country 추가하기
	 */
	Country insert(Country country) throws Exception; 
	/*
	 * 모든 Country찾기
	 */
	List<Country> findAll();    					 		

}
