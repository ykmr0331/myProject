package com.fifa.user.dao;

import java.util.List;

import com.fifa.user.dto.PlayerInsertDto;
import com.fifa.user.dto.PlayerUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;

public interface PlayerDao {
	//find, findAll, insert, update, delete
	  Player findPlayer(Long id) throws Exception;		 //  id로 Player찾기   
//	 List<Player>findPlayerByMember_UserName(String userName) throws Exception;    	//멤버 id로 모든 Player 찾기
	 List<Player>findAll(); // Player전부 찾기
	 Player insert(PlayerInsertDto playerInsertDto) throws Exception;		 // insert
	 Player update(PlayerUpdateDto playerUpdateDto) throws Exception;// update
	 Player delete(Long id) throws Exception; //delete
	 //몸값 TOP3 선수 찾기
	 List<Player> findTop3ByOrderByMarketValueDesc() throws Exception;
}
