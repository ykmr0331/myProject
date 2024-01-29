package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.entity.Country;
import com.fifa.user.entity.Player;
@SpringBootTest
class PlayerDaoImplTest {

	@Autowired
	PlayerDao playerDao;
	@Autowired
	ClubDao clubDao;
	@Autowired
	CountryDao countryDao;
	
	@Test
	@Disabled
	@Rollback(false)
	@Transactional
	void testInsert() throws Exception{
		String stringValue = "170000000000";//1700억임
		BigDecimal number = new BigDecimal(stringValue);
		
		
		PlayerInsertDto playerInsertDto = PlayerInsertDto.builder()
														.name(stringValue)
														.clubId(null)
														.countryId(null)
														.marketValue(number)
														.position(stringValue)
														.build();
		playerDao.insert(playerInsertDto);
	}
	
	
//	@Test
//	@Transactional
//	@Rollback(false)
////	@Disabled
//	void testFindPlayerByMember_UserName() throws Exception{
//		String userName = "user2";
//		List<Player> allPlayer= playerDao.findPlayerByMember_UserName(userName);
//		System.out.println("@@@@@@@@@@@@"+allPlayer);
//		
//	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindPlayer() throws Exception{
		Player player = playerDao.findPlayer(3L);
		System.out.println("@@@@@@@@@@@@"+player);
		
	}

}
