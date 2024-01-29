package com.fifa.user.service;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dao.PlayerDao;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.TransferMarketFindDto;
import com.fifa.user.dto.TransferMarketInsertDto;

import jakarta.transaction.Transactional;

@SpringBootTest
public class TransferMarketServiceImplTest {
	@Autowired
	TransferMarketService service;
	@Autowired
	PlayerDao pDao;
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testFindTransferMarket() throws Exception{
		Long tId = 1L;
		TransferMarketFindDto findDto = service.findTransferMarket(tId);
		System.out.println("@@@@@@@@@@@@@@"+findDto);
		
		
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testFindAll() throws Exception{
		List<TransferMarketFindDto> findAllDto = service.findAll();
		System.out.println("@@@@@@@@@@@@@@"+findAllDto);
	}
	
	
	@Test
//	@Disabled
	@Transactional
	@Rollback(false)
	void testinsert() throws Exception{
		TransferMarketInsertDto insertDto = TransferMarketInsertDto.builder()
																	.playerId(5L)// 래시포드
																	.previousClubId(8L) // 현재 맨유
																	.transferFee(3000000000000L)//3000억 이적료
																	.newClubId(11L)//첼시로 간다
																	.build();
		
		TransferMarketFindDto findDto = service.insert(insertDto);
		System.out.println();
		System.out.println("@@@@@@@@@@@@@@"+findDto);

	}
	
	
	
}
