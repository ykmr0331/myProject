package com.fifa.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.fifa.user.dao.ClubDao;
import com.fifa.user.dao.PlayerDao;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.PlayerUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;

import jakarta.transaction.Transactional;
@SpringBootTest
class PlayerServiceImplTest {
	
	@Autowired
	PlayerService playerService;
	@Autowired
	PlayerDao playerDao;
	@Autowired
	ClubDao clubDao;

	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testFindPlayer() throws Exception{
		PlayerFindDto findPlayer = playerService.findPlayer(3L);
		String clubName = clubDao.findClub(findPlayer.getClubId()).getName();
		
		System.out.println("@@@@@@@@@@플레이어 클럽 아이디"+findPlayer.getClubId());
		System.out.println("@@@@@@@@@@플레이어 클럽 이름"+clubName);
		
	}
	
	
//	@Test
//	@Disabled
//	@Transactional
//	@Rollback(false)
//	void testFindPlayerByMember_UserName() throws Exception{
//		String userName = "user2";
//		List<PlayerFindDto> allPlayerDto = playerService.findPlayerByMember_UserName(userName);
//		System.out.println("################"+allPlayerDto);
//	}
//	
	
//	@Test
//	@Disabled
//	@Transactional
//	@Rollback(false)
//	void testInsert() throws Exception{
//		BigDecimal number = new BigDecimal("100000000000");
//		PlayerInsertDto playerInsertDto = PlayerInsertDto.builder()
//											.name("Ivan Rakitic")
//											.countryId(42L)
//											.clubId(2L)
//											.position("midFielder")
//											.marketValue(number)
//											.memberId(2L)
//											.build();
//		PlayerFindDto insertedPlayerFindDto = playerService.insert(playerInsertDto);
//		System.out.println("@@@@@@@@@@@@@@"+ insertedPlayerFindDto);
//	}
//	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testUpdate() throws Exception{
		Player lucasVascas =playerDao.findPlayer(3L);
		PlayerUpdateDto playerUpdateDto = PlayerUpdateDto.builder()
														.id(lucasVascas.getId())
														.countryId(lucasVascas.getId())
														.clubId(2L)
														.position(lucasVascas.getPosition())
														.marketValue(lucasVascas.getMarketValue())
														.build();
		PlayerUpdateDto updatedPlayerDto = playerService.update(playerUpdateDto);
		System.out.println("updatedPlayerDto는 @@@@@@@@@@@@@@@"+updatedPlayerDto);
		
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testDelete() throws Exception{
		PlayerFindDto findPlayerDto = playerService.delete(6L);
		System.out.println("삭제된 선수~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~``"+findPlayerDto);
		
	}
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void testSearchPlayers() throws Exception{
		List<PlayerFindDto> findDtoplayers = playerService.searchPlayers("ic");
		System.out.println("Players는 "+findDtoplayers);
	}

}
