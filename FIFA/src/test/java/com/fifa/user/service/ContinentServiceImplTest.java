package com.fifa.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dto.ContinentDto;
import com.fifa.user.entity.Continent;

import jakarta.transaction.Transactional;
@SpringBootTest
class ContinentServiceImplTest {

	@Autowired
	ContinentService continentService;
	
	@Test
	@Transactional
	@Disabled
	@Rollback(false)
	void testFindContinent() throws Exception{
		Long id = 2L;
		ContinentDto continentDto = continentService.findContinent(id);
		System.out.println("@@@@@@@@@@@@@@@@@@@@"+continentDto);
		
	}
	
	@Test
	@Transactional
	@Disabled
	@Rollback(false)
	void testFindAll() {
		List<ContinentDto> allContinentDto =  continentService.findAll();
		System.out.println("@@@@@@@@@@@@@@allContinentDto"+allContinentDto);
	}

	
	
	
	@Test
	@Transactional
	@Disabled
	@Rollback(false)
	void testInsert()  throws Exception{
		Continent newContinent = Continent.builder().name("신대륙").build();
		continentService.insert(newContinent);
		System.out.println("@@@@@@@@@@@@@@"+newContinent);
	}
	
	
	
	
	
	
	
//	
//	@Test
//	@Transactional
////	@Disabled
//	@Rollback(false)
//	void testFindContinentListByUserName()  throws Exception{
//		String userName ="user1";
//		List<ContinentDto> continentDtoList = continentService.findContinentListByUserName(userName);
//		System.out.println("@@@@@@@@@@@@@@@continentDtoList는"+continentDtoList);
//		
//	}
}
