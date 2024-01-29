package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.entity.Continent;

import jakarta.transaction.Transactional;
@SpringBootTest
class ContinentDaoImplTest {

	@Autowired
	ContinentDao continentDao;
	
	
	/*
	    @Transactional을 쓰는 이유
	  - 테스트 중에 롤백
	  - 데이터베이스에 대한 테스트가 실제로 데이터베이스에 영향을 주지 않고 테스트를 할 수 있습
	  - 테스트 간의 격리를 보장
	  - 테스트의 실행 시간이 줄어들 수 있슴
	  -	
	 */
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindContinent()throws Exception {
		Continent continent = continentDao.findContinent(1L);
		System.out.println(continent);
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindAll()throws Exception {
		List<Continent> allContinent= continentDao.findAll();
		System.out.println("@@@@@@@@@@@@@@"+allContinent);
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testInsert()throws Exception {
		Continent continent =new Continent().builder().name("어딘가").build();
		System.out.println("@@@@@@@"+continent);
		continentDao.insert(continent);
		
		
			
	}
	
//	@Test
//	@Transactional
//	@Rollback(false)
////	@Disabled
//	void testindContinentByMember_UserName()throws Exception {
//		String userName = "user1";
//		List<Continent> continentList = continentDao.findContinentByMember_UserName(userName);
//		System.out.println("@@@@@@@@continentList는 "+continentList);
//		
//			
//	}
}
