package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fifa.user.entity.Club;
@SpringBootTest
class ClubDaoImplTest {

	@Autowired
	ClubDao clubDao;
	@Autowired
	CountryDao countryDao;
	
	
	@Test
	@Rollback
//	@Disabled
	@Transactional
	void testFindClub() throws Exception {
		Club club= clubDao.findClub(24L);
		System.out.println("@@@@@@@@@@@"+club);
	}

	@Test
	@Rollback
	@Disabled
	@Transactional
	void testFindAll() throws Exception {
		List<Club> allClub= clubDao.findAll();
		System.out.println("@@@@@@@@@@@"+allClub);
	}
	
	
//	@Test
//	@Rollback(false)
////	@Disabled
//	@Transactional
//	void testInsert() throws Exception {
//		Club club = new Club().builder()
//								.country(countryDao.findCountry(153L))//England
//								.funds(7000000000000L)//10조
//								.name("rutonTower")
//								.build();
//		clubDao.insert(club);
//		System.out.println("@@@@@@@@Club ="+club);
//	
//	}
	
	
}
