package com.fifa.user.dao;

import java.util.List;
import java.util.Optional;

import com.fifa.user.dto.ClubInsertDto;
import com.fifa.user.dto.ClubUpdateDto;
import com.fifa.user.entity.Club;
import com.fifa.user.entity.Player;


public interface ClubDao {
	//find, findAll, insert, update, delete
	 Club findClub(Long id) throws Exception;		 // Club id로 Club찾기   
//	List<Club>findClubByMember_UserName(String userName) throws Exception;    	//멤버 id로 모든 Player 찾기
	List<Club> findAll();    					 //모든 Club 찾기
	Club insert(ClubInsertDto clubInsertDto) throws Exception;		 // insert
	Club update(ClubUpdateDto clubUpdateDto) throws Exception;// update
	Club delete(Long id) throws Exception; //delete
	List<Club> findTop3ByOrderByFundsValueDesc() throws Exception; //자금 Top3 클럽
	
} 
