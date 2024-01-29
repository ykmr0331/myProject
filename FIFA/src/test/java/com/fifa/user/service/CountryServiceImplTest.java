package com.fifa.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dao.ContinentDao;
import com.fifa.user.dto.CountryDto;
import com.fifa.user.entity.Country;

import jakarta.transaction.Transactional;
@SpringBootTest
class CountryServiceImplTest {
	@Autowired
	CountryService countryService;
	@Autowired
	ContinentDao continentDao;

	@Test
	@Transactional
	@Disabled
	@Rollback(false)
	void testFindCountry()  throws Exception{
		Long countryId = 153L;
		CountryDto findCountry = countryService.findCountry(countryId);
		System.out.println("@@@@@@@CountryDto인 findCountry는 "+findCountry+"입니다.");
		
	}
	
//	
//	@Test
//	@Transactional
////	@Disabled
//	@Rollback(false)
//	void testFindAllByUserName()  throws Exception{
//		String userName = "user2";
//		 List<CountryDto>findAllCountryDto = countryService.findAllByUserName(userName);
//		 System.out.println("@@@@@@@@@@@List<Country>는 "+findAllCountryDto);
//		
//	}
	
	
	
	
	@Test
	@Transactional
	@Disabled 
	@Rollback(false)
	void testInsert() throws Exception {
		Country country = Country.builder()
									.continent(continentDao.findContinent(1L))
									.name("뉴아프리카국")
									.build();
		countryService.insert(country);
		System.out.println("@@@@@@@@@@@@@@@country"+country);
		
	}

}
