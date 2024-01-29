package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.entity.Country;

import jakarta.transaction.Transactional;
@SpringBootTest
class CountryDaoImplTest {

	@Autowired
	CountryDao countryDao;
	@Autowired
	ContinentDao continentDao;
	
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
////	@Disabled
//	void testFindCountryByMember_UserName() throws Exception{
//		String userName ="user2";
//		List<Country> allCountry = countryDao.findCountryByMember_UserName(userName);
//		System.out.println("@@@@@@@@@@@@"+allCountry);
//		
//	}
	
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindCountry() throws Exception{
		Country country = countryDao.findCountry(632L);
		System.out.println("@@@@@@@@@@@@"+country);
		
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testInsert() throws Exception{
		Country country = Country.builder().continent(continentDao.findContinent(2L))
										.name("spain").build();			
		
		countryDao.insert(country);
		System.out.println("@@@@@@@@@"+country);
		
	}

}
