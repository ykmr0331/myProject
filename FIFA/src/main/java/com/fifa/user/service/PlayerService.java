package com.fifa.user.service;

import java.util.List;

import com.fifa.user.dto.ClubDto;
import com.fifa.user.dto.ClubFindDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.dto.PlayerFindDto;
import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.PlayerUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;

import jakarta.transaction.Transactional;

@Transactional
public interface PlayerService {

	// Player id로 Player찾기
	PlayerFindDto findPlayer(Long playerId) throws Exception;
	// 국가 id로 List<PlayerFindDto>찾기  나중에....
	
	
	//모든 Player찾기
	 List<PlayerFindDto> findAll();
	
	
	//멤버 id로 모든 Player찾기
//	List<PlayerFindDto> findPlayerByMember_UserName(String userName) throws Exception; 
	
	//insert
	PlayerFindDto insert(PlayerInsertDto playerInsertDto) throws Exception;

	//update( id(찾아서) country, club, marketValue, position변경)
	PlayerUpdateDto update(PlayerUpdateDto playerUpdateDto) throws Exception;
	
	//delete
	PlayerFindDto delete(Long playerId)throws Exception;
	
	
	//키워드로 player들 찾기
	List<PlayerFindDto> searchPlayers(String keyword) throws Exception;
	
	 //몸값 TOP3 선수 찾기
	 List<PlayerFindDto> findTop3ByOrderByMarketValueDesc() throws Exception;
	 
}
