package com.fifa.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fifa.user.dto.TransferMarketInsertDto;
import com.fifa.user.entity.Country;
import com.fifa.user.entity.Player;
import com.fifa.user.entity.TransferMarket;
@SpringBootTest
class TransferMarketDaoImplTest {

	@Autowired
	TransferMarketDao transferMarketDao;
	@Autowired
	ClubDao clubDao;
	@Autowired
	PlayerDao playerDao;
	
	
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testInsert() throws Exception{
		Player va = playerDao.findPlayer(3L);
		TransferMarketInsertDto insertDto = TransferMarketInsertDto.builder()
														.newClubId(2L)
														.playerId(va.getId())
														.previousClubId(va.getClub().getId())
														.transferFee(500000000000L)
														.build();
		TransferMarket resultMarket = transferMarketDao.insert(insertDto);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+resultMarket);
	}
	
	
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void testFindAll() throws Exception{
		List<TransferMarket> TransferMarket1 = transferMarketDao.findAll();
		System.out.println("@@@@@@@@@@@@"+TransferMarket1);
		
	}
	
	
	
	
	@Test
	@Transactional
	@Rollback(false)
//	@Disabled
	void testFindTransferMarket() throws Exception{
		TransferMarket transferMarket1 = transferMarketDao.findTransferMarket(1L);
		System.out.println("@@@@@@@@@@@@"+transferMarket1);
		
	}

}
