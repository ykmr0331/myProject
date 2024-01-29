package com.fifa.user.service;

import java.util.List;

import com.fifa.user.dto.ContinentDto;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.entity.Country;

import jakarta.transaction.Transactional;

@Transactional
public interface CountryService {

	
	//국가 아이디로 Country찾기
	CountryDto findCountry(Long countryId) throws Exception;
	
	
	//모든 Country찾기
	List<CountryDto> findAll();
	
	//Country추가하기
	CountryDto insert(Country country) throws Exception;

	
	
	
	
	
}
